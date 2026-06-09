package com.example.backend_unchk.modules.formation.controller;

import com.example.backend_unchk.modules.formation.dto.FormationDTO;
import com.example.backend_unchk.modules.formation.entity.Formation;
import com.example.backend_unchk.modules.formation.service.FormationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/formations")
@RequiredArgsConstructor
public class FormationController {
    private final FormationService service;

    @PostMapping
    public ResponseEntity<Formation> createFormation(@RequestBody FormationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createFormation(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormation(@PathVariable Long id) {
        Optional<Formation> formation = service.getFormation(id);
        return formation.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Formation>> getAllFormations() {
        return ResponseEntity.ok(service.getAllFormations());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Formation>> getFormationsByType(@PathVariable String type) {
        return ResponseEntity.ok(service.getFormationsByType(type));
    }

    @GetMapping("/en-cours")
    public ResponseEntity<List<Formation>> getFormationsEnCours() {
        return ResponseEntity.ok(service.getFormationsEnCours());
    }

    @GetMapping("/niveau/{niveau}")
    public ResponseEntity<List<Formation>> getFormationsByNiveau(@PathVariable String niveau) {
        return ResponseEntity.ok(service.getFormationsByNiveau(niveau));
    }

    @PutMapping("/{id}/statut/{statut}")
    public ResponseEntity<Formation> updateFormationStatut(
            @PathVariable Long id,
            @PathVariable String statut) {
        return ResponseEntity.ok(service.updateFormationStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        service.deleteFormation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics/en-cours")
    public ResponseEntity<Long> countFormationsEnCours() {
        return ResponseEntity.ok(service.countFormationsEnCours());
    }
}
