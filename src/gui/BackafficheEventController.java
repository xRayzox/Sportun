/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class BackafficheEventController implements Initializable {

    @FXML
    private StackPane StckProduits;
    @FXML
    private TableView<?> TableViewProduits;
    @FXML
    private TableColumn<?, ?> col_Nom;
    @FXML
    private TableColumn<?, ?> col_Prix;
    @FXML
    private TableColumn<?, ?> col_Quantity;
    @FXML
    private TableColumn<?, ?> col_Type;
    @FXML
    private TableColumn<?, ?> col_image;
    @FXML
    private TableColumn<?, ?> col_idFournisseur;
    @FXML
    private TableColumn<?, ?> col_Action;
    @FXML
    private Pane PaneBlur;
    @FXML
    private Pane PaneFormulaire;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private JFXTextField txtType;
    @FXML
    private Text TitreFormulaire;
    @FXML
    private JFXButton btnAjouter;
    @FXML
    private JFXTextField txtPrix;
    @FXML
    private JFXTextField txtQuantite;
    @FXML
    private ImageView PreviewImage;
    @FXML
    private JFXComboBox<?> ComboFournisseur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToFournisseur(MouseEvent event) {
    }

    @FXML
    private void GoToProduit(MouseEvent event) {
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) {
    }

    @FXML
    private void OpenFormulaireAdd(MouseEvent event) {
    }

    @FXML
    private void CloseFormulaireClicked(MouseEvent event) {
    }

    @FXML
    private void ModifierProduitClicked(MouseEvent event) {
    }

    @FXML
    private void AjouterProduitClicked(MouseEvent event) {
    }

    @FXML
    private void UploadImageClicked(MouseEvent event) {
    }

    @FXML
    private void CloseWindowClicked(MouseEvent event) {
    }
   
    
}
