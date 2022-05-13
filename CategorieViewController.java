/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.mysql.jdbc.PreparedStatement;
import com.sun.source.doctree.DocTree;
import helpers.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Categorie;
import utils.MyDb;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Event;
import org.controlsfx.control.Notifications;
import service.ServiceCategorie;
import service.ServiceEvent;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;





/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class CategorieViewController implements Initializable {
    
   

    ObservableList<Categorie>  CategorieList = FXCollections.observableArrayList();
    @FXML
    private TextField nomCat;
    @FXML
    private TextField TypeCat;
    @FXML
    private Button bntAjoutCat;
    @FXML
    private Button btnUpdate;
    @FXML
    private ListView<String> listviewcat;
    ObservableList<String> Names;
    @FXML
    private Button btnsupp;
    @FXML
    private StackPane StckFrontProduit;
    @FXML
    private Pane PaneBlur;
    
    private void handleButtonAction(ActionEvent event1){
        System.out.println("you clicked me");
        bntAjoutCat.setText("hii");
        
       /* Notifications notificationBuilder = Notifications.create()
                .title("Great job !!").text("Continue please")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
        public void handle (ActionEvent event){
            System.out.println("Clicked on notification");
        }
    });
        notificationBuilder.showConfirm();*/
        
    }
    public void initialize(URL url, ResourceBundle rb) {
       // loadData();
       ServiceCategorie service= new ServiceCategorie();
         ArrayList<Categorie> categories= (ArrayList)service.afficher();
         
        Names =FXCollections.observableArrayList();
        
       // listcat.setCellFactory(new PropertyValueFactory<>("type"));
        
       
       // idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
       // type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Categorie.GetNames((com.mysql.jdbc.Connection) MyDb.getCnx(), Names);
    listviewcat.setItems(Names);
    }    
    
    
   

  /* private void refreshTable() {
        try {
            CategorieList.clear();
            
            query = "SELECT * FROM `Categorie`";
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                CategorieList.add(new  Categorie(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("type")));
                
                //studentsTable.setItems(CategorieList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }*/
   /* private void loadData() {
    connection = MyDb.getInstance().getCnx() ;
        refreshTable();
        
        name.getText();
        type.getText();
     
    }*/

    @FXML
    private void btnAddCategorie(ActionEvent event) {
        
          ServiceCategorie se = new ServiceCategorie();
    // sc.ajouter(new Commande(0, TfNum.getText(),TfRue.getText(),CbVille.getValue(),TfNumTel.getText(),TfNumCarte.getText(),CbMode.getValue()));
        // String catEvent = (String)catEvent.getSelectionModel().getSelectedItem();
         // String cbMode = (String)CbMode.getSelectionModel().getSelectedItem();
          
     Categorie c = new Categorie(nomCat.getText(),TypeCat.getText());
     se.ajouter(c);
    // listviewcat.getItems().add(c);
     
     TrayNotification tray= new TrayNotification();
        AnimationType anim = AnimationType.POPUP;
        tray.setAnimationType(anim);
        tray.setTitle("Notifications");
        tray.setMessage("Catégorie ajouté avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5)); 
      //((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    
        
   //se.ajouter(c);
   /* Notifications notificationBuilder = Notifications.create()
                .title("Great job !!").text("Continue please")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
        public void handle (ActionEvent event){
            System.out.println("Clicked on notification");
        }
    });
        notificationBuilder.showConfirm();*/
        
    /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Categorie is added successfully!");
        alert.show();*/
    }

   

    @FXML
    private void btnUpdatecat(ActionEvent event) {
         
            ServiceCategorie sc = new ServiceCategorie();
        
      Categorie c =  new Categorie(nomCat.getText(),TypeCat.getText());
      
      //String cbMode = (String)id.getSelectionModel().getSelectedItem();
        System.out.println(c);
      sc.modifier(c);
        
   
      /*  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Categorie is modified successfully!");
        alert.show();*/
        
    }

    @FXML
    private void btnsuppcat(ActionEvent event) {
    }
    
       @FXML
    private void GoToFournisseur(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("EventView.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
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
    }
    
