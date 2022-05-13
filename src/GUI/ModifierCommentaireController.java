/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Commentaire;
import service.ServiceCommentaire;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author alaagha
 */
public class ModifierCommentaireController implements Initializable {

    @FXML
    private TextField tfmodifCom;
    @FXML
    private Text txtusername;
    @FXML
    private Button btannuler;
    @FXML
    private Button btsub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String username = "usrname";
                          //  Commentaire com=new Commentaire(26,"good job");
                          Commentaire com=Statics.COM;
        int comid = 3;
        String comtxt = "naorno";
        txtusername.setText(com.getUsername());
        tfmodifCom.setText(com.getText());
        btsub.setOnAction(event ->{
                        try {
                            ServiceCommentaire sc=new ServiceCommentaire();
                com.setText(tfmodifCom.getText());
                sc.modifier(com);
                URL fxUL = getClass().getResource("../GUI/SingleAritcleFronto.fxml");
                
                Parent root = FXMLLoader.load(fxUL);
                Stage win = (Stage) btsub.getScene().getWindow();
                win.setScene(new Scene(root));
            } catch (IOException ex) {
                Logger.getLogger(SingleArticlFrantController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btannuler.setOnAction(envenn->{
                       try {
                            ServiceCommentaire sc=new ServiceCommentaire();
                com.setText(tfmodifCom.getText());
                sc.modifier(com);
                URL fxUL = getClass().getResource("../GUI/SingleAritcleFronto.fxml");
                
                Parent root = FXMLLoader.load(fxUL);
                Stage win = (Stage) btsub.getScene().getWindow();
                win.setScene(new Scene(root));
            } catch (IOException ex) {
                Logger.getLogger(SingleArticlFrantController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    public void modif(Commentaire com){
        txtusername.setText(com.getUsername());
        tfmodifCom.setText(com.getText());
        btsub.setOnAction(event ->{
                        try {
                            ServiceCommentaire sc=new ServiceCommentaire();
                com.setText(tfmodifCom.getText());
                sc.modifier(com);
                URL fxUL = getClass().getResource("../GUI/SingleAritcleFronto.fxml");
                
                Parent root = FXMLLoader.load(fxUL);
                Stage win = (Stage) btsub.getScene().getWindow();
                win.setScene(new Scene(root));
            } catch (IOException ex) {
                Logger.getLogger(SingleArticlFrantController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
