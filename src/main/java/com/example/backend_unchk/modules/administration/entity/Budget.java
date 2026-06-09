package com.example.backend_unchk.modules.administration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "budget")
public class Budget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String designation;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeBudget type; // PROJET, REALISE, NOTE_ORIENTATION
    
    @Column(nullable = false)
    private BigDecimal montant;
    
    @Column(nullable = false)
    private Integer annee;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutBudget statut; // EN_ATTENTE, APPROUVE, REJETE, EXECUTE
    
    @Column(nullable = false)
    private LocalDate dateCreation;
    
    private LocalDate dateModification;
    
    private LocalDate dateApprobation;
    
    private String cheminDocument;
    
    public enum TypeBudget {
        PROJET, REALISE, NOTE_ORIENTATION
    }
    
    public enum StatutBudget {
        EN_ATTENTE, APPROUVE, REJETE, EXECUTE
    }
}
