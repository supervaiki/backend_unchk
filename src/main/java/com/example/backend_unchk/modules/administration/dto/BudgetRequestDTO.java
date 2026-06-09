package com.example.backend_unchk.modules.administration.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BudgetRequestDTO {
    private String designation;
    private String type;
    private BigDecimal montant;
    private Integer annee;
    private String description;
    private String statut;
}
