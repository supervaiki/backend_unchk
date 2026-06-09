package com.example.backend_unchk.modules.communication.service;

import com.example.backend_unchk.modules.communication.dto.CompteRenduDTO;
import com.example.backend_unchk.modules.communication.entity.CompteRendu;
import com.example.backend_unchk.modules.communication.repository.CompteRenduRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompteRenduService {

    private final CompteRenduRepository repository;

    public CompteRendu createCompteRendu(CompteRenduDTO dto) {
        CompteRendu compteRendu = new CompteRendu();
        compteRendu.setTitre(dto.getTitre());
        compteRendu.setType(CompteRendu.TypeReunion.valueOf(dto.getType()));
        compteRendu.setContenu(dto.getContenu());
        compteRendu.setDateReunion(dto.getDateReunion());
        compteRendu.setHeureDebut(dto.getHeureDebut());
        compteRendu.setHeureFin(dto.getHeureFin());
        compteRendu.setLieu(dto.getLieu());
        compteRendu.setPointsTraites(dto.getPointsTraites());
        compteRendu.setDecisions(dto.getDecisions());
        compteRendu.setRecommandations(dto.getRecommandations());
        compteRendu.setStatut(CompteRendu.StatutCompteRendu.BROUILLON);
        compteRendu.setRedacteur(dto.getRedacteur());
        compteRendu.setDateCreation(LocalDateTime.now());
        
        return repository.save(compteRendu);
    }

    public Optional<CompteRendu> getCompteRendu(Long id) {
        return repository.findById(id);
    }

    public List<CompteRendu> getAllComptesRendus() {
        return repository.findAll();
    }

    public List<CompteRendu> getCompteRendusByType(String type) {
        return repository.findByType(CompteRendu.TypeReunion.valueOf(type));
    }

    public List<CompteRendu> getComptesRendusPublies() {
        return repository.findByStatut(CompteRendu.StatutCompteRendu.PUBLIE);
    }

    public List<CompteRendu> getComptesRendusParPeriode(LocalDate debut, LocalDate fin) {
        return repository.findByDateReunionBetween(debut, fin);
    }

    public CompteRendu publishCompteRendu(Long id, String approuvePar) {
        Optional<CompteRendu> compteRendu = repository.findById(id);
        if (compteRendu.isPresent()) {
            CompteRendu cr = compteRendu.get();
            cr.setStatut(CompteRendu.StatutCompteRendu.PUBLIE);
            cr.setApprouvePar(approuvePar);
            cr.setDateModification(LocalDateTime.now());
            return repository.save(cr);
        }
        throw new RuntimeException("Compte rendu non trouvé");
    }

    public void deleteCompteRendu(Long id) {
        repository.deleteById(id);
    }

    public List<CompteRendu> getComptesRendusSansNotification() {
        return repository.findByNotificationEnvoyeeFalse();
    }
}