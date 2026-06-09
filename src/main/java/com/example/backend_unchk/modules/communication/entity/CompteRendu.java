package com.example.backend_unchk.modules.communication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compte_rendu")
public class CompteRendu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titre;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeReunion type;
    
    @Column(columnDefinition = "TEXT")
    private String contenu;
    
    @Column(nullable = false)
    private LocalDate dateReunion;
    
    private LocalDateTime heureDebut;
    
    private LocalDateTime heureFin;
    
    private String lieu;
    
    @Column(columnDefinition = "TEXT")
    private String pointsTraites;
    
    @Column(columnDefinition = "TEXT")
    private String decisions;
    
    @Column(columnDefinition = "TEXT")
    private String recommandations;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutCompteRendu statut;
    
    private String cheminDocument;
    
    @Column(nullable = false)
    private String redacteur;
    
    private String approuvePar;
    
    @Column(nullable = false)
    private LocalDateTime dateCreation;
    
    private LocalDateTime dateModification;
    
    private Boolean notificationEnvoyee = false;
    
    public enum TypeReunion {
        REUNION, RENCONTRE, SEMINAIRE, WEBINAIRE, CONSEIL_UNIVERSITE, AUTRE
    }
    
    public enum StatutCompteRendu {
        BROUILLON, PUBLIE, ARCHIVE, REJETE
    }
}