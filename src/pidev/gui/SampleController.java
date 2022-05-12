/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author BLVCK
 */
public class SampleController implements Initializable {

    @FXML
    private Button openServ;
    @FXML
    private Circle openServ2;
    @FXML
    private ImageView openServ1;
    @FXML
    private Label openServ3;
    @FXML
    private AnchorPane Sample;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openServv(ActionEvent event) {
        
    }

    @FXML
    private void openServv2(MouseEvent event) {
       
    }

    @FXML
    private void openServv1(MouseEvent event) {
        
    }

    @FXML
    private void closewin(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void Panier_go(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("panier_front.fxml"));
        Sample.getChildren().removeAll();
        Sample.getChildren().setAll(menu);
    }

    
}
