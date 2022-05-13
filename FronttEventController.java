/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import models.Categorie;
import models.Event;
import service.ServiceEvent;
import utils.MyDb;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FronttEventController implements Initializable {

    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnrecherche;
    @FXML
    private VBox chosenEventCard;
    @FXML
    private Label eventNameLable;
    @FXML
    private Label eventPriceLabel;
    @FXML
    private ImageView eventImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
  Connection cnx  ;
private Image image;
private MyListener myListener;
 Event rec = new Event();
 List<Event> events = new ArrayList<>();
 ServiceEvent work = new ServiceEvent();
    /**
     * Initializes the controller class.
     */
 
  /*private List<Event> getData(){
           List<Event> events = new ArrayList();
           Event event;
  
    for(int i=0 ; i<20 ;i++){
       
        event = new Event();
        event.getNameevent();
       event.getNewprix();
       event.getImage();
       events.add(event);
      
    }
    return events;
}*/
  
    private void setChosenEventCard(Event event){
           eventNameLable.setText(event.getNameevent());
           eventPriceLabel.setText(JavaFXApplicationEvent.CURRENCY + event.getNewprix());
            image = new Image(getClass().getResourceAsStream(event.getImage()));
       eventImg.setImage(image);
          chosenEventCard.setStyle("""
                                   -fx-background-color: # ;
                                       -fx-background-radius: 30;""");
       }
    
    //ObservableList<String> Names;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //affichageProducts();
        // System.out.println(affichageProducts());        
      //ServiceEvent service = new ServiceEvent();
        System.out.println("Hi");
      //affichageProducts();
  List<Event> events = new ArrayList<>();
        events = work.affichageProducts(rec);
        //System.out.println("hhh "+events);
        //events.addAll(affichageProducts());
        
         
      
      
        if(!events.isEmpty()){
            
            setChosenEventCard(events.get(0));
           myListener = new MyListener(){
                @Override
                public void onClickListener(Event event) {
                 setChosenEventCard(event);
                }
               
           }; 
                   }
        int column=0;
        int row=1;
        
          try {
              for(int i=0 ; i< events.size() ;i++){
             FXMLLoader fxmlLoader = new FXMLLoader();
             fxmlLoader.setLocation(getClass().getResource("itemevent.fxml"));
           
                AnchorPane anchorPane = fxmlLoader.load();
            
             
             ItemeventController itemeventController = fxmlLoader.getController();
         itemeventController.setData(events.get(i), myListener);
        if(column==3){
            column=0;
            row++;
        }
         
         grid.add(anchorPane, column++, row);
         //
         grid.setMinHeight(Region.USE_COMPUTED_SIZE);
         grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
         grid.setMaxWidth(Region.USE_PREF_SIZE);
         //
         grid.setMinHeight(Region.USE_COMPUTED_SIZE);
         grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
         grid.setMaxHeight(Region.USE_PREF_SIZE);
         
         GridPane.setMargin(anchorPane, new Insets(10));
         
         }
              } catch (IOException e) {
                e.printStackTrace();
            }
         
         
    
    }  
    
      
     

    @FXML
    private void recherchebtn(ActionEvent event) {
    }

    @FXML
    private void GoToMail(MouseDragEvent event) throws IOException { 
        Parent menu = FXMLLoader.load(getClass().getResource("Mail.fxml"));
      
    }

    @FXML
    private void GoToMail(MouseEvent event) {
    }
    
}
