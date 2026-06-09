package com.example.backend_unchk.modules.auth.service;

import com.example.backend_unchk.core.security.JwtUtils;
import com.example.backend_unchk.modules.auth.dto.AuthResponseDTO;
import com.example.backend_unchk.modules.auth.dto.LoginRequestDTO;
import com.example.backend_unchk.modules.auth.entity.Role;
import com.example.backend_unchk.modules.auth.entity.Utilisateur;
import com.example.backend_unchk.modules.auth.repository.RoleRepository;
import com.example.backend_unchk.modules.auth.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    
    // 1. AJOUT CRUCIAL : Injection du gestionnaire d'authentification de Spring Security
    private final AuthenticationManager authenticationManager;

    public Utilisateur registerUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.existsByUsername(utilisateur.getUsername())) {
            throw new RuntimeException("Username déjà existant");
        }
        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new RuntimeException("Email déjà existant");
        }
        
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur.setActif(true);
        utilisateur.setDateCreation(LocalDate.now());
        
        // Assigner le rôle par défaut
        Optional<Role> userRole = roleRepository.findByNom("ROLE_USER");
        if (userRole.isPresent()) {
            utilisateur.getRoles().add(userRole.get());
        }
        
        return utilisateurRepository.save(utilisateur);
    }

    public AuthResponseDTO authenticate(LoginRequestDTO loginRequest) {
        
        // 2. On laisse Spring Security vérifier le mot de passe (cela lèvera une exception si c'est faux)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), 
                        loginRequest.getPassword()
                )
        );

        // 3. On enregistre la session dans le contexte de sécurité
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 4. On génère le token COMPLET (en utilisant l'authentification, pour inclure rôles et email)
        String token = jwtUtils.generateJwtToken(authentication);

        // 5. On met à jour la date de dernière connexion
        Utilisateur user = utilisateurRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
                
        if (!user.getActif()) {
            throw new RuntimeException("Utilisateur inactif");
        }
        
        user.setDerniereConnexion(LocalDate.now());
        utilisateurRepository.save(user); // Sauvegarde
        
        // 6. On construit la réponse
        AuthResponseDTO response = new AuthResponseDTO();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setMessage("Authentification réussie");
        response.setToken(token); // Le token est bien injecté ici
        
        log.info("Authentification réussie pour l'utilisateur: {}", user.getUsername());
        
        return response;
    }

    public Optional<Utilisateur> getUtilisateur(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Optional<Utilisateur> getUtilisateurByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
        Optional<Utilisateur> existing = utilisateurRepository.findById(id);
        if (existing.isPresent()) {
            Utilisateur u = existing.get();
            u.setNom(utilisateur.getNom());
            u.setPrenom(utilisateur.getPrenom());
            u.setTelephone(utilisateur.getTelephone());
            u.setDateModification(LocalDate.now());
            return utilisateurRepository.save(u);
        }
        throw new RuntimeException("Utilisateur non trouvé");
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}