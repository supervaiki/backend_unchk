package com.example.backend_unchk.modules.communication.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CompteRenduDTO {
    private Long id;
    private String titre;
    private String type;
    private String contenu;
    private LocalDate dateReunion;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private String lieu;
    private String pointsTraites;
    private String decisions;
    private String recommandations;
    private String statut;
    private String redacteur;
    private String approuvePar;
    private Boolean notificationEnvoyee;
}