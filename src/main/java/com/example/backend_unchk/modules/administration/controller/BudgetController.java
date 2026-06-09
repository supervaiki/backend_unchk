package com.example.backend_unchk.modules.administration.controller;

import com.example.backend_unchk.modules.administration.dto.BudgetRequestDTO;
import com.example.backend_unchk.modules.administration.entity.Budget;
import com.example.backend_unchk.modules.administration.service.BudgetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/budgets") // Assurez-vous que cette route est gérée dans votre SecurityConfig
@RequiredArgsConstructor
// 1. Sécurité : On restreint l'accès à ce contrôleur d'administration aux seuls administrateurs
@PreAuthorize("hasRole('ADMIN')")
public class BudgetController {

    private final BudgetService service;

    @PostMapping
    public ResponseEntity<Budget> createBudget(
            // 2. Validation : On s'assure que le DTO respecte les règles définies (ex: @NotNull, @Min)
            @Valid @RequestBody BudgetRequestDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createBudget(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudget(@PathVariable Long id) {
        // La gestion via Optional est correcte
        return service.getBudget(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets() {
        return ResponseEntity.ok(service.getAllBudgets());
    }

    @GetMapping("/annee/{annee}")
    public ResponseEntity<List<Budget>> getBudgetsByAnnee(@PathVariable Integer annee) {
        return ResponseEntity.ok(service.getBudgetsByAnnee(annee));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Budget>> getBudgetsByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(service.getBudgetsByStatut(statut));
    }

    // 3. Sémantique HTTP : @PatchMapping est plus adapté pour une mise à jour partielle (juste le statut)
    @PatchMapping("/{id}/statut/{statut}")
    public ResponseEntity<Budget> updateBudgetStatut(
            @PathVariable Long id,
            @PathVariable String statut) {
        return ResponseEntity.ok(service.updateBudgetStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        service.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total/{annee}")
    public ResponseEntity<BigDecimal> getTotalBudgetParAnnee(@PathVariable Integer annee) {
        return ResponseEntity.ok(service.getTotalBudgetParAnnee(annee));
    }
}