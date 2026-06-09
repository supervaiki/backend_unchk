package com.example.backend_unchk.modules.administration.controller;

import com.example.backend_unchk.modules.administration.entity.Personnel;
import com.example.backend_unchk.modules.administration.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/personnels")
@RequiredArgsConstructor
public class PersonnelController {

    private final PersonnelService service;

    @PostMapping
    public ResponseEntity<Personnel> createPersonnel(@RequestBody Personnel personnel) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createPersonnel(personnel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personnel> getPersonnel(@PathVariable Long id) {
        Optional<Personnel> personnel = service.getPersonnel(id);
        return personnel.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Personnel>> getAllPersonnels() {
        return ResponseEntity.ok(service.getAllPersonnels());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Personnel> getPersonnelByEmail(@PathVariable String email) {
        Optional<Personnel> personnel = service.getPersonnelByEmail(email);
        return personnel.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Personnel>> getPersonnelsByCategorie(@PathVariable String categorie) {
        return ResponseEntity.ok(service.getPersonnelsByCategorie(categorie));
    }

    @GetMapping("/actifs")
    public ResponseEntity<List<Personnel>> getPersonnelsActifs() {
        return ResponseEntity.ok(service.getPersonnelsActifs());
    }

    @GetMapping("/departement/{departement}")
    public ResponseEntity<List<Personnel>> getPersonnelsByDepartement(@PathVariable String departement) {
        return ResponseEntity.ok(service.getPersonnelsByDepartement(departement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personnel> updatePersonnel(
            @PathVariable Long id,
            @RequestBody Personnel personnel) {
        return ResponseEntity.ok(service.updatePersonnel(id, personnel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        service.deletePersonnel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics/count-actifs")
    public ResponseEntity<Long> countPersonnelsActifs() {
        return ResponseEntity.ok(service.countPersonnelsActifs());
    }
}
