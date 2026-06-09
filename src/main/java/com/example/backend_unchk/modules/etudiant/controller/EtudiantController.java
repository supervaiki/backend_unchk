package com.example.backend_unchk.modules.etudiant.controller;

import com.example.backend_unchk.modules.etudiant.dto.EtudiantRequestDTO;
import com.example.backend_unchk.modules.etudiant.entity.Etudiant;
import com.example.backend_unchk.modules.etudiant.service.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/etudiants")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantService service;

    @PostMapping
    public ResponseEntity<Etudiant> createEtudiant(@RequestBody EtudiantRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createEtudiant(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiant(@PathVariable Long id) {
        Optional<Etudiant> etudiant = service.getEtudiant(id);
        return etudiant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        return ResponseEntity.ok(service.getAllEtudiants());
    }

    @GetMapping("/matricule/{numeroMatricule}")
    public ResponseEntity<Etudiant> getEtudiantByMatricule(@PathVariable String numeroMatricule) {
        Optional<Etudiant> etudiant = service.getEtudiantByMatricule(numeroMatricule);
        return etudiant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Etudiant> getEtudiantByEmail(@PathVariable String email) {
        Optional<Etudiant> etudiant = service.getEtudiantByEmail(email);
        return etudiant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/actifs")
    public ResponseEntity<List<Etudiant>> getEtudiantsActifs() {
        return ResponseEntity.ok(service.getEtudiantsActifs());
    }

    @GetMapping("/niveau/{niveau}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByNiveau(@PathVariable Integer niveau) {
        return ResponseEntity.ok(service.getEtudiantsByNiveau(niveau));
    }

    @GetMapping("/parcours/{parcours}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByParcours(@PathVariable String parcours) {
        return ResponseEntity.ok(service.getEtudiantsByParcours(parcours));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(
            @PathVariable Long id,
            @RequestBody EtudiantRequestDTO dto) {
        return ResponseEntity.ok(service.updateEtudiant(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        service.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics/actifs")
    public ResponseEntity<Long> countEtudiantsActifs() {
        return ResponseEntity.ok(service.countEtudiantsActifs());
    }
}