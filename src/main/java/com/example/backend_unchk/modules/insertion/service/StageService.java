package com.example.backend_unchk.modules.insertion.service;

import com.example.backend_unchk.modules.insertion.dto.StageDTO;
import com.example.backend_unchk.modules.insertion.entity.Stage;
import com.example.backend_unchk.modules.insertion.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StageService {
    private final StageRepository repository;

    public Stage createStage(StageDTO dto) {
        Stage stage = new Stage();
        stage.setEtudiantId(dto.getEtudiantId());
        stage.setDateDebut(dto.getDateDebut());
        stage.setDateFin(dto.getDateFin());
        stage.setEntreprise(dto.getEntreprise());
        stage.setPosteOccupe(dto.getPosteOccupe());
        stage.setDescriptifTaches(dto.getDescriptifTaches());
        stage.setStatut(Stage.StatutStage.EN_COURS);
        stage.setDateCreation(LocalDate.now());
        
        return repository.save(stage);
    }

    public Optional<Stage> getStage(Long id) {
        return repository.findById(id);
    }

    public List<Stage> getAllStages() {
        return repository.findAll();
    }

    public List<Stage> getStagesByEtudiant(Long etudiantId) {
        return repository.findByEtudiantId(etudiantId);
    }

    public List<Stage> getStagesByStatut(String statut) {
        return repository.findByStatut(Stage.StatutStage.valueOf(statut));
    }

    public List<Stage> getStagesByEntreprise(String entreprise) {
        return repository.findByEntreprise(entreprise);
    }

    public Stage completeStage(Long id, StageDTO dto) {
        Optional<Stage> stage = repository.findById(id);
        if (stage.isPresent()) {
            Stage s = stage.get();
            s.setStatut(Stage.StatutStage.TERMINE);
            s.setBilanStage(dto.getBilanStage());
            s.setNoteEvaluation(dto.getNoteEvaluation());
            s.setDateModification(LocalDate.now());
            return repository.save(s);
        }
        throw new RuntimeException("Stage non trouvé");
    }

    public void deleteStage(Long id) {
        repository.deleteById(id);
    }

    public long countStagesTermines() {
        return getStagesByStatut("TERMINE").size();
    }
}
