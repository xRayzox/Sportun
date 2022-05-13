/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.io.FileUtils;
import static pidev.BackFournisseurController.isValidEmailAddress;
import static pidev.BackFournisseurController.shake;
import pidev.connection.Connection;
import pidev.entites.Produit;
import pidev.services.CrudProduit;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class BackProduitController implements Initializable {

    @FXML
    private Pane PaneBlur;
    @FXML
    private Pane PaneFormulaire;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private Text TitreFormulaire;
    @FXML
    private JFXButton btnAjouter;
    ////
    Produit rec = new Produit();
    CrudProduit work = new CrudProduit();
    private ObservableList<Produit> ListProduits;
    ////
    @FXML
    private StackPane StckProduits;
    @FXML
    private TableView<Produit> TableViewProduits;
    @FXML
    private TableColumn<Produit, String> col_Nom;
    @FXML
    private TableColumn<Produit, Integer> col_Prix;
    @FXML
    private TableColumn<Produit, Integer> col_Quantity;
    @FXML
    private TableColumn<Produit, String> col_Type;
    @FXML
    private TableColumn<Produit, ImageView> col_image;
    @FXML
    private TableColumn<Produit, String> col_idFournisseur;
    @FXML
    private TableColumn<Produit, String> col_Action;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtType;
    @FXML
    private JFXTextField txtPrix;
    @FXML
    private JFXTextField txtQuantite;
    @FXML
    private JFXComboBox<String> ComboFournisseur;
    @FXML
    private ImageView PreviewImage;
    File selectedFile;
    private FileChooser Fc = new FileChooser();
    private File file;
    private static String pathImage = "";
    @FXML
    private Label txtStatTotal;
    @FXML
    private Label txtStatSomme;
    @FXML
    private TextField txtSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableProduits();
        RemplirComboFournisseur();
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("FrontProduit.fxml"));
        StckProduits.getChildren().removeAll();
        StckProduits.getChildren().setAll(menu);
    }

    private class ImageProduitsCellValueFactory implements Callback<TableColumn.CellDataFeatures<Produit, ImageView>, ObservableValue<ImageView>> {

        @Override
        public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Produit, ImageView> param) {
            Produit item = param.getValue();
            ImageView img = null;

            img = item.getImgViewProduit();

            return new SimpleObjectProperty<>(img);
        }
    }

    private void LoadTableProduits() {

        List<Produit> listee = new ArrayList<>();
        listee = work.AfficherAllProduit(rec);
        ObservableList<Produit> Liste = FXCollections.observableArrayList(listee);

        col_Nom.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
        col_Prix.setCellValueFactory(new PropertyValueFactory<>("PrixProduit"));
        col_Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        //
        col_Type.setCellValueFactory(new PropertyValueFactory<>("typeProduit"));

        col_image.setCellValueFactory(new ImageProduitsCellValueFactory());

        col_idFournisseur.setCellValueFactory(new NomFournisseurCellValueFactory());
        //
        ListProduits = FXCollections.observableArrayList(listee);
        TableViewProduits.setItems(ListProduits);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        txtStatTotal.setText(String.valueOf(ListProduits.size())); // Total
        txtStatTotal.setAlignment(Pos.CENTER);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        ///////////////////////////////////////////////// Search 
        //  Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Produit> filteredData = new FilteredList<>(ListProduits, b -> true);

        //  Set the filter Predicate whenever the filter changes.
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare every table columns fields with lowercase filter text
                String lowerCaseFilter = newValue.toLowerCase();

                // Filter with all table columns
                if (employee.getNomProduit().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    AddIcontoTable();
                    return true;
                } else if (employee.getTypeProduit().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    AddIcontoTable();
                    return true;
                } else if (String.valueOf(employee.getQuantite()).indexOf(lowerCaseFilter) != -1) {
                    AddIcontoTable();
                    return true;
                } else if (String.valueOf(employee.getPrixProduit()).indexOf(lowerCaseFilter) != -1) {
                    AddIcontoTable();
                    return true;
                } else {
                    AddIcontoTable();
                    return false; // Does not match

                }
            });
        });

        //  Wrap the FilteredList in a SortedList.
        SortedList<Produit> sortedData = new SortedList<>(filteredData);

        //  Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TableViewProduits.comparatorProperty());

        //  Add sorted (and filtered) data to the table.
        TableViewProduits.setItems(sortedData);

        AddIcontoTable();
    }

    private void AddIcontoTable() {

        /////////////////////////////////////////////////
        //add cell of button edit 
        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFoctory = (TableColumn<Produit, String> param) -> {
            //make cell containing buttons

            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {

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
                            System.out.println("icon Edit is pressed !");
                            txtNom.setText(TableViewProduits.getSelectionModel().getSelectedItem().getNomProduit());
                            txtType.setText(TableViewProduits.getSelectionModel().getSelectedItem().getTypeProduit());
                            txtQuantite.setText(String.valueOf(TableViewProduits.getSelectionModel().getSelectedItem().getQuantite()));
                            txtPrix.setText(String.valueOf(TableViewProduits.getSelectionModel().getSelectedItem().getPrixProduit()));
                            ComboFournisseur.setValue(GetEmailFournisseur(TableViewProduits.getSelectionModel().getSelectedItem().getIdFournisseur()));
                            //PreviewImage.setImage(value);
                            OpenPopupModifier();
                        });
                        Deleteicon.setOnMouseClicked((MouseEvent event) -> {
                            //   System.out.println("icon delete is pressed !");
                            if (TableViewProduits.getSelectionModel().getSelectedItem() != null) {
                                rec = TableViewProduits.getSelectionModel().getSelectedItem();
                                Boolean result = work.SupprimerProduit(rec.getIdProduit());
                                if (result) {
                                    System.out.println("Produit Has Been Deleted ✔");
                                    LoadTableProduits();
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
//////////////////////////////////////////////////////////////////////////////////////////////// Calcule Somme
        //Calcule Somme
        TableViewProduits.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2) {
                int Quantite = TableViewProduits.getSelectionModel().getSelectedItem().getQuantite();
                int Prix = TableViewProduits.getSelectionModel().getSelectedItem().getPrixProduit();
                txtStatSomme.setText(String.valueOf(Quantite * Prix));
                txtStatSomme.setAlignment(Pos.CENTER);
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////// Color Red
        col_Quantity.setCellFactory(column -> {
            return new TableCell<Produit, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);

                    TableRow<Produit> currentRow = getTableRow();
                    
                    if (!isEmpty()) {
                        if (item == 0) {
                            currentRow.setStyle("-fx-background-color: linear-gradient(to right top, #EB3349 , #F45C43);-fx-opacity:0.5"); ///  Set color red
                        } else {
                            currentRow.setStyle("");
                        }
                    }
                }
            };
        });
    }

    @FXML
    private void GoToFournisseur(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackFournisseur.fxml"));
        StckProduits.getChildren().removeAll();
        StckProduits.getChildren().setAll(menu);
    }

    @FXML
    private void GoToProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("BackProduit.fxml"));
        StckProduits.getChildren().removeAll();
        StckProduits.getChildren().setAll(menu);
    }

    private void OpenPopupModifier() {
        int idProduit = 0;
        if (TableViewProduits.getSelectionModel().getSelectedItem() != null) {
            idProduit = Integer.valueOf((TableViewProduits.getSelectionModel().getSelectedItem().getIdProduit()));
        }
        ////////////
        try {
            String Destination = "C:\\xampp\\htdocs\\Projet\\Uploads\\";
            File dest = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\");

            String requeteee = "SELECT image FROM produit WHERE id = '" + idProduit + "'";
            Statement psttt = Connection.getInstance().getCnx().createStatement();
            ResultSet rsss = psttt.executeQuery(requeteee);
            while (rsss.next()) {
                String exist = "";
                exist = rsss.getString(1);
                if (exist != null && !exist.isEmpty()) {
                    String ImageProduct = Destination + rsss.getString(1);
                    String NomImage = rsss.getString(1);
                    pathImage = rsss.getString(1);
                    File f = new File(dest, NomImage);
                    if (f.exists()) {
                        System.out.println("File  Exist  in Uploads");
                        Image imagee = new Image(new FileInputStream(ImageProduct), 200, 200, true, true);
                        PreviewImage.setImage(imagee);
                    } else {
                        System.out.println("File Not Exist  in Uploads");
                        File file = new File(Destination + "uploadimageicon.png");
                        Image imagee = new Image(file.toURI().toString());
                        PreviewImage.setImage(imagee);
                    }

                } else if (exist == null || exist.isEmpty()) {
                    System.out.println("Base de donnée champ image null or empty !");
                    File file = new File(Destination + "uploadimageicon.png");
                    Image imagee = new Image(file.toURI().toString());
                    PreviewImage.setImage(imagee);
                }

            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BackProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ///////////
        btnModifier.toFront();
        TitreFormulaire.setText("Modifier Le Produit");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
    }

    @FXML
    private void OpenFormulaireAdd(MouseEvent event) {
        txtNom.clear();
        txtType.clear();
        txtQuantite.clear();
        txtPrix.clear();
        // ComboFournisseur.setValue(null);
        ComboFournisseur.getSelectionModel().clearSelection();
        btnAjouter.toFront();
        TitreFormulaire.setText("Ajouter un Produit");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
    }

    @FXML
    private void CloseFormulaireClicked(MouseEvent event) {
        PaneFormulaire.setVisible(false);
        PaneBlur.setVisible(false);

        //
        File file = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "uploadimageicon.png");
        Image imagee = new Image(file.toURI().toString());
        PreviewImage.setImage(imagee);
        //
        pathImage = "";
    }

    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    }

    public static void shake(Node node) {
        new Shake(node).play();
    }

    @FXML
    private void AjouterProduitClicked(MouseEvent event) {

        if (txtNom.getText().isEmpty()) {
            txtNom.requestFocus();
            shake(txtNom);
            return;
        }
        if (txtType.getText().isEmpty()) {
            txtType.requestFocus();
            shake(txtType);
            return;
        }

        if (txtQuantite.getText().isEmpty() || txtQuantite.getText().chars().allMatch(Character::isAlphabetic)) {
            txtQuantite.requestFocus();
            shake(txtQuantite);
            return;
        }

        if (txtPrix.getText().isEmpty() || txtPrix.getText().chars().allMatch(Character::isAlphabetic)) {
            txtPrix.requestFocus();
            shake(txtPrix);
            return;
        }

        if (ComboFournisseur.getSelectionModel().getSelectedItem() == null) {
            ComboFournisseur.requestFocus();
            shake(ComboFournisseur);
            return;
        }

        if (pathImage.isEmpty()) {
            PreviewImage.requestFocus();
            shake(PreviewImage);
            return;
        }

        rec.setNomProduit(txtNom.getText());
        rec.setTypeProduit(txtType.getText());
        rec.setQuantite(Integer.valueOf(txtQuantite.getText()));
        rec.setPrixProduit(Integer.valueOf(txtPrix.getText()));
        rec.setImageProduit(pathImage);
        rec.setIdFournisseur(GetidFournisseur(ComboFournisseur.getSelectionModel().getSelectedItem()));
        //  ComboFournisseur.getSelectionModel().getSelectedItem();
        // rec.setVote();

        boolean result = work.AjouterProduit(rec); // Fonction AjoutUser
        if (result) {

            txtNom.clear();
            txtType.clear();
            txtQuantite.clear();
            txtPrix.clear();
            ComboFournisseur.getSelectionModel().clearSelection();
            pathImage = "";
            // ComboFournisseur.setValue(null);
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableProduits();
        }

    }

    private void RemplirComboFournisseur() {
        try {

            String requetee = "SELECT email FROM fournisseur";
            Statement pstt = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                ComboFournisseur.getItems().addAll(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int GetidFournisseur(String email) {
        int id = 0;
        try {

            String requetee = "SELECT id FROM fournisseur WHERE email = '" + email + "'";
            Statement pstt = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                id = rs.getInt(1);
                break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    private String GetEmailFournisseur(int id) {
        String email = null;
        try {

            String requetee = "SELECT email FROM fournisseur WHERE id = '" + id + "'";
            Statement pstt = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                email = rs.getString(1);
                break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return email;
    }

    @FXML
    private void ModifierProduitClicked(MouseEvent event) {
        int idProduit = 0;
        if (TableViewProduits.getSelectionModel().getSelectedItem() != null) {
            idProduit = Integer.valueOf((TableViewProduits.getSelectionModel().getSelectedItem().getIdProduit()));
        }
        if (txtNom.getText().isEmpty()) {
            txtNom.requestFocus();
            shake(txtNom);
            return;
        }
        if (txtType.getText().isEmpty()) {
            txtType.requestFocus();
            shake(txtType);
            return;
        }

        if (txtQuantite.getText().isEmpty() || txtQuantite.getText().chars().allMatch(Character::isAlphabetic)) {
            txtQuantite.requestFocus();
            shake(txtQuantite);
            return;
        }

        if (txtPrix.getText().isEmpty() || txtPrix.getText().chars().allMatch(Character::isAlphabetic)) {
            txtPrix.requestFocus();
            shake(txtPrix);
            return;
        }
        if (pathImage.isEmpty()) {
            PreviewImage.requestFocus();
            shake(PreviewImage);
            return;
        }

        if (ComboFournisseur.getSelectionModel().getSelectedItem() == null) {
            ComboFournisseur.requestFocus();
            shake(ComboFournisseur);
            return;
        }
        rec.setNomProduit(txtNom.getText());
        rec.setTypeProduit(txtType.getText());
        rec.setQuantite(Integer.valueOf(txtQuantite.getText()));
        rec.setPrixProduit(Integer.valueOf(txtPrix.getText()));
        rec.setImageProduit(pathImage);
        rec.setIdFournisseur(GetidFournisseur(ComboFournisseur.getSelectionModel().getSelectedItem()));
        rec.setIdProduit(idProduit);
        Boolean result = work.ModifierProduit(rec);

        if (result) {
            txtNom.clear();
            txtType.clear();
            txtQuantite.clear();
            txtPrix.clear();
            ComboFournisseur.getSelectionModel().clearSelection();
            pathImage = "";
            // ComboFournisseur.setValue(null);
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableProduits();

            // Reset imagePreview ater Modif
            File file = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\" + "uploadimageicon.png");
            Image imagee = new Image(file.toURI().toString());
           PreviewImage.setImage(imagee);
        }
        LoadTableProduits();

    }

    @FXML
    private void UploadImageClicked(MouseEvent event) {
        File dest = new File("C:\\xampp\\htdocs\\Projet\\Uploads");
        Fc.setTitle("Open Resource File");
        Fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        selectedFile = Fc.showOpenDialog(null);

        if (selectedFile != null) {
            try {

                String Destination = "C:\\xampp\\htdocs\\Projet\\Uploads";
                File f = new File(dest, selectedFile.getName());

                FileUtils.copyFileToDirectory(selectedFile, dest);
                pathImage = selectedFile.getName();

                Image image = new Image(new FileInputStream(selectedFile), 200, 200, true, true);
                PreviewImage.setImage(image);
            } catch (IOException ex) {
                ex.getStackTrace();
            }
        }
    }

    private Stage getStage() {
        return (Stage) PreviewImage.getScene().getWindow();
    }

    private File getFileSelected() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        fileChooser.setTitle("Select an image");

        File selectedImage = fileChooser.showOpenDialog(getStage());
        return selectedImage;
    }
    
    
        private class NomFournisseurCellValueFactory implements Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
            Produit item = param.getValue();
            Integer idFournisseur = 0;
            String nom = "";
            idFournisseur = item.getIdFournisseur();

            try {

                String requetee = "SELECT name FROM fournisseur WHERE id = '" + idFournisseur + "'";
                Statement pstt = Connection.getInstance().getCnx().createStatement();
                ResultSet rs = pstt.executeQuery(requetee);
                while (rs.next()) {
                    nom = rs.getString(1);
                    break;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return new SimpleObjectProperty<>(nom);
        }
    }

}
