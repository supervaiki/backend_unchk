package com.example.backend_unchk.modules.insertion.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StageDTO {
    private Long id;
    private Long etudiantId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String entreprise;
    private String posteOccupe;
    private String descriptifTaches;
    private String statut;
    private String bilanStage;
    private Double noteEvaluation;
    private String evaluateurEntreprise;
    private String evaluateurEcole;
}
