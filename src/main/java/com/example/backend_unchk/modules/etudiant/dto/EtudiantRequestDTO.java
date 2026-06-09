package com.example.backend_unchk.modules.etudiant.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EtudiantRequestDTO {
    private String ine;
    private String nom;
    private String prenom;
    private String numeroMatricule;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
    private String sexe;
    private String adresse;
    private String statut;
    private String parcours;
    private String formation;
    private String promo;
    private Integer anneeDebut;
    private Integer niveau;
}