/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Commentaire;
import service.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author alaagha
 */
public class ListCommentsFXMLController implements Initializable {

    @FXML
    private Button btUtilisateur;
    @FXML
    private Button btService;
    @FXML
    private Button btProduit;
    @FXML
    private Button btBlog;
    @FXML
    private Button btPanier;
    @FXML
    private Button btEvent;
    @FXML
    private VBox vboxlc;
        @FXML

    private HBox hboxbn;
            @FXML

    private Label lab;
                @FXML

        private Label lab1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ServiceCommentaire sc =  new ServiceCommentaire();
        List<Commentaire> lscomnnet = sc.afficher();

        int i = 0;
        for(Commentaire elem: lscomnnet){
lab=new Label();
lab1=new Label();
               Label lab3 = new Label();
               HBox herbox = new HBox();

               Button btsupprimer = new Button("supprimer");
               btsupprimer.setOnAction(event ->{
    try {
        sc.supprimer(elem);
        URL fxURL = getClass().getResource("../GUI/ListCommentsFXML.fxml");
        
        //URL fxURL = getClass().getResource("../gui1/Payment.fxml");
        Parent root = FXMLLoader.load(fxURL);
        Stage win = (Stage) btsupprimer.getScene().getWindow();
        win.setScene(new Scene(root));
        
    } catch (IOException ex) {
        Logger.getLogger(ListCommentsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
               });
//               ImageView imgId=new ImageView();
//               imgId.setImage(new Image("..//blogimages//corbeille-removebg-preview.png"));
//               imgId.setFitHeight(34);
//               imgId.setFitWidth(34);
               VBox verbox = new VBox();
            lab.setText(elem.getUsername());
            lab.setPrefWidth(300);
            System.out.println(elem.getUsername());
            lab1.setText(elem.getText());
            lab1.setPrefWidth(300);
            verbox.getChildren().addAll(lab,lab1);
            herbox.getChildren().addAll(verbox,btsupprimer);
            herbox.setPrefWidth(vboxlc.getMaxWidth());
            herbox.getStyleClass().add("color-palette");
herbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            vboxlc.getChildren().addAll(herbox,lab3);

        }

    }    
    
}
