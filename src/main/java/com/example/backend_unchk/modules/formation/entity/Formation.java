package com.example.backend_unchk.modules.formation.entity;

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
@Table(name = "formation")
public class Formation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titre;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private LocalDate dateDebut;
    
    @Column(nullable = false)
    private LocalDate dateFin;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeFormation type; // CERTIFICATION, PRIVEE, CONTINUE
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NiveauFormation niveau; // INITIATION, INTERMEDIATE, AVANCE
    
    private BigDecimal montant;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeFinancement typeFinancement; // INTERNE, EXTERNE, PARTENARIAT
    
    private Integer nombreFormes;
    
    private Integer nombreFemmes;
    
    private Integer nombreHommes;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutFormation statut; // PLANIFIEE, EN_COURS, TERMINEE, ANNULEE
    
    private String lieu;
    
    private LocalDate dateCreation;
    
    private LocalDate dateModification;
    
    public enum TypeFormation {
        CERTIFICATION, PRIVEE, CONTINUE, REGULIERE
    }
    
    public enum NiveauFormation {
        INITIATION, INTERMEDIATE, AVANCE, EXPERT
    }
    
    public enum TypeFinancement {
        INTERNE, EXTERNE, PARTENARIAT, AUTO_FINANCEMENT
    }
    
    public enum StatutFormation {
        PLANIFIEE, EN_COURS, TERMINEE, ANNULEE, SUSPENDUE
    }
}
