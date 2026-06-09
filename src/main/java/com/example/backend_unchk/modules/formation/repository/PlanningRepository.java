package com.example.backend_unchk.modules.formation.repository;

import com.example.backend_unchk.modules.formation.entity.Planning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {
    List<Planning> findByFormationId(Long formationId);
    List<Planning> findByDate(LocalDate date);
    List<Planning> findByDateBetween(LocalDate debut, LocalDate fin);
    List<Planning> findByTypeActivite(Planning.TypeActivite typeActivite);
}
