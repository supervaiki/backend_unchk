package com.example.backend_unchk.modules.formation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "planning")
public class Planning {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private LocalTime heureDebut;
    
    @Column(nullable = false)
    private LocalTime heureFin;
    
    @Column(nullable = false)
    private String sujet;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String salle;
    
    private String formateur;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeActivite typeActivite; // COURS, TP, PROJET, EVALUATION, REUNION_TUTELLE
    
    private Integer nombreParticipants;
    
    private String documentPreparation;
    
    private LocalDate dateCreation;
    
    private LocalDate dateModification;
    
    public enum TypeActivite {
        COURS, TP, PROJET, EVALUATION, REUNION_TUTELLE, REUNION_PREPARATION, REUNION_EVALUATION
    }
}
