/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import static javax.swing.text.StyleConstants.Bold;
import model.Article;
import service.ServiceArticle;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author alaagha
 */
public class ListArticleFrontooController implements Initializable {

    @FXML
    private VBox vbfront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

                   ServiceArticle sa=new ServiceArticle();
        List<Article> lsarticle = sa.afficher();
  

             for(Article elem: lsarticle)
       {
       	 System.out.println (elem.toString());
         Label lab1=new  Label();
         Label lab2=new  Label();
         Label lab3=new  Label();
           ImageView iv=new ImageView();
           Button btdetail=new Button();
           Button btmodif=new Button();
           Button btdelete=new Button();
btdetail.setText("details");
        btdetail.setStyle("-fx-background-color: #f23849");
       // btdetail.setPrefHeight(62);
                btdetail.setPrefSize(200,42);
                btdetail.setTextFill(Color.WHITESMOKE);
            //    btdetail.setFont(Font.font(, FontPosture.BOLD, 16));
               btdetail.setOnAction(event ->{
    try {
        URL fxURL = getClass().getResource("../GUI/SingleAritcleFronto.fxml");
                Statics.ART=elem;
        Parent root = FXMLLoader.load(fxURL);
        Stage win = (Stage) btdetail.getScene().getWindow();
        win.setScene(new Scene(root));
        
    } catch (IOException ex) {
        Logger.getLogger(ListCommentsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
               });
                
               btmodif.setText("modifier");
               btmodif.setOnAction(event ->{
    try {
        URL fxURL = getClass().getResource("../GUI/ModifierArticle.fxml");
        Statics.ART=elem;
        Parent root = FXMLLoader.load(fxURL);
        Stage win = (Stage) btmodif.getScene().getWindow();
        win.setScene(new Scene(root));
        
    } catch (IOException ex) {
        Logger.getLogger(ListCommentsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
               });
               
               
               btdelete.setText("supprimer");
               btdelete.setOnAction(event ->{
    try {
        sa.supprimer(elem);
        URL fxURL = getClass().getResource("../GUI/ListArticleFronto.fxml");
        
        Parent root = FXMLLoader.load(fxURL);
        Stage win = (Stage) btdelete.getScene().getWindow();
        win.setScene(new Scene(root));
        
    } catch (IOException ex) {
        Logger.getLogger(ListCommentsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
               });
                              Label lab4 = new Label();
               HBox herbox = new HBox();
                              VBox verbox = new VBox();
                              Label labdate = new Label();
                              Label lLikes = new Label();
                              ImageView imagev=new ImageView();
  lab1.setText(elem.getTitre());
  lab1.setTextFill(Color.FLORALWHITE);
  lab1.setFont(Font.font(26));
              lab1.setPrefWidth(300);

        lab2.setText(elem.getDescription());
          lab2.setTextFill(Color.FLORALWHITE);

        lab2.setGraphic(imagev);
          lab2.setFont(Font.font(20));
            lab2.setPrefWidth(300);

        lab3.setText(elem.getTag());
  lab3.setTextFill(Color.FLORALWHITE);
            lab3.setPrefWidth(300);
        labdate.setText(elem.getCreatedAt());
          labdate.setTextFill(Color.FLORALWHITE);
            labdate.setPrefWidth(100);

                        lLikes.setPrefWidth(50);
lLikes.setText(sa.likesCount(elem.getId())+" likes");
  lLikes.setTextFill(Color.FLORALWHITE);
File im=new File(Statics.RelativeURL+"\\"+elem.getMedia());
        try {
            System.out.println(im.toURI().toURL().toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(SingleArticlFrantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            iv.setImage(new Image(im.toURI().toURL().toString()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(SingleArticlFrantController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    iv.setFitHeight(181);
                    iv.setFitWidth(191);
                           verbox.getChildren().addAll(lab1,lab2,lab3,labdate,lLikes,btdetail);

                herbox.getChildren().addAll(iv,verbox);
            herbox.setPrefWidth(vbfront.getMaxWidth());
            herbox.getStyleClass().add("color-palette");
herbox.setBackground(new Background(new BackgroundFill(Color.valueOf("#393351"), CornerRadii.EMPTY, Insets.EMPTY)));
            vbfront.getChildren().addAll(herbox,lab4);
       }
    }    
    

    @FXML
    private void closewin(MouseEvent event) {
    }
    
}
