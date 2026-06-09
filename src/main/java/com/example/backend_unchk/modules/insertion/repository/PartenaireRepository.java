package com.example.backend_unchk.modules.insertion.repository;

import com.example.backend_unchk.modules.insertion.entity.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
    Optional<Partenaire> findByEmail(String email);
    List<Partenaire> findByTypePartenariat(Partenaire.TypePartenariat typePartenariat);
    List<Partenaire> findByStatut(Partenaire.StatutPartenaire statut);
    List<Partenaire> findBySecteurActiviteContaining(String secteur);
    List<Partenaire> findByNomEntrepriseContainingIgnoreCase(String nom);
}
