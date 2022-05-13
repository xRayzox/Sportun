/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FrontEventController implements Initializable {

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
    @FXML
    private Button btnrecherche;
    @FXML
    private TextField txtrecherche;
    
   Connection cnx  ;
private Image image;
private MyListener myListener;
 private Event eventt;
//Event rec = new Event();
    //ServiceEvent work = new ServiceEvent();
/*private List<Categorie> getData(){
    List<Categorie> Categories = new ArrayList<>();*/
    // ServiceEvent service= new ServiceEvent();
      //   ArrayList<Event> eventss= (ArrayList)service.afficher();
  
   
       private List<Event> getData(){
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
}
       private void setChosenEventCard(Event event){
           eventNameLable.setText(event.getNameevent());
           eventPriceLabel.setText(JavaFXApplicationEvent.CURRENCY + event.getNewprix());
            image = new Image(getClass().getResourceAsStream(event.getImage()));
       eventImg.setImage(image);
          
       }
       
       
       
       
       public List<Event> affichageProducts() {
        List<Event> myList = new ArrayList<>();
        //ObservableList<Product> myList = FXCollections.observableArrayList();
        try {
            String request = "SELECT * FROM Event";
            java.sql.Statement stm = cnx.createStatement();
            ResultSet res= stm.executeQuery(request);
            while (res.next()) {
                Event p = new Event();
                
                p.setNameevent(res.getString("nameevent"));
                p.setNewprix(res.getString("prixevent"));
               
                p.setImage(res.getString("image"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //products.addAll(getData());
        ProductService service = new ProductService();
        products = service.getAll();
        if (products.size() > 0) {
            setChosenProduct(products.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Product product) {

                    setChosenProduct(product);
                }
            };
        }

        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/infernalgames/gui/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(products.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/
       
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Hi");        
      ServiceEvent service = new ServiceEvent();
      
  List<Event> events = new ArrayList<>();
        events = service.afficher();
        System.out.println(events);
        events.addAll(getData());
        if(events.size()>0){
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
           
                AnchorPane anchorpane = fxmlLoader.load();
            
             
             ItemeventController itemeventController = fxmlLoader.getController();
         itemeventController.setData(events.get(i), myListener);
        if(column==3){
            column=0;
            row++;
        }
         
         grid.add(anchorpane, column++, row);
         //
         grid.setMinHeight(Region.USE_COMPUTED_SIZE);
         grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
         grid.setMaxWidth(Region.USE_PREF_SIZE);
         //
         grid.setMinHeight(Region.USE_COMPUTED_SIZE);
         grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
         grid.setMaxHeight(Region.USE_PREF_SIZE);
         
         GridPane.setMargin(anchorpane, new Insets(10));
         
         }
              } catch (IOException e) {
                e.printStackTrace();
            }
         
         
    }
    @FXML
    private void GoToMail(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Mail.fxml"));
       grid.getChildren().removeAll();
       grid.getChildren().setAll(menu);
    }    

    @FXML
    private void recherchebtn(ActionEvent event) {
        ServiceEvent se = new ServiceEvent();
        
        //se.recherche(txtrecherche);
    }
    
}
