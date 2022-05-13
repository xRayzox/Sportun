/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

//import com.mysql.jdbc.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import models.Categorie;
import models.Event;
import service.ServiceCategorie;
import service.ServiceEvent;
import utils.MyDb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class EventViewController implements Initializable {

    @FXML
    private TextField nomEvent;
    @FXML
    private TextField descEvent;
    @FXML
    private TextField proEvent;
    @FXML
    private TextField prixEvent;
    @FXML
    private Button addEvent;
    @FXML
    private Button cancelEvent;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Button imgEvent;
    @FXML
    private ComboBox<String> catEvent;
          ObservableList<String> Names;
          private ImageView PreviewImage;
    File selectedFile;
    private FileChooser Fc = new FileChooser();
    private File file;
    private static String pathImage = "";
     //private File FileUtils;
    @FXML
    private StackPane StckFrontProduit;
    @FXML
    private Pane PaneBlur;


    /**
     * Initializes the controller class.
     */
    
    private void handleButtonAction(ActionEvent event1){
        System.out.println("you clicked me");
        addEvent.setText("hii");
      
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remplirCombobox();
       
        ServiceCategorie service= new ServiceCategorie();
         ArrayList<Categorie> categories= (ArrayList)service.afficher();
        Names =FXCollections.observableArrayList();
        
        Categorie.GetNames((com.mysql.jdbc.Connection) MyDb.getCnx(), Names);
    catEvent.setItems(Names);
    }    

    @FXML
    private void btnAjoutEvent(ActionEvent event) {
                 LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
         ServiceEvent se = new ServiceEvent();
    // sc.ajouter(new Commande(0, TfNum.getText(),TfRue.getText(),CbVille.getValue(),TfNumTel.getText(),TfNumCarte.getText(),CbMode.getValue()));
        // String catEvent = (String)catEvent.getSelectionModel().getSelectedItem();
         // String cbMode = (String)CbMode.getSelectionModel().getSelectedItem();
         
     Event c = new Event(nomEvent.getText(),descEvent.getText(),proEvent.getText(),prixEvent.getText(),imgEvent.getText());
   se.ajouter(c);
    TrayNotification tray= new TrayNotification();
        AnimationType anim = AnimationType.POPUP;
        tray.setAnimationType(anim);
        tray.setTitle("Notifications");
        tray.setMessage("Event ajouté avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
    /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("event is added successfully!");
        alert.show();*/
    }

//    @FXML
//    private void Addimage(ActionEvent event) {
//        File dest = new File("C:\\");
//        Fc.setTitle("Open Resource File");
//        Fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("images", ".bmp", ".png", ".jpg", ".gif"));
//        selectedFile = Fc.showOpenDialog(null);
//
//        if (selectedFile != null) {
//            try {
//
//                String Destination = "C:\\";
//                File f = new File(dest, selectedFile.getName());
//
//             //   FileUtils.copyFileToDirectory(selectedFile, dest);
//                pathImage = selectedFile.getName();
//
//                Image image = new Image(new FileInputStream(selectedFile), 200, 200, true, true);
//                PreviewImage.setImage(image);
//            } catch (IOException ex) {
//                ex.getStackTrace();
//            }
//        }
//    }
private void remplirCombobox(){
try {

            String requetee = "SELECT name FROM categorie";
            Statement pstt = MyDb.getInstance().getCnx().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                catEvent.getItems().addAll(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
 @FXML
    private void Addimage(ActionEvent event) throws MalformedURLException {
                FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

                    String path = selectedFile.getName();
          //  ImageIV.setImage(new Image(selectedFile.toURI().toURL().toString()));
            //ImageIV.setFitHeight(150);
           // ImageIV.setFitWidth(250);
            imgEvent.setText(path);
            File tagetFile=new File(Statics.BASE_URL);
            System.out.println(tagetFile.toURI().toURL().toString());
            copyFiles(selectedFile, tagetFile);
        }  
    }
    public static void copyFiles(File sourceLocation , File targetLocation) {

       
                InputStream in = null;
        try {
            in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation+"\\"+sourceLocation.getName());
                        System.out.println(targetLocation+"\\"+sourceLocation.getName());

            // Copy the bits from input stream to output stream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("image ");
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            System.out.println(ex);
            }
        }
            } 
     @FXML
    private void GoToProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("PieChart2.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Mail2.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }
@FXML
    private void gotocategorie(MouseEvent event) throws IOException {
          Parent menu = FXMLLoader.load(getClass().getResource("CategorieView.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }
    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    } 
  @FXML
    private void GoToFournisseur(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("EventView.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }
    
}
