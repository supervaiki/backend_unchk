package com.example.backend_unchk.modules.auth.controller;

import com.example.backend_unchk.modules.auth.dto.AuthResponseDTO;
import com.example.backend_unchk.modules.auth.dto.LoginRequestDTO;
import com.example.backend_unchk.modules.auth.entity.Utilisateur;
import com.example.backend_unchk.modules.auth.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    // Cette annotation vide enlève le cadenas de Swagger pour cette route
    @SecurityRequirements()
    @PostMapping("/register")
    public ResponseEntity<Utilisateur> register(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.registerUtilisateur(utilisateur));
    }

    // Cette annotation vide enlève le cadenas de Swagger pour cette route
    @SecurityRequirements()
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            AuthResponseDTO response = service.authenticate(loginRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            AuthResponseDTO errorResponse = new AuthResponseDTO();
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Utilisateur> getUser(@PathVariable Long id) {
        return service.getUtilisateur(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<Utilisateur> getUserByUsername(@PathVariable String username) {
        return service.getUtilisateurByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Utilisateur> updateUser(
            @PathVariable Long id,
            @RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(service.updateUtilisateur(id, utilisateur));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}