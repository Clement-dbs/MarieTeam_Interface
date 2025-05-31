package marieteam.marieteam_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class Interface {

    @FXML
    private FlowPane bateauxContainer;


    @FXML
    private VBox sidebarContainer;

    APIController controller = new APIController();

    public void getAllBoats(ActionEvent event) {
        createBoatElement(controller.getAllBoats());
    }


    public void getAllSectors(ActionEvent event) {
        createSecteurSection(controller.getAllSecteurs());
    }

    @FXML
    public void initialize() {
        getAllSectors(new ActionEvent());
    }
    public void getAllBoatsBySecteur(ActionEvent event) {
        //createSecteurSection(controller.getAllBoatsBySecteur(1));
        createBoatElement(controller.getAllBoatsBySecteur(1));
    }

    public void createBoatElement(JSONArray boatsArray) {

        bateauxContainer.getChildren().clear();
        //bateauxContainer.setSpacing(20); // Espacement entre les cartes
        bateauxContainer.setAlignment(Pos.CENTER); // Centrage des cartes

        for (Object boat : boatsArray) {
            JSONObject boatObject = (JSONObject) boat;
            String boatName = (String) boatObject.get("nom");
            String boatNameImage = (String) boatObject.get("nomImage");
            String boatLongueur = (String) boatObject.get("longueur");
            String boatLargeur = (String) boatObject.get("largeur");

            // Récupération de la liste des équipements
            JSONArray equipementsArray = (JSONArray) boatObject.get("equipements");
            String boatEquipements = String.join(", ", equipementsArray);

            // Conteneur principal du bateau
            VBox boatBox = new VBox(10);
            boatBox.setStyle("-fx-border-color: black; -fx-padding: 10; -fx-background-color: #f0f0f0;");
            boatBox.setAlignment(Pos.CENTER);
            boatBox.setMinWidth(200);
            boatBox.setMinHeight(300);

            // Image du bateau (à remplacer par une vraie image si disponible)
            ImageView boatImage = new ImageView(new Image(getClass().getResource("/Assets/BoatsImages/" + boatNameImage + ".jpg").toExternalForm()));
            boatImage.setFitWidth(150);
            boatImage.setFitHeight(100);
            System.out.println(boatNameImage);

            // Labels pour afficher les infos
            Label nameLabel = new Label("Nom : " + boatName);
            Label sizeLabel = new Label("Taille : " + boatLongueur + "m x " + boatLargeur + "m");
            Label equipementsLabel = new Label("Équipements :\n- " + boatEquipements);

            // Ajouter les éléments à la boîte
            boatBox.getChildren().addAll(boatImage, nameLabel, sizeLabel, equipementsLabel);

            // Événement de clic pour générer un PDF
            boatBox.setOnMouseClicked(event -> {
                PDFGenerator pdfGenerator = new PDFGenerator(boatNameImage,boatName, boatLongueur, boatLargeur, boatEquipements);
                pdfGenerator.generatePDF(boatNameImage,boatName, boatLongueur, boatLargeur, boatEquipements);
            });

            bateauxContainer.getChildren().add(boatBox);
        }
    }

    public void createSecteurSection(JSONArray secteurArray) {
         sidebarContainer.getChildren().clear();

        // Espacement entre les éléments
        sidebarContainer.setMinWidth(200);
        sidebarContainer.setPrefHeight(400);
        sidebarContainer.setAlignment(Pos.TOP_CENTER);

        // Titre de la sidebar
        Label titleLabel = new Label("Secteurs");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        System.out.println(titleLabel.getText());
        sidebarContainer.getChildren().addAll(titleLabel);

        // Boucle à travers les secteurs
        for (Object obj : secteurArray) {

            JSONObject secteurObject = (JSONObject) obj;
            String secteurNom = (String) secteurObject.get("nom");
            System.out.println(secteurNom);

            // Conteneur principal du secteur
            VBox secteurBox = new VBox(5);
            secteurBox.setStyle("-fx-border-color: white; -fx-padding: 10;");
            secteurBox.setAlignment(Pos.CENTER);
            secteurBox.setMinWidth(180);
            secteurBox.setMinHeight(50);

            // Label du secteur
            Label secteurLabel = new Label(secteurNom);
            secteurLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            // Ajouter le label dans la boîte
            secteurBox.getChildren().add(secteurLabel);

            Long secteurId = (Long) secteurObject.get("id");

            // Événement de clic pour afficher les bateaux du secteur (filtrage possible ici)
            secteurBox.setOnMouseClicked(event -> {
                System.out.println("Secteur sélectionné : " + secteurNom);
                createBoatElement(controller.getAllBoatsBySecteur(secteurId.intValue()));
            });

            sidebarContainer.getChildren().add(secteurBox);
        }
    }

}