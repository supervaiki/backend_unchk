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
@Table(name = "courrier")
public class Courrier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String numero;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeCourrier type; // ARRIVE, DEPART, INTERNE, CIRCULAIRE
    
    @Column(nullable = false)
    private String objet;
    
    @Column(columnDefinition = "TEXT")
    private String contenu;
    
    @Column(nullable = false)
    private LocalDate dateReception;
    
    private LocalDate dateTraitement;
    
    private String expediteur;
    
    private String destinataire;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutCourrier statut; // EN_ATTENTE, TRAITE, ARCHIVE
    
    private String cheminFichier;
    
    private LocalDate dateCreation;
    
    private LocalDate dateModification;
    
    public enum TypeCourrier {
        ARRIVE, DEPART, INTERNE, CIRCULAIRE, NOTE_SERVICE, NOTE_ADMINISTRATIVE
    }
    
    public enum StatutCourrier {
        EN_ATTENTE, TRAITE, ARCHIVE, REJETE
    }
}