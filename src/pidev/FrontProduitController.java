/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import com.jfoenix.controls.JFXMasonryPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pidev.connection.Connection;
import pidev.entites.Produit;
import pidev.services.CrudProduit;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class FrontProduitController implements Initializable {

    @FXML
    private StackPane StckFrontProduit;
    @FXML
    private Pane PaneBlur;
    @FXML
    private ScrollPane scrollPane;
    private final JFXMasonryPane mansoryPane = new JFXMasonryPane();
    ////
    Produit rec = new Produit();
    CrudProduit work = new CrudProduit();
    ////

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
        List<Produit> listeProduits = new ArrayList<>();
        listeProduits = work.AfficherAllProduit(rec);

        if (!listeProduits.isEmpty()) {
            for (int i = 0; i < listeProduits.size(); i++) {
                VBox root = new VBox();
                ImageView PreviewImageProduit = new ImageView();
                PreviewImageProduit.setFitWidth(120);
                PreviewImageProduit.setFitHeight(120);
                PreviewImageProduit.setPreserveRatio(false);
                PreviewImageProduit.setSmooth(true);
                PreviewImageProduit.setCache(true);

                String nom = listeProduits.get(i).getNomProduit();
                int id = listeProduits.get(i).getIdProduit();
                int prix = listeProduits.get(i).getPrixProduit();
                int Quantite = listeProduits.get(i).getQuantite();
                //
                File dest = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\");
                File f = new File(dest, listeProduits.get(i).getImageProduit());
                //
                if (listeProduits.get(i).getImageProduit() != null) {
                    if (!f.exists()) {
                        try {
                            Image img = new Image(new FileInputStream("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "nophoto.jpg"));
                            PreviewImageProduit.setImage(img);
                        } catch (FileNotFoundException ex) {
                            //Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            // Image img = new Image(new FileInputStream(listeUser.get(i).getCarteidentity()));
                            Image img = new Image(new FileInputStream("C:\\xampp\\htdocs\\Projet\\Uploads\\" + listeProduits.get(i).getImageProduit()));
                            PreviewImageProduit.setImage(img);
                        } catch (FileNotFoundException ex) {
                            //   Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else if (listeProduits.get(i).getImageProduit() == null) {

                    //identityView.setImage(new Image(getClass().getResource("/resources/image/empty-image.jpg").toString()));
                    File file = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "nophoto.jpg");
                    Image imagee = new Image(file.toURI().toString());
                    PreviewImageProduit.setImage(imagee);
                }
                root.setPadding(new Insets(12, 17, 17, 17));
                root.setSpacing(13);

                ///
                root.setStyle("-fx-background-color: #fff; -fx-background-radius: 15px;-fx-effect:dropshadow(three-pass-box, gray, 10, 0, 0, 0);");
                //labels[i].setTextFill(Color.color(1, 0, 0));

                Label LabelNom = new Label("" + nom);
                Label LabelQte = new Label("" + Quantite +" piéces ");
                Label LabelPrix = new Label("" + prix + " DT");
                LabelPrix.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setTextFill(Color.web("#202B36", 0.8));
                LabelNom.setStyle("-fx-font-weight: bold");

                ////
                ImageView BuyProducticon;
                BuyProducticon = new ImageView(new Image("/pidev/images/buyicon.png"));
                BuyProducticon.setFitHeight(30);
                BuyProducticon.setFitWidth(30);
                HBox managebtn = new HBox(BuyProducticon);

                if(Quantite>0)  // Yo93ed yaffichi mdem Quantite lproduit mawsoltech lel 0 ken woslt m3edech tetafficha
                {
                root.getChildren().addAll(LabelNom, PreviewImageProduit, LabelPrix, LabelQte , managebtn);
                root.setAlignment(Pos.CENTER);
                mansoryPane.getChildren().add(root);
                }

                BuyProducticon.setOnMouseClicked((MouseEvent event) -> {
                    System.out.println("icon NotVerified is pressed !");
                    // System.out.println("" + id);

                    work.DecrementProduit(id); //  - 1 quantite
                    
                    
                    LoadCardProduits();
                    
                    
                });

            }

        }

    }

    @FXML
    private void GoToFournisseur(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackFournisseur.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void GoToProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackProduit.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("FrontProduit.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    }

}
