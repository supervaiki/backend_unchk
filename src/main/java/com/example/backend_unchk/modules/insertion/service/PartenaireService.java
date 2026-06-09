package com.example.backend_unchk.modules.insertion.service;

import com.example.backend_unchk.modules.insertion.entity.Partenaire;
import com.example.backend_unchk.modules.insertion.repository.PartenaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PartenaireService {
    private final PartenaireRepository repository;

    public Partenaire createPartenaire(Partenaire partenaire) {
        partenaire.setDateCreation(LocalDate.now());
        partenaire.setStatut(Partenaire.StatutPartenaire.EN_ATTENTE);
        return repository.save(partenaire);
    }

    public Optional<Partenaire> getPartenaire(Long id) {
        return repository.findById(id);
    }

    public List<Partenaire> getAllPartenaires() {
        return repository.findAll();
    }

    public Optional<Partenaire> getPartenaireByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Partenaire> getPartenairesByType(String typePartenariat) {
        return repository.findByTypePartenariat(Partenaire.TypePartenariat.valueOf(typePartenariat));
    }

    public List<Partenaire> getPartenairesActifs() {
        return repository.findByStatut(Partenaire.StatutPartenaire.ACTIF);
    }

    public List<Partenaire> getPartenairesBySecteur(String secteur) {
        return repository.findBySecteurActiviteContaining(secteur);
    }

    public Partenaire updatePartenaireStatut(Long id, String statut) {
        Optional<Partenaire> partenaire = repository.findById(id);
        if (partenaire.isPresent()) {
            Partenaire p = partenaire.get();
            p.setStatut(Partenaire.StatutPartenaire.valueOf(statut));
            p.setDateModification(LocalDate.now());
            return repository.save(p);
        }
        throw new RuntimeException("Partenaire non trouvé");
    }

    public void deletePartenaire(Long id) {
        repository.deleteById(id);
    }

    public long countPartenairesActifs() {
        return getPartenairesActifs().size();
    }
}
