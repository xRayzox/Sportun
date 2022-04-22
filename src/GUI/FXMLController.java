/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField txidCommande;
    @FXML
    private TextField txnomCommande;
    @FXML
    private TextField txproduitCommande;
    @FXML
    private TextField txtotalCommande;
    @FXML
    private TableView<?> tvCommande;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> colnom;
    @FXML
    private TableColumn<?, ?> colproduit;
    @FXML
    private TableColumn<?, ?> coltotal;
    @FXML
    private Button btnAjouterCommande;
    @FXML
    private Button btnModifierCommande;
    @FXML
    private Button btnSupprimerCommande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
    }

    @FXML
    private void addCommande(ActionEvent event) {
    }

    @FXML
    private void updateCommande(ActionEvent event) {
    }

    @FXML
    private void deleteCommande(ActionEvent event) {
    }
    
}
