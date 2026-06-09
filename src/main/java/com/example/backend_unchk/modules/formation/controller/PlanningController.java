package com.example.backend_unchk.modules.formation.controller;

import com.example.backend_unchk.modules.formation.entity.Planning;
import com.example.backend_unchk.modules.formation.service.PlanningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/plannings")
@RequiredArgsConstructor
public class PlanningController {
    private final PlanningService service;

    @PostMapping
    public ResponseEntity<Planning> createPlanning(@RequestBody Planning planning) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createPlanning(planning));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planning> getPlanning(@PathVariable Long id) {
        Optional<Planning> planning = service.getPlanning(id);
        return planning.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Planning>> getAllPlannings() {
        return ResponseEntity.ok(service.getAllPlannings());
    }

    @GetMapping("/formation/{formationId}")
    public ResponseEntity<List<Planning>> getPlanningsByFormation(@PathVariable Long formationId) {
        return ResponseEntity.ok(service.getPlanningsByFormation(formationId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Planning>> getPlanningsByDate(@PathVariable LocalDate date) {
        return ResponseEntity.ok(service.getPlanningsByDate(date));
    }

    @GetMapping("/periode")
    public ResponseEntity<List<Planning>> getPlanningsByPeriode(
            @RequestParam LocalDate debut,
            @RequestParam LocalDate fin) {
        return ResponseEntity.ok(service.getPlanningsByPeriode(debut, fin));
    }

    @GetMapping("/type/{typeActivite}")
    public ResponseEntity<List<Planning>> getPlanningsByTypeActivite(@PathVariable String typeActivite) {
        return ResponseEntity.ok(service.getPlanningsByTypeActivite(typeActivite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Planning> updatePlanning(
            @PathVariable Long id,
            @RequestBody Planning planning) {
        return ResponseEntity.ok(service.updatePlanning(id, planning));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanning(@PathVariable Long id) {
        service.deletePlanning(id);
        return ResponseEntity.noContent().build();
    }
}
