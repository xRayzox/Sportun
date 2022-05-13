/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.Commentaire;

/**
 * FXML Controller class
 *
 * @author alaagha
 */
public class ComItemController  {

    @FXML
    private Text txtComment;
     @FXML
private Label lusername;
    private Commentaire commentaire;


    void setData(Commentaire commentaire) {
        lusername.setText(commentaire.getUsername());
        txtComment.setText(commentaire.getText());
    }
    
}
