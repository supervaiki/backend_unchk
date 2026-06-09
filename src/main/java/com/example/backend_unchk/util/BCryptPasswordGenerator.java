package com.example.backend_unchk.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utilitaire pour générer les hashs BCrypt des mots de passe
 * À utiliser uniquement pour initialiser la base de données
 */
public class BCryptPasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String password = "admin123";
        String hashedPassword = encoder.encode(password);
        
        System.out.println("===========================================");
        System.out.println("Password: " + password);
        System.out.println("BCrypt Hash: " + hashedPassword);
        System.out.println("===========================================");
        System.out.println("\nCopie ce hash dans init-database.sql :");
        System.out.println("'" + hashedPassword + "'");
        System.out.println("\nVérification:");
        System.out.println("Password matches: " + encoder.matches(password, hashedPassword));
    }
}
