package com.example.backend_unchk.core.util;

import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class PdfGeneratorUtil {

    private static final String PDF_UPLOAD_DIR = "uploads/pdf";

    /**
     * Génère un fichier PDF à partir d'un contenu HTML
     * @param content contenu HTML
     * @param filename nom du fichier
     * @return chemin du fichier créé
     */
    public String generatePdfFromHtml(String content, String filename) {
        try {
            // Créer le répertoire s'il n'existe pas
            Files.createDirectories(Paths.get(PDF_UPLOAD_DIR));
            
            // TODO: Implémenter la génération de PDF avec une librairie comme iText ou Apache PDFBox
            String filePath = PDF_UPLOAD_DIR + "/" + filename + ".pdf";
            Files.createFile(Paths.get(filePath));
            
            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF: " + e.getMessage());
        }
    }

    /**
     * Génère un PDF à partir d'un compte rendu
     * @param titre titre du document
     * @param contenu contenu du document
     * @param redacteur nom du rédacteur
     * @return chemin du fichier créé
     */
    public String generateCompteRenduPdf(String titre, String contenu, String redacteur) {
        String htmlContent = String.format(
                "<html><head><title>%s</title></head><body>" +
                "<h1>%s</h1><p>%s</p><p>Rédigé par: %s</p>" +
                "</body></html>",
                titre, titre, contenu, redacteur
        );
        return generatePdfFromHtml(htmlContent, titre);
    }
}
