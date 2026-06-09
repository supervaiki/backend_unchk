package com.example.backend_unchk.modules.etudiant.service;

import com.example.backend_unchk.modules.etudiant.dto.EtudiantRequestDTO;
import com.example.backend_unchk.modules.etudiant.entity.Etudiant;

import java.util.List;
import java.util.Optional;

public interface EtudiantService {
    Etudiant createEtudiant(EtudiantRequestDTO dto);

    Optional<Etudiant> getEtudiant(Long id);

    List<Etudiant> getAllEtudiants();

    Optional<Etudiant> getEtudiantByMatricule(String numeroMatricule);

    Optional<Etudiant> getEtudiantByEmail(String email);

    List<Etudiant> getEtudiantsActifs();

    List<Etudiant> getEtudiantsByNiveau(Integer niveau);

    List<Etudiant> getEtudiantsByParcours(String parcours);

    Etudiant updateEtudiant(Long id, EtudiantRequestDTO dto);

    void deleteEtudiant(Long id);

    long countEtudiantsActifs();
}