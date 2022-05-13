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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Article;
import service.ServiceArticle;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author alaagha
 */
public class ListArticleFrontController implements Initializable {

    @FXML
    private Text txtdescription;
    @FXML
    private VBox voboxid;
    @FXML
    private TextField tfsearche;
    @FXML
    private Button bt1;
    @FXML
    private Button bt2;
    @FXML
    private Button bt3;
    @FXML
    private Button bt4;
    @FXML
    private Button bt5;
    @FXML
    private Button bt6;
    @FXML
    private Button ajouterArt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              // TODO
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
               btdetail.setOnAction(event ->{
    try {
        URL fxURL = getClass().getResource("../GUI/SingleArticleBack.fxml");
        
        //URL fxURL = getClass().getResource("../gui1/Payment.fxml");
        Parent root = FXMLLoader.load(fxURL);
        Stage win = (Stage) btdetail.getScene().getWindow();
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
  lab1.setFont(Font.font(26));
              lab1.setPrefWidth(300);

        lab2.setText(elem.getDescription());
        lab2.setGraphic(imagev);
          lab2.setFont(Font.font(20));
            lab2.setPrefWidth(300);

        lab3.setText(elem.getTag());

            lab3.setPrefWidth(300);
        labdate.setText(elem.getCreatedAt());
            labdate.setPrefWidth(100);

                        lLikes.setPrefWidth(50);
lLikes.setText(sa.likesCount(elem.getId())+" likes");
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
            herbox.setPrefWidth(voboxid.getMaxWidth());
            herbox.getStyleClass().add("color-palette");
herbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            voboxid.getChildren().addAll(herbox,lab4);
               
       }
             ajouterArt.setOnAction(eventss->{
                         URL fxURL = getClass().getResource("../GUI/AjouterArticleFXML.fxml");
             });
    }    
  
    
}
