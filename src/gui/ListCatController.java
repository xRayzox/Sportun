/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.jfoenix.controls.JFXComboBox;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;

import models.Categorie;
import service.ServiceCategorie;
import utils.MyDb;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */

public class ListCatController implements Initializable {
ObservableList<String> Names;
    @FXML
    private ListView<String> listcat;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServiceCategorie service= new ServiceCategorie();
         ArrayList<Categorie> categories= (ArrayList)service.afficher();
         
        Names =FXCollections.observableArrayList();
        
       // listcat.setCellFactory(new PropertyValueFactory<>("type"));
        
       
       // idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
       // type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Categorie.GetNames((Connection) MyDb.getCnx(), Names);
    listcat.setItems(Names);
       // dbcat.setItems(Names);
     
            }    

    @FXML
    private void clicmodif(ActionEvent event) {
        
          ServiceCategorie se = new ServiceCategorie();
    // sc.ajouter(new Commande(0, TfNum.getText(),TfRue.getText(),CbVille.getValue(),TfNumTel.getText(),TfNumCarte.getText(),CbMode.getValue()));
        // String catEvent = (String)catEvent.getSelectionModel().getSelectedItem();
         // String cbMode = (String)CbMode.getSelectionModel().getSelectedItem();
         
     Categorie c = new Categorie(listcat.getId());
   se.modifier(c);
        
    }

    @FXML
    private void clicksupp(ActionEvent event) {
        ServiceCategorie se = new ServiceCategorie();
    // sc.ajouter(new Commande(0, TfNum.getText(),TfRue.getText(),CbVille.getValue(),TfNumTel.getText(),TfNumCarte.getText(),CbMode.getValue()));
        // String catEvent = (String)catEvent.getSelectionModel().getSelectedItem();
         // String cbMode = (String)CbMode.getSelectionModel().getSelectedItem();
         
     Categorie c = new Categorie(listcat.getId());
   se.supprimer(c);
        System.out.println("est supprimé");
    }
    
}
