package com.example.backend_unchk.core.util;

import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Component
public class ExcelExportUtil {

    private static final String EXCEL_UPLOAD_DIR = "uploads/excel";

    /**
     * Exporte une liste de données en fichier Excel
     * @param headers en-têtes de colonnes
     * @param data données à exporter
     * @param filename nom du fichier
     * @return chemin du fichier créé
     */
    public String exportToExcel(List<String> headers, List<Map<String, Object>> data, String filename) {
        try {
            // Créer le répertoire s'il n'existe pas
            Files.createDirectories(Paths.get(EXCEL_UPLOAD_DIR));
            
            // TODO: Implémenter l'export Excel avec une librairie comme Apache POI
            String filePath = EXCEL_UPLOAD_DIR + "/" + filename + ".xlsx";
            Files.createFile(Paths.get(filePath));
            
            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'export Excel: " + e.getMessage());
        }
    }

    /**
     * Exporte les formations en fichier Excel
     * @param formations données des formations
     * @param filename nom du fichier
     * @return chemin du fichier créé
     */
    public String exportFormationsToExcel(List<Map<String, Object>> formations, String filename) {
        List<String> headers = List.of(
                "ID", "Titre", "Type", "Niveau", "Montant", 
                "Nombre de Formés", "Femmes", "Hommes", "Statut"
        );
        return exportToExcel(headers, formations, filename);
    }

    /**
     * Exporte les étudiants en fichier Excel
     * @param etudiants données des étudiants
     * @param filename nom du fichier
     * @return chemin du fichier créé
     */
    public String exportEtudiantsToExcel(List<Map<String, Object>> etudiants, String filename) {
        List<String> headers = List.of(
                "ID", "Nom", "Prénom", "Matricule", "Email", 
                "Niveau", "Parcours", "Statut", "Date Inscription"
        );
        return exportToExcel(headers, etudiants, filename);
    }
}
