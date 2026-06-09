package com.example.backend_unchk.modules.administration.repository;

import com.example.backend_unchk.modules.administration.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Optional<Personnel> findByEmail(String email);
    List<Personnel> findByCategorie(Personnel.CategoriePersonnel categorie);
    List<Personnel> findByStatut(Personnel.StatutPersonnel statut);
    List<Personnel> findByDepartement(String departement);
    List<Personnel> findByNomContainingIgnoreCase(String nom);
}
