package com.example.backend_unchk.modules.formation.service;

import com.example.backend_unchk.modules.formation.entity.Planning;
import com.example.backend_unchk.modules.formation.repository.PlanningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanningService {
    private final PlanningRepository repository;

    public Planning createPlanning(Planning planning) {
        planning.setDateCreation(LocalDate.now());
        return repository.save(planning);
    }

    public Optional<Planning> getPlanning(Long id) {
        return repository.findById(id);
    }

    public List<Planning> getAllPlannings() {
        return repository.findAll();
    }

    public List<Planning> getPlanningsByFormation(Long formationId) {
        return repository.findByFormationId(formationId);
    }

    public List<Planning> getPlanningsByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public List<Planning> getPlanningsByPeriode(LocalDate debut, LocalDate fin) {
        return repository.findByDateBetween(debut, fin);
    }

    public List<Planning> getPlanningsByTypeActivite(String typeActivite) {
        return repository.findByTypeActivite(Planning.TypeActivite.valueOf(typeActivite));
    }

    public Planning updatePlanning(Long id, Planning planning) {
        Optional<Planning> existing = repository.findById(id);
        if (existing.isPresent()) {
            Planning p = existing.get();
            p.setDate(planning.getDate());
            p.setHeureDebut(planning.getHeureDebut());
            p.setHeureFin(planning.getHeureFin());
            p.setSujet(planning.getSujet());
            p.setFormateur(planning.getFormateur());
            p.setDateModification(LocalDate.now());
            return repository.save(p);
        }
        throw new RuntimeException("Planning non trouvé");
    }

    public void deletePlanning(Long id) {
        repository.deleteById(id);
    }
}
