package marieteam.marieteam_v2;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class PDFGenerator {
    private String boatName;
    private String boatLongueur;
    private String boatLargeur;
    private String boatEquipements;

    public PDFGenerator(String boatName, String boatLongueur, String boatLargeur, String boatEquipements) {
        this.boatName = boatName;
        this.boatLongueur = boatLongueur;
        this.boatLargeur = boatLargeur;
        this.boatEquipements = boatEquipements;
    }

    public void PDFGenerator(String boatName, String boatLongueur, String boatLargeur, String boatEquipements) {
        // Créer un document PDF
        PDDocument document = new PDDocument();
        try {
            System.out.println("PDFGenerator");
            // Créer une nouvelle page
            PDPage page = new PDPage();
            document.addPage(page);

            // Créer un flux de contenu pour ajouter du texte à la page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Définir la police et la taille du texte
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750); // Position du texte

            // Ajouter les informations du bateau avec des sauts de ligne pour la lisibilité
            contentStream.showText("Nom du Bateau: " + boatName);
            contentStream.newLine();
            contentStream.newLine();  // Saut de ligne pour séparer
            contentStream.showText("Longueur: " + boatLongueur + "m");
            contentStream.newLine();
            contentStream.newLine();  // Saut de ligne pour séparer
            contentStream.showText("Largeur: " + boatLargeur + "m");
            contentStream.newLine();
            contentStream.newLine();  // Saut de ligne pour séparer
            contentStream.showText("Équipements: " + boatEquipements);
            contentStream.newLine();

            // Fermer le flux de contenu
            contentStream.endText();
            contentStream.close();

            // Sauvegarder le document PDF
            document.save(new File("Bateau_" + boatName + ".pdf"));
            document.close();

            System.out.println("PDF généré avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
