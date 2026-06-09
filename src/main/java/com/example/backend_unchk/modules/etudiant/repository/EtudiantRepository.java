package com.example.backend_unchk.modules.etudiant.repository;

import com.example.backend_unchk.modules.etudiant.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByIne(String ine);
    Optional<Etudiant> findByNumeroMatricule(String numeroMatricule);
    Optional<Etudiant> findByEmail(String email);
    List<Etudiant> findByStatut(Etudiant.StatutEtudiant statut);
    List<Etudiant> findByNiveau(Integer niveau);
    List<Etudiant> findByParcours(String parcours);
}