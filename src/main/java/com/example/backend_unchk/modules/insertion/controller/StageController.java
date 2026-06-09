package com.example.backend_unchk.modules.insertion.controller;

import com.example.backend_unchk.modules.insertion.dto.StageDTO;
import com.example.backend_unchk.modules.insertion.entity.Stage;
import com.example.backend_unchk.modules.insertion.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stages")
@RequiredArgsConstructor
public class StageController {
    private final StageService service;

    @PostMapping
    public ResponseEntity<Stage> createStage(@RequestBody StageDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createStage(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStage(@PathVariable Long id) {
        Optional<Stage> stage = service.getStage(id);
        return stage.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Stage>> getAllStages() {
        return ResponseEntity.ok(service.getAllStages());
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Stage>> getStagesByEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(service.getStagesByEtudiant(etudiantId));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Stage>> getStagesByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(service.getStagesByStatut(statut));
    }

    @GetMapping("/entreprise/{entreprise}")
    public ResponseEntity<List<Stage>> getStagesByEntreprise(@PathVariable String entreprise) {
        return ResponseEntity.ok(service.getStagesByEntreprise(entreprise));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Stage> completeStage(
            @PathVariable Long id,
            @RequestBody StageDTO dto) {
        return ResponseEntity.ok(service.completeStage(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        service.deleteStage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics/termines")
    public ResponseEntity<Long> countStagesTermines() {
        return ResponseEntity.ok(service.countStagesTermines());
    }
}
