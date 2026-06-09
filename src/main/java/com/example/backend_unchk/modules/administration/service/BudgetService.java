package com.example.backend_unchk.modules.administration.service;

import com.example.backend_unchk.modules.administration.dto.BudgetRequestDTO;
import com.example.backend_unchk.modules.administration.entity.Budget;
import com.example.backend_unchk.modules.administration.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BudgetService {
    private final BudgetRepository repository;

    public Budget createBudget(BudgetRequestDTO dto) {
        Budget budget = new Budget();
        budget.setDesignation(dto.getDesignation());
        budget.setType(Budget.TypeBudget.valueOf(dto.getType()));
        budget.setMontant(dto.getMontant());
        budget.setAnnee(dto.getAnnee());
        budget.setDescription(dto.getDescription());
        budget.setStatut(Budget.StatutBudget.EN_ATTENTE);
        budget.setDateCreation(LocalDate.now());
        
        return repository.save(budget);
    }

    public Optional<Budget> getBudget(Long id) {
        return repository.findById(id);
    }

    public List<Budget> getAllBudgets() {
        return repository.findAll();
    }

    public List<Budget> getBudgetsByAnnee(Integer annee) {
        return repository.findByAnnee(annee);
    }

    public List<Budget> getBudgetsByStatut(String statut) {
        return repository.findByStatut(Budget.StatutBudget.valueOf(statut));
    }

    public Budget updateBudgetStatut(Long id, String statut) {
        Optional<Budget> budget = repository.findById(id);
        if (budget.isPresent()) {
            Budget b = budget.get();
            b.setStatut(Budget.StatutBudget.valueOf(statut));
            if (statut.equals("APPROUVE")) {
                b.setDateApprobation(LocalDate.now());
            }
            b.setDateModification(LocalDate.now());
            return repository.save(b);
        }
        throw new RuntimeException("Budget non trouvé");
    }

    public void deleteBudget(Long id) {
        repository.deleteById(id);
    }

    public BigDecimal getTotalBudgetParAnnee(Integer annee) {
        return repository.findByAnneeOrderByMontantDesc(annee).stream()
                .map(Budget::getMontant)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
