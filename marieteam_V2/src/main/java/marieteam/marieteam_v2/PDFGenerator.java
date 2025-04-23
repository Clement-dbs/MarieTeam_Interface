package marieteam.marieteam_v2;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class PDFGenerator {

    private String boatNameImage;
    private String boatName;
    private String boatLongueur;
    private String boatLargeur;
    private String boatEquipements;

    public PDFGenerator(String boatNameImage, String boatName, String boatLongueur, String boatLargeur, String boatEquipements) {
        this.boatNameImage = boatNameImage;
        this.boatName = boatName;
        this.boatLongueur = boatLongueur;
        this.boatLargeur = boatLargeur;
        this.boatEquipements = boatEquipements;
    }

    public void generatePDF(String boatNameImage, String boatName, String boatLongueur, String boatLargeur, String boatEquipements) {
        // Créer un document PDF
        PDDocument document = new PDDocument();
        try {
            System.out.println("PDFGenerator");

            // Créer une nouvelle page
            PDPage page = new PDPage();
            document.addPage(page);

            // Créer un flux de contenu pour ajouter du texte à la page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Ajouter l'image du bateau
            File imageFile = new File("C:\\Users\\cleme\\Desktop\\MarieteamAPI\\src\\main\\resources\\Assets\\BoatsImages\\"+boatNameImage+".jpg");  // chemin de l'image du bateau
            PDImageXObject pdImage = PDImageXObject.createFromFile(imageFile.getAbsolutePath(), document);

            // Dessiner l'image dans le PDF
            float imageX = 50;  // Position X de l'image
            float imageY = 500; // Position Y de l'image
            float imageWidth = 200; // Largeur de l'image
            float imageHeight = 150; // Hauteur de l'image
            contentStream.drawImage(pdImage, imageX, imageY, imageWidth, imageHeight);

            // Définir la police et la taille du texte
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750); // Position du texte

            // Ajouter les informations du bateau avec des sauts de ligne pour la lisibilité
            contentStream.showText("Nom du Bateau: " + boatName);
            contentStream.newLineAtOffset(0, -20); // Décalage vertical pour la ligne suivante
            contentStream.showText("Longueur: " + boatLongueur + "m");
            contentStream.newLineAtOffset(0, -20); // Décalage vertical pour la ligne suivante
            contentStream.showText("Largeur: " + boatLargeur + "m");
            contentStream.newLineAtOffset(0, -20); // Décalage vertical pour la ligne suivante
            contentStream.showText("Équipements: " + boatEquipements);

            // Fermer le flux de texte
            contentStream.endText();

            // Fermer le flux de contenu
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
