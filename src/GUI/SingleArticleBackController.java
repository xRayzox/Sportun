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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.Article;
import model.Commentaire;
import service.ServiceArticle;
import service.ServiceCommentaire;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author alaagha
 */
public class SingleArticleBackController implements Initializable {

    @FXML
    private VBox vbvbvb;
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
    private WebView wbview;
    @FXML
    private VBox vbvbvbdvd;
    private Article article;
 @FXML
    private Text singblogtitreid;
    @FXML
    private Text txtdescription;
    @FXML
    private ImageView btlikes;
    @FXML
    private Label lLikes;
    @FXML
    private Label lcommentaires;
    @FXML
    private Label lhashtag;
        @FXML
    private Label lDate;
    @FXML
    private Label lArticleTitre;
@FXML
private ImageView ivArt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        String usrname="usrname";
        int artid=Statics.ART.getId();
        ListView<VBox> li=new ListView<>();
    ServiceArticle sa=new ServiceArticle();
        article=sa.ArticlleDetail(artid);
        if(sa.PoslikedByUser(artid, 1)){
            File ig=new File("..\\blogimages\\like-removebg-preview.png");
            try {
                System.err.println(ig.toURI().toURL().toString());
            } catch (MalformedURLException ex) {
                Logger.getLogger(SingleArticlFrantController.class.getName()).log(Level.SEVERE, null, ex);
            }

        lArticleTitre.setText(article.getTitre());
        txtdescription.setText(article.getDescription());
        lhashtag.setText(article.getTag());
        lDate.setText(article.getCreatedAt());
        lLikes.setText(sa.likesCount(artid)+" likes");
File im=new File(Statics.RelativeURL+"\\"+article.getMedia());
        try {
            System.out.println(im.toURI().toURL().toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(SingleArticlFrantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ivArt.setImage(new Image(im.toURI().toURL().toString()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(SingleArticlFrantController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    ivArt.setFitHeight(381);
                    ivArt.setFitWidth(691);
        System.out.println(sa.likesCount(artid));
        
        
        
        
        
        
        
        
       ServiceCommentaire sc =  new ServiceCommentaire();
        List<Commentaire> lscomnnet = sc.afficherCommentArticle(artid);
        Commentaire com=new Commentaire();
                     Label labusr = new Label(usrname);
                             TextField txAddCom=new TextField();
                             Button btajcom=new Button();
                             btajcom.setText("ajouter Commentaire");
                             btajcom.setOnAction(event->{
            try {
                com.setArticleid(artid);
                com.setUsername(usrname);
                com.setText(txAddCom.getText());
                sc.ajouter(com);
                URL fxUL = getClass().getResource("../GUI/SingleArticleBack.fxml");
                
                //URL fxURL = getClass().getResource("../gui1/Payment.fxml");
                Parent root = FXMLLoader.load(fxUL);
                Stage win = (Stage) btajcom.getScene().getWindow();
                win.setScene(new Scene(root));
            } catch (IOException ex) {
                Logger.getLogger(SingleArticlFrantController.class.getName()).log(Level.SEVERE, null, ex);
            }
                             });
VBox verbo = new VBox();
            labusr.setPrefWidth(300);
            txAddCom.setPrefWidth(300);
                           HBox herbb=new HBox();               
                           Label labe = new Label();
         verbo.getChildren().addAll(labusr,txAddCom,btajcom);
            herbb.getChildren().addAll(verbo);
            herbb.setPrefWidth(vbvbvbdvd.getMaxWidth());
            herbb.getStyleClass().add("color-palette");
            herbb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            vbvbvbdvd.getChildren().addAll(herbb,labe);
        int i = 0;
        for(Commentaire elem: lscomnnet){
            Label lab = new Label();
            Label lab1 = new Label();
               Label lab3 = new Label();
               HBox herbox = new HBox();

               Button btsupprimer = new Button("supprimer");
               btsupprimer.setOnAction(event ->{
    try {
        sc.supprimer(elem);
        URL fxURL = getClass().getResource("../GUI/SingleArticlFrant.fxml");
        
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
            if(elem.getUsername().equals(usrname)){
            herbox.getChildren().addAll(verbox,btsupprimer);
            }else
            {
            herbox.getChildren().addAll(verbox);
            }
            herbox.setPrefWidth(vbvbvbdvd.getMaxWidth());
            herbox.getStyleClass().add("color-palette");
            herbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            vbvbvbdvd.getChildren().addAll(herbox,lab3);

        }
    }    }
    
}
