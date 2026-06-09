package com.example.backend_unchk.core.security;

import com.example.backend_unchk.modules.auth.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    /**
     * SOLUTION 2 : Récupère la clé de signature configurée ou génère dynamiquement 
     * une clé conforme aux exigences strictes de taille de l'algorithme HS512.
     */
    private Key key() {
        try {
            // 1. Essai de décodage de la clé configurée en Base64
            byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
            
            // 2. HS512 requiert impérativement une clé d'au moins 512 bits (64 octets)
            if (keyBytes.length < 64) {
                log.warn("⚠️ La clé 'jwt.secret' est trop courte ({} bits). Génération d'une clé d'urgence sécurisée pour HS512.", keyBytes.length * 8);
                return Keys.secretKeyFor(SignatureAlgorithm.HS512);
            }
            
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            // 3. En cas d'absence de configuration ou d'erreur de format
            log.error("❌ Erreur de lecture de 'jwt.secret' (Clé non-Base64 ou absente). Génération d'une clé robuste automatique.", e);
            return Keys.secretKeyFor(SignatureAlgorithm.HS512);
        }
    }

    /**
     * Génère un token JWT à partir de l'authentification
     */
    public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
            .setSubject(userPrincipal.getUsername())
            .claim("email", userPrincipal.getEmail())
            // AJOUT DE CETTE LIGNE : On inclut les rôles dans le jeton
            .claim("roles", userPrincipal.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList()))
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
            .signWith(key(), SignatureAlgorithm.HS512)
            .compact();
}

    /**
     * Génère un token JWT à partir du nom d'utilisateur
     */
    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Méthode générique pour extraire les Claims (Principe DRY - Ne pas se répéter)
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Méthode utilitaire pour extraire une information spécifique
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrait le nom d'utilisateur (sujet) du token JWT
     */
    public String getUsernameFromJwtToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait l'email du token JWT
     */
    public String getEmailFromJwtToken(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    /**
     * Valide la signature et la structure du token JWT
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (SecurityException e) {
            log.error("Clé JWT invalide: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Token JWT invalide: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Token JWT expiré: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Token JWT non supporté: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Chaîne JWT vide: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Vérifie si le token est expiré
     */
    public boolean isTokenExpired(String token) {
        try {
            Date expiration = extractClaim(token, Claims::getExpiration);
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            log.error("Token expiré: {}", e.getMessage());
            return true;
        } catch (Exception ex) {
            log.error("Erreur lors de la vérification d'expiration: {}", ex.getMessage());
            return true;
        }
    }
}