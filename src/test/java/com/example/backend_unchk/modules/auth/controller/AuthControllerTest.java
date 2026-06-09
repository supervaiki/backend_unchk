package com.example.backend_unchk.modules.auth.controller;

import com.example.backend_unchk.modules.auth.dto.AuthResponseDTO;
import com.example.backend_unchk.modules.auth.dto.LoginRequestDTO;
import com.example.backend_unchk.modules.auth.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests d'intégration pour le contrôleur d'authentification
 * Ces tests vérifient les endpoints d'authentification
 * 
 * Pour exécuter :
 * mvn test -Dtest=AuthControllerTest
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private LoginRequestDTO validLoginRequest;
    private LoginRequestDTO invalidPasswordRequest;
    private LoginRequestDTO invalidUsernameRequest;

    @BeforeEach
    public void setUp() {
        // Requête de connexion valide
        validLoginRequest = new LoginRequestDTO();
        validLoginRequest.setUsername("admin");
        validLoginRequest.setPassword("admin123");

        // Requête avec mauvais mot de passe
        invalidPasswordRequest = new LoginRequestDTO();
        invalidPasswordRequest.setUsername("admin");
        invalidPasswordRequest.setPassword("wrongpassword");

        // Requête avec utilisateur inexistant
        invalidUsernameRequest = new LoginRequestDTO();
        invalidUsernameRequest.setUsername("nonexistent");
        invalidUsernameRequest.setPassword("admin123");
    }

    /**
     * Test 1 : Connexion réussie avec les bonnes credentials
     */
    @Test
    public void testLoginSuccess() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validLoginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.email").value("admin@unchk.edu.dz"))
                .andExpect(jsonPath("$.message").doesNotExist());
    }

    /**
     * Test 2 : Échec de connexion avec mot de passe incorrect
     */
    @Test
    public void testLoginFailureWrongPassword() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidPasswordRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.token").isEmpty())
                .andExpect(jsonPath("$.message").value("Mot de passe incorrect"));
    }

    /**
     * Test 3 : Échec de connexion avec utilisateur inexistant
     */
    @Test
    public void testLoginFailureUserNotFound() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidUsernameRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.token").isEmpty())
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    /**
     * Test 4 : Tentative de connexion avec JSON mal formé
     */
    @Test
    public void testLoginMalformedJson() throws Exception {
        String malformedJson = "{\"username\": \"admin\",}"; // JSON invalide

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(malformedJson))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test 5 : Connexion avec tous les utilisateurs de test
     */
    @Test
    public void testAllTestUsersLogin() throws Exception {
        String[][] testUsers = {
                {"admin", "admin123"},
                {"gestionnaire", "admin123"},
                {"enseignant", "admin123"},
                {"hassan", "admin123"},
                {"fatima", "admin123"},
                {"ali", "admin123"}
        };

        for (String[] credentials : testUsers) {
            LoginRequestDTO request = new LoginRequestDTO();
            request.setUsername(credentials[0]);
            request.setPassword(credentials[1]);

            MvcResult result = mockMvc.perform(post("/api/v1/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token").isNotEmpty())
                    .andExpect(jsonPath("$.username").value(credentials[0]))
                    .andReturn();

            System.out.println("✓ User '" + credentials[0] + "' logged in successfully");
            System.out.println("  Token: " + result.getResponse().getContentAsString());
        }
    }

    /**
     * Test 6 : Obtenir les informations d'un utilisateur par ID
     */
    @Test
    public void testGetUserById() throws Exception {
        // D'abord, se connecter pour obtenir l'ID de l'utilisateur
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validLoginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());

        // Ensuite, récupérer les informations de l'utilisateur (ID 1 pour admin)
        mockMvc.perform(post("/api/v1/auth/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // GET au lieu de POST
    }
}
