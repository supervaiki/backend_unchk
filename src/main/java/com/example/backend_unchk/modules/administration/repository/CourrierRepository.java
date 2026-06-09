package com.example.backend_unchk.modules.administration.repository;

import com.example.backend_unchk.modules.administration.entity.Courrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourrierRepository extends JpaRepository<Courrier, Long> {
    List<Courrier> findByType(Courrier.TypeCourrier type);
    List<Courrier> findByStatut(Courrier.StatutCourrier statut);
    List<Courrier> findByDateReceptionBetween(LocalDate debut, LocalDate fin);
    List<Courrier> findByExpediteurContaining(String expediteur);
}