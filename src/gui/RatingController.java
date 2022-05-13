/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.controlsfx.control.Rating;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author MAKREM
 */
public class RatingController implements Initializable {
@FXML
    private Rating rating;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void BtnSubmit(ActionEvent event) {
      System.out.println("Rating given by user :" + rating.getRating());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rating");
         alert.setContentText("Rating=" +rating.getRating()+"/5"+"  "
                 + "    "+ "Thank you ! Have a nice day ");
       
        alert.show();
    }
   
}    
    
