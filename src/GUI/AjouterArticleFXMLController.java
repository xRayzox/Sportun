/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Article;
import service.ServiceArticle;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author alaagha
 */
public class AjouterArticleFXMLController implements Initializable {

    @FXML
    private TextField tfTitreArticle;
    @FXML
    private TextField tfTagArticle;
    @FXML
    private TextField tfTagDesc;
    @FXML
    private TextArea taArticle;
    @FXML
    private Button btimg;
@FXML
private Button btBlog;
    @FXML
    private Label imagepath;
    @FXML
    private ImageView ImageIV;
        File selectedFile; 
    private String path;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterArticle(ActionEvent event) throws IOException {
                ServiceArticle sa =  new ServiceArticle();

        String titre=tfTitreArticle.getText();     
     String tag=tfTagArticle.getText();
     String desc=tfTagDesc.getText();
     String text=taArticle.getText();
     String img=imagepath.getText();
      if (  titre.isEmpty() || tag.isEmpty()||desc.isEmpty() || desc.isEmpty()||text.isEmpty()||img.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }else{
          Article article=new Article(titre,tag,desc,img,text);
          sa.ajouter(article);
                URL fxUR = getClass().getResource("../GUI/LIstArticleBack.fxml");
        Parent root = FXMLLoader.load(fxUR);
        Stage win = (Stage) btBlog.getScene().getWindow();
        win.setScene(new Scene(root));
      }
    }
      @FXML
    private void ImportImage(ActionEvent event) throws MalformedURLException {
                FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
            ImageIV.setImage(new Image(selectedFile.toURI().toURL().toString()));
            ImageIV.setFitHeight(150);
            ImageIV.setFitWidth(250);
            imagepath.setText(path);
            File tagetFile=new File(Statics.BASE_URL);
            System.out.println(tagetFile.toURI().toURL().toString());
            copyFiles(selectedFile, tagetFile);
        }  
    }
    public static void copyFiles(File sourceLocation , File targetLocation) {

       
                InputStream in = null;
        try {
            in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation+"\\"+sourceLocation.getName());
                        System.out.println(targetLocation+"\\"+sourceLocation.getName());

            // Copy the bits from input stream to output stream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("image ");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjouterArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AjouterArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(AjouterArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            }            
        
}