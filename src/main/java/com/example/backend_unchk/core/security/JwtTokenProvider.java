package com.example.backend_unchk.core.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * DEPRECATED: Utilisez JwtUtils à la place.
 * Cette classe est conservée pour compatibilité avec JwtAuthenticationFilter.
 */
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    /**
     * SOLUTION 2 : Alignement sur JwtUtils pour empêcher le plantage 448 bits
     * lors de la validation des requêtes dans le Filter.
     */
    private Key getSigningKey() {
        try {
            // 1. Essai de décodage de la clé configurée en Base64
            byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
            
            // 2. Vérification de la taille réglementaire pour HS512 (64 octets / 512 bits)
            if (keyBytes.length < 64) {
                log.warn("⚠️ [Compatibilité] La clé 'jwt.secret' est trop courte ({} bits). Clé d'urgence générée pour le Filter.", keyBytes.length * 8);
                return Keys.secretKeyFor(SignatureAlgorithm.HS512);
            }
            
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            // 3. Fallback si la configuration est corrompue
            log.error("❌ [Compatibilité] Impossible de lire 'jwt.secret' dans JwtTokenProvider. Clé automatique générée.", e);
            return Keys.secretKeyFor(SignatureAlgorithm.HS512);
        }
    }

    /**
     * Génère un token JWT simple (Surcharge conservée pour la rétrocompatibilité)
     */
    public String generateToken(String username) {
        return generateToken(username, Collections.emptyList());
    }

    /**
     * AMÉLIORATION : Génère un token incluant les rôles pour permettre une authentification stateless
     */
    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles) // Injection des rôles dans le token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * AJOUT CRUCIAL : Permet à JwtAuthenticationFilter de récupérer les rôles sans interroger la BDD
     */
    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        Object rolesObject = extractClaim(token, claims -> claims.get("roles"));
        
        if (rolesObject instanceof List<?>) {
            return ((List<?>) rolesObject).stream()
                    .map(role -> new SimpleGrantedAuthority((String) role))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * Centralisation de l'extraction des Claims (Principe DRY)
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Méthode générique d'extraction
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
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

    public boolean isTokenExpired(String token) {
        try {
            Date expiration = extractClaim(token, Claims::getExpiration);
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception ex) {
            log.error("Erreur lors de la vérification d'expiration: {}", ex.getMessage());
            return true;
        }
    }
}