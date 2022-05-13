/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import pidev.entites.Fournisseur;
import pidev.services.CrudFournisseur;
import javax.mail.internet.InternetAddress;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class BackFournisseurController implements Initializable {

    @FXML
    private Pane PaneFormulaire;
    @FXML
    private JFXTextArea txtDescriptionFournisseur;
    @FXML
    private JFXTextField txtNomFournisseur;
    @FXML
    private JFXTextField txtEmailFournisseur;
    @FXML
    private Text TitreFormulaire;
    ////
    Fournisseur rec = new Fournisseur();
    CrudFournisseur work = new CrudFournisseur();
    private ObservableList<Fournisseur> ListFournisseurs;
    ////
    @FXML
    private TableColumn<Fournisseur, String> col_Nom;
    @FXML
    private TableColumn<Fournisseur, String> col_Description;
    @FXML
    private TableColumn<Fournisseur, String> col_Email;
    @FXML
    private TableColumn<Fournisseur, String> col_Action;
    @FXML
    private TableView<Fournisseur> TableViewFournisseurs;
    @FXML
    private Pane PaneBlur;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private JFXButton btnAjouter;
    @FXML
    private StackPane StckFournisseur;
    @FXML
    private TextField txtSearch;

    //
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableFournisseur();
    }

       private void LoadTableFournisseur() {

        List<Fournisseur> listee = new ArrayList<>();
        listee = work.AfficherAllFournisseur(rec);
        ObservableList<Fournisseur> Liste = FXCollections.observableArrayList(listee);

        col_Nom.setCellValueFactory(new PropertyValueFactory<>("NomFournisseur"));
        col_Email.setCellValueFactory(new PropertyValueFactory<>("EmailFournisseur"));
        col_Description.setCellValueFactory(new PropertyValueFactory<>("DescriptionFournisseur"));
        ListFournisseurs = FXCollections.observableArrayList(listee);
        TableViewFournisseurs.setItems(ListFournisseurs);
        
        
//////////////////////////////////////////////////////////////////////////////////////////////////////////// Search

        //  Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Fournisseur> filteredData = new FilteredList<>(ListFournisseurs, b -> true);

        //  Set the filter Predicate whenever the filter changes.
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(fourni -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare every table columns fields with lowercase filter text
                String lowerCaseFilter = newValue.toLowerCase();

                // Filter with all table columns
                if (fourni.getNomFournisseur().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    AddIcontoTable();
                    return true;
                } else if (fourni.getEmailFournisseur().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    AddIcontoTable();
                    return true;
                } else if (String.valueOf(fourni.getDescriptionFournisseur()).indexOf(lowerCaseFilter) != -1) {
                    AddIcontoTable();
                    return true;
                } else {
                    AddIcontoTable();
                    return false; // Does not match

                }
            });
        });

        //  Wrap the FilteredList in a SortedList.
        SortedList<Fournisseur> sortedData = new SortedList<>(filteredData);
        //  Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TableViewFournisseurs.comparatorProperty());
        //  Add sorted (and filtered) data to the table.
        TableViewFournisseurs.setItems(sortedData);
        //////////////////////////////////////////////////////
       AddIcontoTable();
    }
    
    private void AddIcontoTable()
    {
     //add cell of button edit 
        Callback<TableColumn<Fournisseur, String>, TableCell<Fournisseur, String>> cellFoctory = (TableColumn<Fournisseur, String> param) -> {
            //make cell containing buttons

            final TableCell<Fournisseur, String> cell = new TableCell<Fournisseur, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {

                        ImageView Deleteicon, Editicon;
                        Deleteicon = new ImageView(new Image("/pidev/images/deleteicon.png"));
                        Deleteicon.setFitHeight(30);
                        Deleteicon.setFitWidth(30);
                        setGraphic(Deleteicon);

                        Editicon = new ImageView(new Image("/pidev/images/editicon.png"));
                        Editicon.setFitHeight(30);
                        Editicon.setFitWidth(30);
                        setGraphic(Editicon);

                        Editicon.setOnMouseClicked((MouseEvent event) -> {
                            //   System.out.println("icon Edit is pressed !");
                            txtNomFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getNomFournisseur());
                            txtEmailFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getEmailFournisseur());
                            txtDescriptionFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getDescriptionFournisseur());

                            OpenPopupModifier();
                        });
                        Deleteicon.setOnMouseClicked((MouseEvent event) -> {
                            //   System.out.println("icon delete is pressed !");
                            if (TableViewFournisseurs.getSelectionModel().getSelectedItem() != null) {
                                rec = TableViewFournisseurs.getSelectionModel().getSelectedItem();
                                Boolean result = work.SupprimerFournisseur(rec.getIdFornisseur());
                                if (result) {
                                    System.out.println("Fournisseur Has Been Deleted ✔");
                                    LoadTableFournisseur();
                                }
                            }
                        });

                        //managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(Deleteicon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(Editicon, new Insets(2, 3, 0, 2));
                        HBox managebtn = new HBox(Editicon, Deleteicon);
                        setGraphic(managebtn);
                    }
                }
            };
            return cell;
        };
        col_Action.setCellFactory(cellFoctory);
    }

    private void OpenPopupModifier() {
        btnModifier.toFront();
        TitreFormulaire.setText("Modifier Le Fournisseur");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
    }

    @FXML
    private void CloseFormulaireClicked(MouseEvent event) {
        PaneFormulaire.setVisible(false);
        PaneBlur.setVisible(false);
    }

    @FXML
    private void OpenFormulaireAdd(MouseEvent event) {
        txtDescriptionFournisseur.clear();
        txtNomFournisseur.clear();
        txtEmailFournisseur.clear();
        btnAjouter.toFront();
        TitreFormulaire.setText("Ajouter un Fournisseur");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
    }

    @FXML
    private void ModifierFournisseurClicked(MouseEvent event) {

        int idFournisseur = 0;
        if (TableViewFournisseurs.getSelectionModel().getSelectedItem() != null) {
            idFournisseur = Integer.valueOf((TableViewFournisseurs.getSelectionModel().getSelectedItem().getIdFornisseur()));
        }
        if (txtNomFournisseur.getText().isEmpty()) {
            txtNomFournisseur.requestFocus();
            shake(txtNomFournisseur);
            return;
        }
        if (txtEmailFournisseur.getText().isEmpty()) {
            txtEmailFournisseur.requestFocus();
            shake(txtEmailFournisseur);
            return;
        }

        if (isValidEmailAddress(txtEmailFournisseur.getText()) == false) {
            txtEmailFournisseur.requestFocus();
            shake(txtEmailFournisseur);
            return;

        }
        if (txtDescriptionFournisseur.getText().isEmpty()) {
            txtDescriptionFournisseur.requestFocus();
            shake(txtDescriptionFournisseur);
            return;
        }
        rec.setNomFournisseur(txtNomFournisseur.getText());
        rec.setEmailFournisseur(txtEmailFournisseur.getText());
        rec.setDescriptionFournisseur(txtDescriptionFournisseur.getText());
        rec.setIdFornisseur(idFournisseur);
        Boolean result = work.ModifierFournisseur(rec);

        if (result) {
            txtDescriptionFournisseur.clear();
            txtNomFournisseur.clear();
            txtEmailFournisseur.clear();
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableFournisseur();
        }
        LoadTableFournisseur();

    }

    @FXML
    private void AjouterFournisseurClicked(MouseEvent event) {
        if (txtNomFournisseur.getText().isEmpty()) {
            txtNomFournisseur.requestFocus();
            shake(txtNomFournisseur);
            return;
        }
        if (txtEmailFournisseur.getText().isEmpty()) {
            txtEmailFournisseur.requestFocus();
            shake(txtEmailFournisseur);
            return;
        }

        if (isValidEmailAddress(txtEmailFournisseur.getText()) == false) {
            txtEmailFournisseur.requestFocus();
            shake(txtEmailFournisseur);
            return;

        }
        if (txtDescriptionFournisseur.getText().isEmpty()) {
            txtDescriptionFournisseur.requestFocus();
            shake(txtDescriptionFournisseur);
            return;
        }

        rec.setNomFournisseur(txtNomFournisseur.getText());
        rec.setEmailFournisseur(txtEmailFournisseur.getText());
        rec.setDescriptionFournisseur(txtDescriptionFournisseur.getText());

        boolean result = work.AjouterFournisseur(rec); // Fonction AjoutUser
        if (result) {

            txtDescriptionFournisseur.clear();
            txtNomFournisseur.clear();
            txtEmailFournisseur.clear();
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableFournisseur();
        }

    }

    public static void shake(Node node) {
        new Shake(node).play();
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void GoToFournisseur(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackFournisseur.fxml"));
        StckFournisseur.getChildren().removeAll();
        StckFournisseur.getChildren().setAll(menu);
    }

    @FXML
    private void GoToProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackProduit.fxml"));
        StckFournisseur.getChildren().removeAll();
        StckFournisseur.getChildren().setAll(menu);
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("FrontProduit.fxml"));
        StckFournisseur.getChildren().removeAll();
        StckFournisseur.getChildren().setAll(menu);
    }

}
