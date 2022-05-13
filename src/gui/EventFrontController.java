/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.jfoenix.controls.JFXMasonryPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import models.Event;
import service.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class EventFrontController implements Initializable {

    @FXML
    private StackPane StckFrontProduit;
    @FXML
    private Pane PaneBlur;
    @FXML
    private ScrollPane scrollPane;

    private final JFXMasonryPane mansoryPane = new JFXMasonryPane();
    ////
    Event rec = new Event();
    ServiceEvent work = new ServiceEvent();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        scrollPane.setStyle("-fx-background: rgb(255,255,255);\n -fx-background-color: rgb(255,255,255)");
        initMansoryCard();
        LoadCardProduits();
    }  
    
      private void initMansoryCard() {
        mansoryPane.setPadding(new Insets(15, 15, 15, 15));
        mansoryPane.setVSpacing(5);
        mansoryPane.setHSpacing(5);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mansoryPane);

    }
      private void LoadCardProduits() {

        mansoryPane.getChildren().clear();
        List<Event> listeEvents = new ArrayList<>();
        listeEvents = work.affichageProducts(rec);

        if (!listeEvents.isEmpty()) {
            for (int i = 0; i < listeEvents.size(); i++) {
                VBox root = new VBox();
                ImageView PreviewImageProduit = new ImageView();
                PreviewImageProduit.setFitWidth(120);
                PreviewImageProduit.setFitHeight(120);
                PreviewImageProduit.setPreserveRatio(false);
                PreviewImageProduit.setSmooth(true);
                PreviewImageProduit.setCache(true);

                String nom = listeEvents.get(i).getNameevent();
                int id = listeEvents.get(i).getId();
                String prix = listeEvents.get(i).getNewprix();
                //int Quantite = listeEvents.get(i).getQuantite();
                //
                File dest = new File("C:\\xampp\\htdocs\\Sportun\\public\\uploads\\images\\");
                File f = new File(dest, listeEvents.get(i).getImage());
                //
                if (listeEvents.get(i).getImage() != null) {
                    if (!f.exists()) {
                        try {
                            Image img = new Image(new FileInputStream("C:\\xampp\\htdocs\\Sportun\\public\\uploads\\images\\" + "nophoto.jpg"));
                            PreviewImageProduit.setImage(img);
                        } catch (FileNotFoundException ex) {
                            //Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            // Image img = new Image(new FileInputStream(listeUser.get(i).getCarteidentity()));
                            Image img = new Image(new FileInputStream("C:\\xampp\\htdocs\\Sportun\\public\\uploads\\images\\" + listeEvents.get(i).getImage()));
                            PreviewImageProduit.setImage(img);
                        } catch (FileNotFoundException ex) {
                            //   Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else if (listeEvents.get(i).getImage() == null) {

                    //identityView.setImage(new Image(getClass().getResource("/resources/image/empty-image.jpg").toString()));
                    File file = new File("C:\\xampp\\htdocs\\Sportun\\public\\uploads\\images\\" + "nophoto.jpg");
                    Image imagee = new Image(file.toURI().toString());
                    PreviewImageProduit.setImage(imagee);
                }
                root.setPadding(new Insets(12, 17, 17, 17));
                root.setSpacing(13);

                ///
                root.setStyle("-fx-background-color: #fff; -fx-background-radius: 15px;-fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                //labels[i].setTextFill(Color.color(1, 0, 0));

                Label LabelNom = new Label("" + nom);
                Label LabelPrix = new Label("" + prix + " DT");
                LabelPrix.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setStyle("-fx-font-weight: bold");
                //label2.setStyle("-fx-font-weight: bold");

                root.getChildren().addAll(LabelNom, PreviewImageProduit, LabelPrix);
                root.setAlignment(Pos.CENTER);
                mansoryPane.getChildren().add(root);

                root.setOnMouseClicked(ev -> {

                    if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2) {
                        System.out.println("" + id);

                        /////////////////////////////////////////                      
                        
                        //Les Actions sur la Carte les Produits
                                              
                        /////////////////////////////////////////
                    }

                });
            }

        }

    }
       @FXML
    private void GoToFournisseur(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("EventFront.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void GoToProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("PieChart.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Mail.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }
 @FXML
    private void gotocategorie(MouseEvent event) throws IOException {
          Parent menu = FXMLLoader.load(getClass().getResource("CategorieView.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }
    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    }

   

    
}
