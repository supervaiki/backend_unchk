package com.example.backend_unchk.modules.administration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personnel")
public class Personnel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    private String telephone;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriePersonnel categorie; // ADMINISTRATIF, ENSEIGNANT, TUTEUR
    
    @Column(nullable = false)
    private String fonction;
    
    private String departement;
    
    @Column(nullable = false)
    private LocalDate dateEmbauche;
    
    private LocalDate dateFinContrat;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutPersonnel statut; // ACTIF, INACTIF, EN_CONGE
    
    private String adresse;
    
    private String numeroMatricule;
    
    @Column(columnDefinition = "TEXT")
    private String observation;
    
    private LocalDate dateCreation;
    
    private LocalDate dateModification;
    
    public enum CategoriePersonnel {
        ADMINISTRATIF, ENSEIGNANT, TUTEUR, FORMATEUR
    }
    
    public enum StatutPersonnel {
        ACTIF, INACTIF, EN_CONGE, SUSPENDU
    }
}
