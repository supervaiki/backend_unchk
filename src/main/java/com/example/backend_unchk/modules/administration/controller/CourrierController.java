package com.example.backend_unchk.modules.administration.controller;

import com.example.backend_unchk.modules.administration.dto.CourrierRequestDTO;
import com.example.backend_unchk.modules.administration.entity.Courrier;
import com.example.backend_unchk.modules.administration.service.CourrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courriers")
@RequiredArgsConstructor
public class CourrierController {
    private final CourrierService service;

    @PostMapping
    public ResponseEntity<Courrier> createCourrier(@RequestBody CourrierRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createCourrier(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Courrier> getCourrier(@PathVariable Long id) {
        Optional<Courrier> courrier = service.getCourrier(id);
        return courrier.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Courrier>> getAllCourriers() {
        return ResponseEntity.ok(service.getAllCourriers());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Courrier>> getCourriersParType(@PathVariable String type) {
        return ResponseEntity.ok(service.getCourriersParType(type));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Courrier>> getCourriersParStatut(@PathVariable String statut) {
        return ResponseEntity.ok(service.getCourriersParStatut(statut));
    }

    @GetMapping("/periode")
    public ResponseEntity<List<Courrier>> getCourriersParPeriode(
            @RequestParam LocalDate debut,
            @RequestParam LocalDate fin) {
        return ResponseEntity.ok(service.getCourriersParPeriode(debut, fin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Courrier> updateCourrier(
            @PathVariable Long id,
            @RequestBody CourrierRequestDTO dto) {
        return ResponseEntity.ok(service.updateCourrier(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourrier(@PathVariable Long id) {
        service.deleteCourrier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics/en-attente")
    public ResponseEntity<Long> countCourriersEnAttente() {
        return ResponseEntity.ok(service.countCourriersEnAttente());
    }
}