package com.example.backend_unchk.modules.insertion.repository;

import com.example.backend_unchk.modules.insertion.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByEtudiantId(Long etudiantId);
    List<Stage> findByStatut(Stage.StatutStage statut);
    List<Stage> findByEntreprise(String entreprise);
    List<Stage> findByDateDebutBetween(LocalDate debut, LocalDate fin);
}
