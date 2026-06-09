package com.example.backend_unchk.modules.communication.repository;

import com.example.backend_unchk.modules.communication.entity.CompteRendu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompteRenduRepository extends JpaRepository<CompteRendu, Long> {
    List<CompteRendu> findByType(CompteRendu.TypeReunion type);
    List<CompteRendu> findByStatut(CompteRendu.StatutCompteRendu statut);
    List<CompteRendu> findByDateReunionBetween(LocalDate debut, LocalDate fin);
    List<CompteRendu> findByRedacteur(String redacteur);
    List<CompteRendu> findByNotificationEnvoyeeFalse();
}