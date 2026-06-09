package com.example.backend_unchk.modules.etudiant.entity;

import com.example.backend_unchk.core.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "etudiants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Etudiant extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String ine;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true)
    private String numeroMatricule;

    @Column(unique = true)
    private String email;

    private String telephone;

    private LocalDate dateNaissance;

    private String sexe;

    private String adresse;

    @Enumerated(EnumType.STRING)
    private StatutEtudiant statut;

    private String formation;

    private String promo;

    private Integer anneeDebut;

    private Integer anneeSortie;

    private String parcours;

    private Integer niveau;

    private LocalDate dateInscription;

    private LocalDate dateGraduation;

    @Column(columnDefinition = "TEXT")
    private String diplomes;

    @Column(columnDefinition = "TEXT")
    private String autresFormations;

    public enum StatutEtudiant {
        ACTIF,
        INACTIF,
        SUSPENDU,
        DIPLOME,
        ABANDONNE
    }
}