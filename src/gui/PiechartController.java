/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import javafx.scene.chart.PieChart;

import javafx.fxml.Initializable;

import javafx.scene.chart.PieChart;

import javafx.fxml.FXML;



import java.net.URL;

import java.util.ResourceBundle;



import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import models.Event;
import service.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author MAKREM
 */
public class PiechartController implements Initializable {

    @FXML
    private PieChart statistique;

    /**
     * Initializes the controller class.
     */
    
    private Event event ;
    @FXML
    private StackPane StckFrontProduit;
    @FXML
    private Pane PaneBlur;

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    ServiceEvent fs = new ServiceEvent();
  
   
      
       
        ObservableList<PieChart.Data> list=FXCollections.observableArrayList(
//current_user.getidRest
            new PieChart.Data("promo par Event", fs.getPromo()),
                new PieChart.Data("Prix par Event", fs.getPrix())
            //new PieChart.Data("En Ligne " + prcntRec +"%", fs.nbSurEnLigne())
         );
       
        System.out.println("promo par Event" + fs.getPromo() +"%");
       // System.out.println("En Ligne" + prcntRec +"%");
         statistique.setAnimated(true);
         statistique.setData(list);
    }
 @FXML
    private void GoToFournisseur(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("EventFront.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void GoToProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("PieChart.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Mail.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    }    
   
}
