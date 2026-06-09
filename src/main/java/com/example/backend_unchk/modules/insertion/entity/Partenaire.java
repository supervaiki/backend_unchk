package com.example.backend_unchk.modules.insertion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "partenaire")
public class Partenaire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nomEntreprise;
    
    @Column(nullable = false)
    private String secteurActivite;
    
    private String adresse;
    
    private String telephone;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String nomContact;
    
    private String fonctionContact;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypePartenariat typePartenariat; // STAGE, INSERTION, RECRUTEMENT, FORMATION
    
    @Column(nullable = false)
    private LocalDate datePartenariat;
    
    private LocalDate dateFinPartenariat;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutPartenaire statut; // ACTIF, INACTIF, SUSPENDU
    
    @Column(columnDefinition = "TEXT")
    private String observation;
    
    private LocalDate dateCreation;
    
    private LocalDate dateModification;
    
    public enum TypePartenariat {
        STAGE, INSERTION, RECRUTEMENT, FORMATION, AUTRE
    }
    
    public enum StatutPartenaire {
        ACTIF, INACTIF, SUSPENDU, EN_ATTENTE
    }
}
