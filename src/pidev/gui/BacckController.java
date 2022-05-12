/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import animatefx.animation.Shake;
/*import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;*/
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.apache.commons.io.IOIndexedException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import pidev.entites.Location;
import pidev.entites.Services;
import pidev.services.CrudService;

import utils.MyDb;


/**
 * FXML Controller class
 *
 * @author BLVCK
 */
public class BacckController implements Initializable {

    @FXML
    private Pane PaneBlur;
    @FXML
    private StackPane StckFournisseur;
    @FXML
    private TableView<Services> TableViewFournisseurs;
    @FXML
    private TextField txtSearch;
    @FXML
    private Pane PaneFormulaire;
    @FXML
    private JFXTextArea txtDescriptionFournisseur;
    @FXML
    private JFXTextField txtNomFournisseur;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private JFXTextField txtEmailFournisseur;
    @FXML
    private Text TitreFormulaire;
    @FXML
    private JFXButton btnAjouter;
    @FXML
    private TableColumn<?, ?> col_name;
    @FXML
    private TableColumn<?, ?> col_description;
    @FXML
    private TableColumn<?, ?> col_Title;
    @FXML
    private TableColumn<?, ?> col_Type;
    @FXML
    private TableColumn<?, ?> col_Numl_tel;
    @FXML
    private TableColumn<Services, String> col_Lat;
    @FXML
    private TableColumn<Services, String> col_Lng;
    @FXML
    private TableColumn<Services, String> col_Region;
    @FXML
    private JFXTextField txtNomFournisseur1;
    @FXML
    private JFXTextField txtEmailFournisseur1;
    @FXML
    private JFXTextField txtEmailFournisseur11;
    @FXML
    private JFXTextField txtEmailFournisseur111;
    @FXML
    private JFXTextField txtEmailFournisseur1111;
    Services rec = new Services();
    CrudService work = new CrudService();

    private ObservableList<Services> ListServices;
    @FXML
    private Button editIcon;
    @FXML
    private Button deleteIcon;
    @FXML
    private Button imprimer;
    @FXML
    private Label lab_name;
    @FXML
    private Label lab_Title;
    @FXML
    private Label lab_desc;
    @FXML
    private Label lab_type;
    @FXML
    private Label lab_num;
    @FXML
    private Label lab_lat;
    @FXML
    private Label lab_lng;
    @FXML
    private Label lab_region;
    @FXML
    private Button exeel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTableServices();
        /////imprimer/*////
        imprimer.setOnAction(event -> {

            // Création du job d'impression.
            final PrinterJob printerJob = PrinterJob.createPrinterJob();
            // Affichage de la boite de dialog de configation de l'impression.    
            if (printerJob.showPrintDialog(TableViewFournisseurs.getScene().getWindow())) {
                final JobSettings settings = printerJob.getJobSettings();
                final PageLayout pageLayout = settings.getPageLayout();
                final double pageWidth = pageLayout.getPrintableWidth();
                final double pageHeight = pageLayout.getPrintableHeight();
                System.out.println(Printer.getAllPrinters());
                // Mise en page, si nécessaire.
                // Lancement de l'impression.
                if (printerJob.printPage(TableViewFournisseurs)) {
                    // Fin de l'impression.
                    printerJob.endJob();
                }
            }

        });

    }

    @FXML
    private void OpenFormulaireAdd(MouseEvent event) {
        txtDescriptionFournisseur.clear();
        txtNomFournisseur.clear();
        txtEmailFournisseur.clear();
        btnAjouter.toFront();
        TitreFormulaire.setText("Ajouter un Service");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
    }

    @FXML
    private void ModifierFournisseurClicked(MouseEvent event) {
        int id = 0;
        if (TableViewFournisseurs.getSelectionModel().getSelectedItem() != null) {
            id = Integer.valueOf((TableViewFournisseurs.getSelectionModel().getSelectedItem().getId()));
        }
        if (txtNomFournisseur.getText().isEmpty()) {
            txtNomFournisseur.requestFocus();
            shake(txtNomFournisseur);
            return;
        }
        if (txtNomFournisseur1.getText().isEmpty()) {
            txtNomFournisseur1.requestFocus();
            shake(txtNomFournisseur1);
            return;
        }

        if (txtDescriptionFournisseur.getText().isEmpty()) {
            txtDescriptionFournisseur.requestFocus();
            shake(txtDescriptionFournisseur);
            return;
        }
        if (txtEmailFournisseur.getText().isEmpty()) {
            txtEmailFournisseur.requestFocus();
            shake(txtEmailFournisseur);
            return;

        }
        if (txtEmailFournisseur1.getText().isEmpty()) {
            txtEmailFournisseur1.requestFocus();
            shake(txtEmailFournisseur1);
            return;

        }
        if (txtEmailFournisseur11.getText().isEmpty()) {
            txtEmailFournisseur11.requestFocus();
            shake(txtEmailFournisseur11);
            return;

        }
        if (txtEmailFournisseur111.getText().isEmpty()) {
            txtEmailFournisseur111.requestFocus();
            shake(txtEmailFournisseur111);
            return;

        }
        if (txtEmailFournisseur1111.getText().isEmpty()) {
            txtEmailFournisseur1111.requestFocus();
            shake(txtEmailFournisseur1111);
            return;

        }
        Location ll = new Location();
        rec.setId(id);
        rec.setName(txtNomFournisseur.getText());
        rec.setTitle(txtNomFournisseur1.getText());
        rec.setDescription(txtDescriptionFournisseur.getText());
        rec.setType(txtEmailFournisseur.getText());
        rec.setNum_Tel(Integer.parseInt(txtEmailFournisseur1.getText()));
        ll.setId(id);
        ll.setLat(Double.parseDouble(txtEmailFournisseur11.getText()));
        ll.setLng(Double.parseDouble(txtEmailFournisseur111.getText()));
        ll.setRegion(txtEmailFournisseur1111.getText());
        rec.setLocation(ll);

        Boolean result = work.ModifierService(rec);
    

        if (result) {
            txtNomFournisseur.clear();
            txtNomFournisseur1.clear();
            txtDescriptionFournisseur.clear();
            txtEmailFournisseur.clear();
            txtEmailFournisseur1.clear();
            txtEmailFournisseur11.clear();
            txtEmailFournisseur111.clear();
            txtEmailFournisseur1111.clear();
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
            LoadTableServices();
        }
        
        LoadTableServices();
    }

    public static void shake(Node node) {
        new Shake(node).play();
    }

    @FXML
    private void AjouterFournisseurClicked(MouseEvent event) {
        if (txtNomFournisseur.getText().isEmpty()) {
            txtNomFournisseur.requestFocus();
            lab_name.setText("name is empty");
            shake(txtNomFournisseur);
            return;
        }
        if (txtNomFournisseur1.getText().isEmpty()) {
            txtNomFournisseur1.requestFocus();
            shake(txtNomFournisseur1);
            lab_Title.setText("Title is empty");
            return;
        }

        if (txtDescriptionFournisseur.getText().isEmpty()) {
            txtDescriptionFournisseur.requestFocus();
            shake(txtDescriptionFournisseur);
            lab_desc.setText("Description is empty");
            return;
        }
        if (txtEmailFournisseur.getText().isEmpty()) {
            txtEmailFournisseur.requestFocus();
            shake(txtEmailFournisseur);
            lab_type.setText("Type is empty");
            return;

        }
        if (txtEmailFournisseur1.getText().isEmpty()) {
            txtEmailFournisseur1.requestFocus();
            shake(txtEmailFournisseur1);
            lab_num.setText("Phone is empty");
            return;

        }
        if (txtEmailFournisseur11.getText().isEmpty()) {
            txtEmailFournisseur11.requestFocus();
            shake(txtEmailFournisseur11);
            lab_lat.setText("Lat is empty");
            return;

        }
        if (txtEmailFournisseur111.getText().isEmpty()) {
            txtEmailFournisseur111.requestFocus();
            shake(txtEmailFournisseur111);
            lab_lng.setText("Lng is empty");
            return;

        }
        if (txtEmailFournisseur1111.getText().isEmpty()) {
            txtEmailFournisseur1111.requestFocus();
            shake(txtEmailFournisseur1111);
            lab_region.setText("Region is empty");
            return;

        }
        Location ll = new Location();
        rec.setName(txtNomFournisseur.getText());
        rec.setTitle(txtNomFournisseur1.getText());
        rec.setDescription(txtDescriptionFournisseur.getText());
        rec.setType(txtEmailFournisseur.getText());
        rec.setNum_Tel(Integer.parseInt(txtEmailFournisseur1.getText()));
        ll.setLat(Double.parseDouble(txtEmailFournisseur11.getText()));
        ll.setLng(Double.parseDouble(txtEmailFournisseur111.getText()));
        ll.setRegion(txtEmailFournisseur1111.getText());
        rec.setLocation(ll);

        boolean result = work.AjouterService(rec); // Fonction AjoutUser
    
				
				
        if (result) {

            txtNomFournisseur.clear();
            txtNomFournisseur1.clear();
            txtDescriptionFournisseur.clear();
            txtEmailFournisseur.clear();
            txtEmailFournisseur1.clear();
            txtEmailFournisseur11.clear();
            txtEmailFournisseur111.clear();
            txtEmailFournisseur1111.clear();
            //
            PaneFormulaire.setVisible(false);
            PaneBlur.setVisible(false);
                 Notifications notificationBuilder = Notifications.create()
                                                     .title("Service Ajoutée")
                                                     .text("le Service :\n Name ="+rec.getName()+"\n Phone ="+rec.getNumTel()+"")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                     .position(Pos.BOTTOM_RIGHT) ;
        
          notificationBuilder.darkStyle();
         notificationBuilder.showConfirm();
            LoadTableServices();
        }

    }

    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void GoToService(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Bacck.fxml"));
        StckFournisseur.getChildren().removeAll();
        StckFournisseur.getChildren().setAll(menu);
    }

    @FXML
    private void CloseFormulaireClicked(MouseEvent event) {
        PaneFormulaire.setVisible(false);
        PaneBlur.setVisible(false);
        LoadTableServices();
        txtNomFournisseur.clear();
        txtNomFournisseur1.clear();
        txtDescriptionFournisseur.clear();
        txtEmailFournisseur.clear();
        txtEmailFournisseur1.clear();
        txtEmailFournisseur11.clear();
        txtEmailFournisseur111.clear();
        txtEmailFournisseur1111.clear();

    }

    ////////////////////////////////display///////////////////////////////
    private void LoadTableServices() {

        List<Services> listee = new ArrayList<>();

        listee = work.AfficherAllService();
        ObservableList<Services> Liste = FXCollections.observableArrayList(listee);

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        col_Numl_tel.setCellValueFactory(new PropertyValueFactory<>("Num_Tel"));
        col_Lat.setCellValueFactory(new PropertyValueFactory<>("Location"));
        col_Lat.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Services, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Services, String> l) {
                return new SimpleStringProperty(Double.toString(l.getValue().getLocation().getLat()));
            }
        });

        col_Lng.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Services, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Services, String> l) {
                return new SimpleStringProperty(Double.toString(l.getValue().getLocation().getLng()));
            }
        });
        col_Region.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Services, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Services, String> l) {
                return new SimpleStringProperty(l.getValue().getLocation().getRegion());
            }
        });

        ListServices = FXCollections.observableArrayList(listee);
        TableViewFournisseurs.setItems(ListServices);
        ////////////

///////////////////////////////////////////////////////////////////// Search
        //  Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Services> filteredData = new FilteredList<>(ListServices, b -> true);

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
                if (fourni.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //AddIcontoTable();
                    return true;
                } else if (fourni.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    // AddIcontoTable();
                    return true;
                } else if (String.valueOf(fourni.getTitle()).indexOf(lowerCaseFilter) != -1) {
                    //AddIcontoTable();
                    return true;
                } else {
                    //AddIcontoTable();
                    return false; // Does not match

                }
            });
        });

        //  Wrap the FilteredList in a SortedList.
        SortedList<Services> sortedData = new SortedList<>(filteredData);
        //  Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TableViewFournisseurs.comparatorProperty());
        //  Add sorted (and filtered) data to the table.
        TableViewFournisseurs.setItems(sortedData);
        //////////////////////////////////////////////////////
        // AddIcontoTable();
    }

    /* private void AddIcontoTable()
    {
     //add cell of button edit 
        Callback<TableColumn<Services, String>, TableCell<Services, String>> cellFoctory;
        cellFoctory = (TableColumn<Services, String> param) -> {
            //make cell containing buttons

            final TableCell<Services, String> cell = new TableCell<Services, String>() {

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
                            txtNomFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getName());
                            txtNomFournisseur1.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getTitle());
                            txtDescriptionFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getDescription());
                            txtEmailFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getType());
                            txtEmailFournisseur1.setText(String.valueOf(TableViewFournisseurs.getSelectionModel().getSelectedItem().getNum_Tel()));
                            txtEmailFournisseur11.setText(String.valueOf(TableViewFournisseurs.getSelectionModel().getSelectedItem().getLocation().getLat()));
                            txtEmailFournisseur111.setText(String.valueOf(TableViewFournisseurs.getSelectionModel().getSelectedItem().getLocation().getLng()));
                            txtEmailFournisseur1111.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getLocation().getRegion());
                            
                            OpenPopupModifier();
                        });
                        Deleteicon.setOnMouseClicked((MouseEvent event) -> {
                            //   System.out.println("icon delete is pressed !");
                            if (TableViewFournisseurs.getSelectionModel().getSelectedItem() != null) {
                                rec = TableViewFournisseurs.getSelectionModel().getSelectedItem();
                                Boolean result = work.SupprimerService(rec.getId());
                                if (result) {
                                    System.out.println("Fournisseur Has Been Deleted ✔");
                                    LoadTableServices();
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
    }*/
    private void OpenPopupModifier() {
        btnModifier.toFront();
        TitreFormulaire.setText("Modifier Le Fournisseur");
        PaneFormulaire.setVisible(true);
        PaneBlur.setVisible(true);
        LoadTableServices();
    }
////////////////

    @FXML
    private void moddd(ActionEvent event) {

        //   System.out.println("icon Edit is pressed !");
        txtNomFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getName());
        txtNomFournisseur1.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getTitle());
        txtDescriptionFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getDescription());
        txtEmailFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getType());
        txtEmailFournisseur1.setText(String.valueOf(TableViewFournisseurs.getSelectionModel().getSelectedItem().getNum_Tel()));
        txtEmailFournisseur11.setText(String.valueOf(TableViewFournisseurs.getSelectionModel().getSelectedItem().getLocation().getLat()));
        txtEmailFournisseur111.setText(String.valueOf(TableViewFournisseurs.getSelectionModel().getSelectedItem().getLocation().getLng()));
        txtEmailFournisseur1111.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getLocation().getRegion());

        OpenPopupModifier();

    }

    @FXML
    private void suppServ(ActionEvent event) {
        //System.out.println("icon delete is pressed !");
        if (TableViewFournisseurs.getSelectionModel().getSelectedItem() != null) {
            rec = TableViewFournisseurs.getSelectionModel().getSelectedItem();
            Boolean result = work.SupprimerService(rec.getId());
            if (result) {
                System.out.println("Service Has Been Deleted ✔");
                LoadTableServices();
            }
        }
    }

    @FXML
    private void exeele(ActionEvent event) throws FileNotFoundException, IOException,IOIndexedException{
      
       CrudService prd=new CrudService();
          List <Services> myList = prd.AfficherAllService();
          try{
//declare file name to be create   
String filename = "C:/Users/Wael Hcine/Downloads/Video/java desktop/PidevV2/Pidev/BalanceE.xls"; 
//creating an instance of HSSFWorkbook class  
HSSFWorkbook workbook = new HSSFWorkbook();  
//invoking creatSheet() method and passing the name of the sheet to be created   
HSSFSheet sheet = workbook.createSheet("liste des Services");   
//creating the 0th row using the createRow() method  
HSSFRow rowhead = sheet.createRow((short)0);  
//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
rowhead.createCell(0).setCellValue("Name");  
rowhead.createCell(1).setCellValue("Description");  
rowhead.createCell(2).setCellValue("Title");  
rowhead.createCell(3).setCellValue("Type");  
rowhead.createCell(4).setCellValue("Numero Telephone");  
/*rowhead.createCell(5).setCellValue("Lat");  
rowhead.createCell(6).setCellValue("Lng"); 
rowhead.createCell(7).setCellValue("Region"); */
  int i =1;
//creating the 1st row  
for (Services c : myList){
HSSFRow row = sheet.createRow((short)i);  
//inserting data in the first row  
row.createCell(0).setCellValue(c.getName());  
row.createCell(1).setCellValue(c.getDescription());  
row.createCell(2).setCellValue(c.getTitle());  
row.createCell(3).setCellValue(c.getType());  
row.createCell(4).setCellValue(c.getNumTel());  
/*row.createCell(6).setCellValue(c.getLocation().setLat());  
row.createCell(7).setCellValue(c.getDescription());  
row.createCell(8).setCellValue(c.getDescription());  */
 i++;
    }
       
FileOutputStream fileOut = new FileOutputStream(filename);  
workbook.write(fileOut); 
System.out.println("succ");
//closing the workbook 
//closing the Stream  
fileOut.close();
workbook.close();  
//prints the message on the console  
System.out.println("Excel file has been generated successfully.");  
    }
catch (Exception e)   
{  
e.printStackTrace();  
}
      
      
    }
    
    

  

   

    private class ImageProduitsCellValueFactory implements Callback<TableColumn.CellDataFeatures<Services, ImageView>, ObservableValue<ImageView>> {

        @Override
        public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Services, ImageView> param) {
            Services item = param.getValue();
            ImageView img = null;

            img = item.getImgViewService();

            return new SimpleObjectProperty<>(img);
        }
    }

    ////////////////
    /* private void loadDate() {
        
        
        
       col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        col_Numl_tel.setCellValueFactory(new PropertyValueFactory<>("Num_Tel"));
        col_Lat.setCellValueFactory(new PropertyValueFactory<>("Location"));
        col_Lat.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Services, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Services, String> l) {
                return new SimpleStringProperty(Double.toString(l.getValue().getLocation().getLat()));
            }
        });

        col_Lng.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Services, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Services, String> l) {
                return new SimpleStringProperty(Double.toString(l.getValue().getLocation().getLng()));
            }
        });
        col_Region.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Services, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Services, String> l) {
                return new SimpleStringProperty(l.getValue().getLocation().getRegion());
            }
        });

        
        //add cell of button edit 
         Callback<TableColumn<Services, String>, TableCell<Services,String>> cellFoctory = (TableColumn<Services, String> param) -> {
            // make cell containing buttons
            final TableCell<Services, String> cell = new TableCell<Services, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            //   System.out.println("icon delete is pressed !");
                            if (TableViewFournisseurs.getSelectionModel().getSelectedItem() != null) {
                                rec = TableViewFournisseurs.getSelectionModel().getSelectedItem();
                                Boolean result = work.SupprimerService(rec.getId());
                                if (result) {
                                    System.out.println("Fournisseur Has Been Deleted ✔");
                                    LoadTableServices();
                                }
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                              //   System.out.println("icon Edit is pressed !");
                            txtNomFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getName());
                            txtNomFournisseur1.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getTitle());
                            txtDescriptionFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getDescription());
                            txtEmailFournisseur.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getType());
                            txtEmailFournisseur1.setText(String.valueOf(TableViewFournisseurs.getSelectionModel().getSelectedItem().getNum_Tel()));
                            txtEmailFournisseur11.setText(String.valueOf(TableViewFournisseurs.getSelectionModel().getSelectedItem().getLocation().getLat()));
                            txtEmailFournisseur111.setText(String.valueOf(TableViewFournisseurs.getSelectionModel().getSelectedItem().getLocation().getLng()));
                            txtEmailFournisseur1111.setText(TableViewFournisseurs.getSelectionModel().getSelectedItem().getLocation().getRegion());
                            
                            OpenPopupModifier();

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         col_Action.setCellFactory(cellFoctory);
         TableViewFournisseurs.setItems(ListServices);
         
         
    }*/
    ///////////
    
 
}
