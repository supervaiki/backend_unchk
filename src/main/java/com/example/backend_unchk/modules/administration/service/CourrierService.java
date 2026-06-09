package com.example.backend_unchk.modules.administration.service;

import com.example.backend_unchk.modules.administration.dto.CourrierRequestDTO;
import com.example.backend_unchk.modules.administration.entity.Courrier;
import com.example.backend_unchk.modules.administration.repository.CourrierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CourrierService {
    private final CourrierRepository repository;

    public Courrier createCourrier(CourrierRequestDTO dto) {
        Courrier courrier = new Courrier();
        courrier.setNumero(dto.getNumero());
        courrier.setType(Courrier.TypeCourrier.valueOf(dto.getType()));
        courrier.setObjet(dto.getObjet());
        courrier.setContenu(dto.getContenu());
        courrier.setDateReception(dto.getDateReception());
        courrier.setExpediteur(dto.getExpediteur());
        courrier.setDestinataire(dto.getDestinataire());
        courrier.setStatut(Courrier.StatutCourrier.EN_ATTENTE);
        courrier.setDateCreation(LocalDate.now());
        
        return repository.save(courrier);
    }

    public Optional<Courrier> getCourrier(Long id) {
        return repository.findById(id);
    }

    public List<Courrier> getAllCourriers() {
        return repository.findAll();
    }

    public List<Courrier> getCourriersParType(String type) {
        return repository.findByType(Courrier.TypeCourrier.valueOf(type));
    }

    public List<Courrier> getCourriersParStatut(String statut) {
        return repository.findByStatut(Courrier.StatutCourrier.valueOf(statut));
    }

    public List<Courrier> getCourriersParPeriode(LocalDate debut, LocalDate fin) {
        return repository.findByDateReceptionBetween(debut, fin);
    }

    public Courrier updateCourrier(Long id, CourrierRequestDTO dto) {
        Optional<Courrier> courrier = repository.findById(id);
        if (courrier.isPresent()) {
            Courrier c = courrier.get();
            c.setStatut(Courrier.StatutCourrier.valueOf(dto.getStatut()));
            c.setDateTraitement(LocalDate.now());
            c.setDateModification(LocalDate.now());
            return repository.save(c);
        }
        throw new RuntimeException("Courrier non trouvé");
    }

    public void deleteCourrier(Long id) {
        repository.deleteById(id);
    }

    public long countCourriersEnAttente() {
        return getAllCourriers().stream()
                .filter(c -> c.getStatut() == Courrier.StatutCourrier.EN_ATTENTE)
                .count();
    }
}