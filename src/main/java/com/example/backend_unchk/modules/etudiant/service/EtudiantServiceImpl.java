package com.example.backend_unchk.modules.etudiant.service;

import com.example.backend_unchk.modules.etudiant.dto.EtudiantRequestDTO;
import com.example.backend_unchk.modules.etudiant.dto.EtudiantResponseDTO;
import com.example.backend_unchk.modules.etudiant.entity.Etudiant;
import com.example.backend_unchk.modules.etudiant.repository.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;

    @Override
    public Etudiant createEtudiant(EtudiantRequestDTO dto) {
        Etudiant etudiant = new Etudiant();
        etudiant.setIne(dto.getIne());
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setNumeroMatricule(dto.getNumeroMatricule());
        etudiant.setEmail(dto.getEmail());
        etudiant.setTelephone(dto.getTelephone());
        etudiant.setDateNaissance(dto.getDateNaissance());
        etudiant.setFormation(dto.getFormation());
        etudiant.setPromo(dto.getPromo());
        etudiant.setAnneeDebut(dto.getAnneeDebut());
        etudiant.setDateInscription(LocalDate.now());

        return etudiantRepository.save(etudiant);
    }

    @Override
    public Optional<Etudiant> getEtudiant(Long id) {
        return etudiantRepository.findById(id);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Optional<Etudiant> getEtudiantByMatricule(String numeroMatricule) {
        return etudiantRepository.findByNumeroMatricule(numeroMatricule);
    }

    @Override
    public Optional<Etudiant> getEtudiantByEmail(String email) {
        return etudiantRepository.findByEmail(email);
    }

    @Override
    public List<Etudiant> getEtudiantsActifs() {
        return etudiantRepository.findByStatut(Etudiant.StatutEtudiant.ACTIF);
    }

    @Override
    public List<Etudiant> getEtudiantsByNiveau(Integer niveau) {
        return etudiantRepository.findByNiveau(niveau);
    }

    @Override
    public List<Etudiant> getEtudiantsByParcours(String parcours) {
        return etudiantRepository.findByParcours(parcours);
    }

    @Override
    public Etudiant updateEtudiant(Long id, EtudiantRequestDTO dto) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        if (etudiant.isPresent()) {
            Etudiant e = etudiant.get();
            if (dto.getIne() != null) e.setIne(dto.getIne());
            if (dto.getNom() != null) e.setNom(dto.getNom());
            if (dto.getPrenom() != null) e.setPrenom(dto.getPrenom());
            if (dto.getDateNaissance() != null) e.setDateNaissance(dto.getDateNaissance());
            if (dto.getFormation() != null) e.setFormation(dto.getFormation());
            if (dto.getPromo() != null) e.setPromo(dto.getPromo());
            if (dto.getAnneeDebut() != null) e.setAnneeDebut(dto.getAnneeDebut());
            return etudiantRepository.save(e);
        }
        throw new RuntimeException("Etudiant non trouvé");
    }

    @Override
    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public long countEtudiantsActifs() {
        return getEtudiantsActifs().size();
    }

    private EtudiantResponseDTO mapToDTO(Etudiant etudiant) {
        EtudiantResponseDTO dto = new EtudiantResponseDTO();
        dto.setId(etudiant.getId());
        dto.setIne(etudiant.getIne());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setNumeroMatricule(etudiant.getNumeroMatricule());
        dto.setEmail(etudiant.getEmail());
        dto.setTelephone(etudiant.getTelephone());
        dto.setDateNaissance(etudiant.getDateNaissance());
        dto.setFormation(etudiant.getFormation());
        dto.setPromo(etudiant.getPromo());
        if (etudiant.getDateInscription() != null) {
            dto.setDateInscription(etudiant.getDateInscription());
        }
        return dto;
    }
}