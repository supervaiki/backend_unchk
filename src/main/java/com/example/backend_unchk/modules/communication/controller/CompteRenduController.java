package com.example.backend_unchk.modules.communication.controller;

import com.example.backend_unchk.modules.communication.dto.CompteRenduDTO;
import com.example.backend_unchk.modules.communication.entity.CompteRendu;
import com.example.backend_unchk.modules.communication.service.CompteRenduService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comptes-rendus")
@RequiredArgsConstructor
public class CompteRenduController {

    private final CompteRenduService service;

    @PostMapping
    public ResponseEntity<CompteRendu> createCompteRendu(@RequestBody CompteRenduDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createCompteRendu(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteRendu> getCompteRendu(@PathVariable Long id) {
        Optional<CompteRendu> compteRendu = service.getCompteRendu(id);
        return compteRendu.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CompteRendu>> getAllComptesRendus() {
        return ResponseEntity.ok(service.getAllComptesRendus());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<CompteRendu>> getCompteRendusByType(@PathVariable String type) {
        return ResponseEntity.ok(service.getCompteRendusByType(type));
    }

    @GetMapping("/publies")
    public ResponseEntity<List<CompteRendu>> getComptesRendusPublies() {
        return ResponseEntity.ok(service.getComptesRendusPublies());
    }

    @GetMapping("/periode")
    public ResponseEntity<List<CompteRendu>> getComptesRendusParPeriode(
            @RequestParam LocalDate debut,
            @RequestParam LocalDate fin) {
        return ResponseEntity.ok(service.getComptesRendusParPeriode(debut, fin));
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<CompteRendu> publishCompteRendu(
            @PathVariable Long id,
            @RequestParam String approuvePar) {
        return ResponseEntity.ok(service.publishCompteRendu(id, approuvePar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompteRendu(@PathVariable Long id) {
        service.deleteCompteRendu(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/notifications/non-envoyees")
    public ResponseEntity<List<CompteRendu>> getComptesRendusSansNotification() {
        return ResponseEntity.ok(service.getComptesRendusSansNotification());
    }
}