package com.example.backend_unchk.modules.formation.repository;

import com.example.backend_unchk.modules.formation.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByType(Formation.TypeFormation type);
    List<Formation> findByStatut(Formation.StatutFormation statut);
    List<Formation> findByNiveau(Formation.NiveauFormation niveau);
    List<Formation> findByTitreContaining(String titre);
}
