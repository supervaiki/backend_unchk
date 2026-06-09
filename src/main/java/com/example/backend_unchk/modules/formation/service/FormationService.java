package com.example.backend_unchk.modules.formation.service;

import com.example.backend_unchk.modules.formation.dto.FormationDTO;
import com.example.backend_unchk.modules.formation.entity.Formation;
import com.example.backend_unchk.modules.formation.repository.FormationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FormationService {
    private final FormationRepository repository;

    public Formation createFormation(FormationDTO dto) {
        Formation formation = new Formation();
        formation.setTitre(dto.getTitre());
        formation.setDescription(dto.getDescription());
        formation.setDateDebut(dto.getDateDebut());
        formation.setDateFin(dto.getDateFin());
        formation.setType(Formation.TypeFormation.valueOf(dto.getType()));
        formation.setNiveau(Formation.NiveauFormation.valueOf(dto.getNiveau()));
        formation.setMontant(dto.getMontant());
        formation.setTypeFinancement(Formation.TypeFinancement.valueOf(dto.getTypeFinancement()));
        formation.setNombreFormes(dto.getNombreFormes());
        formation.setNombreFemmes(dto.getNombreFemmes());
        formation.setNombreHommes(dto.getNombreHommes());
        formation.setStatut(Formation.StatutFormation.PLANIFIEE);
        formation.setLieu(dto.getLieu());
        formation.setDateCreation(LocalDate.now());
        
        return repository.save(formation);
    }

    public Optional<Formation> getFormation(Long id) {
        return repository.findById(id);
    }

    public List<Formation> getAllFormations() {
        return repository.findAll();
    }

    public List<Formation> getFormationsByType(String type) {
        return repository.findByType(Formation.TypeFormation.valueOf(type));
    }

    public List<Formation> getFormationsEnCours() {
        return repository.findByStatut(Formation.StatutFormation.EN_COURS);
    }

    public List<Formation> getFormationsByNiveau(String niveau) {
        return repository.findByNiveau(Formation.NiveauFormation.valueOf(niveau));
    }

    public Formation updateFormationStatut(Long id, String statut) {
        Optional<Formation> formation = repository.findById(id);
        if (formation.isPresent()) {
            Formation f = formation.get();
            f.setStatut(Formation.StatutFormation.valueOf(statut));
            f.setDateModification(LocalDate.now());
            return repository.save(f);
        }
        throw new RuntimeException("Formation non trouvée");
    }

    public void deleteFormation(Long id) {
        repository.deleteById(id);
    }

    public long countFormationsEnCours() {
        return getFormationsEnCours().size();
    }
}
