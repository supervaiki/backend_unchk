package com.example.backend_unchk.modules.formation.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FormationDTO {
    private Long id;
    private String titre;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String type;
    private String niveau;
    private BigDecimal montant;
    private String typeFinancement;
    private Integer nombreFormes;
    private Integer nombreFemmes;
    private Integer nombreHommes;
    private String statut;
    private String lieu;
}
