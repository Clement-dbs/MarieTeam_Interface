package marieteam.marieteam_v2;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

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
        PDDocument document = new PDDocument();

        try {
            System.out.println("PDFGenerator");

            // Charger l'image du bateau depuis le classpath
            URL imageUrl = getClass().getResource("/Assets/BoatsImages/" + boatNameImage + ".jpg");
            if (imageUrl == null) {
                System.err.println("Image introuvable : " + boatNameImage + ".jpg");
                return;
            }

            File imageFile = new File(imageUrl.toURI());

            // Créer une nouvelle page
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Ajouter l'image
            PDImageXObject pdImage = PDImageXObject.createFromFile(imageFile.getAbsolutePath(), document);
            float imageX = 50;
            float imageY = 500;
            float imageWidth = 200;
            float imageHeight = 150;
            contentStream.drawImage(pdImage, imageX, imageY, imageWidth, imageHeight);

            // Texte
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("Nom du Bateau: " + boatName);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Longueur: " + boatLongueur + "m");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Largeur: " + boatLargeur + "m");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Équipements: " + boatEquipements);
            contentStream.endText();

            contentStream.close();

            document.save(new File("Bateau_" + boatName + ".pdf"));
            document.close();

            System.out.println("PDF généré avec succès.");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
