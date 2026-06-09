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
@Table(name = "stage")
public class Stage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long etudiantId;
    
    @Column(nullable = false)
    private LocalDate dateDebut;
    
    @Column(nullable = false)
    private LocalDate dateFin;
    
    private String entreprise;
    
    private String posteOccupe;
    
    @Column(columnDefinition = "TEXT")
    private String descriptifTaches;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutStage statut; // EN_COURS, TERMINE, ANNULE
    
    @Column(columnDefinition = "TEXT")
    private String bilanStage;
    
    private Double noteEvaluation;
    
    private String evaluateurEntreprise;
    
    private String evaluateurEcole;
    
    private String cheminRapport;
    
    private LocalDate dateCreation;
    
    private LocalDate dateModification;
    
    public enum StatutStage {
        EN_COURS, TERMINE, ANNULE, EN_ATTENTE
    }
}
