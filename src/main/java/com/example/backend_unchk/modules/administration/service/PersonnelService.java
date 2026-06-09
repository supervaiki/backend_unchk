package com.example.backend_unchk.modules.administration.service;

import com.example.backend_unchk.modules.administration.entity.Personnel;
import com.example.backend_unchk.modules.administration.repository.PersonnelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonnelService {
    private final PersonnelRepository repository;

    public Personnel createPersonnel(Personnel personnel) {
        personnel.setDateCreation(LocalDate.now());
        return repository.save(personnel);
    }

    public Optional<Personnel> getPersonnel(Long id) {
        return repository.findById(id);
    }

    public List<Personnel> getAllPersonnels() {
        return repository.findAll();
    }

    public Optional<Personnel> getPersonnelByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Personnel> getPersonnelsByCategorie(String categorie) {
        return repository.findByCategorie(Personnel.CategoriePersonnel.valueOf(categorie));
    }

    public List<Personnel> getPersonnelsActifs() {
        return repository.findByStatut(Personnel.StatutPersonnel.ACTIF);
    }

    public List<Personnel> getPersonnelsByDepartement(String departement) {
        return repository.findByDepartement(departement);
    }

    public Personnel updatePersonnel(Long id, Personnel personnel) {
        Optional<Personnel> existing = repository.findById(id);
        if (existing.isPresent()) {
            Personnel p = existing.get();
            p.setNom(personnel.getNom());
            p.setPrenom(personnel.getPrenom());
            p.setFonction(personnel.getFonction());
            p.setStatut(personnel.getStatut());
            p.setDateModification(LocalDate.now());
            return repository.save(p);
        }
        throw new RuntimeException("Personnel non trouvé");
    }

    public void deletePersonnel(Long id) {
        repository.deleteById(id);
    }

    public long countPersonnelsActifs() {
        return getPersonnelsActifs().size();
    }
}
