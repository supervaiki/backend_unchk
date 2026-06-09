package com.example.backend_unchk.modules.administration.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CourrierRequestDTO {
    private String numero;
    private String type;
    private String objet;
    private String contenu;
    private LocalDate dateReception;
    private String expediteur;
    private String destinataire;
    private String statut;
    private Long personnelId;
}
