/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIcommande;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author selme
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button btnGestPromo;
    @FXML
    private Button btnGestEvenet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pagePanier(ActionEvent event) {
     FXMLLoader loader =new FXMLLoader(getClass().getResource("FXMLpanier.fxml")) ;
     try {
            Parent root = loader.load(); //charger les acteurs (button,textfield...)
            FXMLpanierController cc =loader.getController();//kima jibna l fichier fxml njibou lcontrolleur mta3ha
        btnGestPromo.getScene().setRoot(root); ;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}}
