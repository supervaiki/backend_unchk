package com.example.backend_unchk.modules.insertion.controller;

import com.example.backend_unchk.modules.insertion.entity.Partenaire;
import com.example.backend_unchk.modules.insertion.service.PartenaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/partenaires")
@RequiredArgsConstructor
public class PartenaireController {
    private final PartenaireService service;

    @PostMapping
    public ResponseEntity<Partenaire> createPartenaire(@RequestBody Partenaire partenaire) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createPartenaire(partenaire));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partenaire> getPartenaire(@PathVariable Long id) {
        Optional<Partenaire> partenaire = service.getPartenaire(id);
        return partenaire.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Partenaire>> getAllPartenaires() {
        return ResponseEntity.ok(service.getAllPartenaires());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Partenaire> getPartenaireByEmail(@PathVariable String email) {
        Optional<Partenaire> partenaire = service.getPartenaireByEmail(email);
        return partenaire.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{typePartenariat}")
    public ResponseEntity<List<Partenaire>> getPartenairesByType(@PathVariable String typePartenariat) {
        return ResponseEntity.ok(service.getPartenairesByType(typePartenariat));
    }

    @GetMapping("/actifs")
    public ResponseEntity<List<Partenaire>> getPartenairesActifs() {
        return ResponseEntity.ok(service.getPartenairesActifs());
    }

    @GetMapping("/secteur/{secteur}")
    public ResponseEntity<List<Partenaire>> getPartenairesBySecteur(@PathVariable String secteur) {
        return ResponseEntity.ok(service.getPartenairesBySecteur(secteur));
    }

    @PutMapping("/{id}/statut/{statut}")
    public ResponseEntity<Partenaire> updatePartenaireStatut(
            @PathVariable Long id,
            @PathVariable String statut) {
        return ResponseEntity.ok(service.updatePartenaireStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartenaire(@PathVariable Long id) {
        service.deletePartenaire(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics/actifs")
    public ResponseEntity<Long> countPartenairesActifs() {
        return ResponseEntity.ok(service.countPartenairesActifs());
    }
}
