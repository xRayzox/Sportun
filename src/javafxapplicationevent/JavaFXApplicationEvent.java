/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplicationevent;

import helpers.DbConnect;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Categorie;
import models.Event;
import service.ServiceCategorie;
import service.ServiceEvent;
import utils.MyDb;



/**
 *
 * @author Lenovo
 */
public class JavaFXApplicationEvent extends Application{
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(  final Stage primaryStage) throws Exception{
        
       // Parent root = FXMLLoader.load(getClass().getResource("/javafxpagination/FXMLDocument.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/gui/CategorieView.fxml"));
      
        
       // primaryStage.setTitle("sendEmail");
       // FXMLLoader loader = new FXMLLoader();
       // loader.setLocation(JavaFXApplicationEvent.class.getResource("/utils/Mail.fxml"));
        //Parent root = loader.load();
         Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        /*pagination = new Pagination(10);
        pagination.setStyle("-fx-border-color:blue");
        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
        
        AnchorPane anchor = new AnchorPane();
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        
        anchor.getChildren().add(pagination);
        Scene scene = new Scene(anchor);
        primaryStage.setScene(scene);
        primaryStage.show();
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tableView/tableView.fxml"));
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(JavaFXApplicationEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      primaryStage.setTitle("JavaFX Tutorial / Pagination");
          Pagination pagination = new Pagination();
          pagination.setPageCount(35);
          pagination.setCurrentPageIndex(4);
          pagination.setMaxPageIndicatorCount(3);
          
          pagination.setPageFactory((pageIndex)->{
            Label label1 = new Label("Content for page with Index : "+ pageIndex);
            label1.setFont(new Font("Arial",36));
            
            Label label2 = new Label("Main Content of Code Amir");
            label2.setFont(new Font("Arial" , 20));    
            return new VBox(label1,label2);
  });
        VBox vBox = new VBox(pagination);
        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();  */
  }
    
/**
     * @param args the command line arguments
     */
    
   // public static void main(String[] args) {
       //launch(args);
    
      // MyDb a= MyDb.getInstance();
       
      // System.out.println(a.hashCode());
         //ServiceCategorie s =  new ServiceCategorie();
        // ServiceEvent s2 =  new ServiceEvent();
         //Categorie c1 = new Categorie (4, "java", "sprint2");
       //  Event c2 = new Event ("2", "java", "sprint2","hi","hi");
        //s.ajouter(c1);
        // System.out.println(s2.afficher());
         //System.out.println(s.tri());
//System.out.println(s.recherche("sprint2"));

//s.supprimer(c1);
//s.modifier(c1);
    

   /* public VBox createPage(int pageIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    VBox pageBox = new VBox();
    Label pageLabel = new Label("page :"+(pageIndex+1));
    pageBox.getChildren().add(pageLabel);
    return pageBox;
    
    }*/

   
    
}
        //}
