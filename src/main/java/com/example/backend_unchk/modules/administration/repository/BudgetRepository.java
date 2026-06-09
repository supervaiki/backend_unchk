package com.example.backend_unchk.modules.administration.repository;

import com.example.backend_unchk.modules.administration.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByAnnee(Integer annee);
    List<Budget> findByType(Budget.TypeBudget type);
    List<Budget> findByStatut(Budget.StatutBudget statut);
    List<Budget> findByAnneeOrderByMontantDesc(Integer annee);
}
