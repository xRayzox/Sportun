/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.object.Animation;
import com.dlsc.gmapsfx.javascript.object.GoogleMap;
import com.dlsc.gmapsfx.javascript.object.InfoWindow;
import com.dlsc.gmapsfx.javascript.object.InfoWindowOptions;
import com.dlsc.gmapsfx.javascript.object.LatLong;
import com.dlsc.gmapsfx.javascript.object.MapOptions;
import com.dlsc.gmapsfx.javascript.object.MapTypeIdEnum;
import com.dlsc.gmapsfx.javascript.object.Marker;
import com.dlsc.gmapsfx.javascript.object.MarkerOptions;
import com.dlsc.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pidev.entites.Services;
import pidev.services.CrudService;
import utils.MyDb;

/**
 * FXML Controller class
 *
 * @author BLVCK
 */
public class maptest implements Initializable , MapComponentInitializedListener{

    @FXML
    private AnchorPane panier_name;
    @FXML
    private AnchorPane Sample;
    @FXML
    private Button utilisateur;
    @FXML
   
    private GoogleMapView mapComponent;
    
        private GoogleMap map;
    
    private GeocodingService geocodingService;
    
    private StringProperty address = new SimpleStringProperty();
        CrudService work = new CrudService();
        private MarkerOptions markerOptions2;
	private Marker myMarker2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  mapComponent.addMapInitializedListener(this);
  
    }    

    @FXML
    private void Home_space(MouseEvent event) {
    }

    @FXML
    private void menu(MouseEvent event) {
        
    }

    @FXML
    private void closewinw(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void mapInitialized() {
      
    geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(36.860199, 10.190500))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(10);

        map = mapComponent.createMap(mapOptions);
        List<Services> listee = new ArrayList<>();
        List<String> namee = new ArrayList<>();
        listee = work.AfficherAllService();
        ObservableList<Services> Liste = FXCollections.observableArrayList(listee);
        System.out.println(Liste);
        for (int i = 0; i < Liste.size(); i++) {
            

        markerOptions2 = new MarkerOptions();
        LatLong markerLatLong2 = new LatLong(Liste.get(i).getLocation().getLat(), Liste.get(i).getLocation().getLng());
        markerOptions2.position(markerLatLong2)
                .title(Liste.get(i).getTitle())
                .visible(true);
        

        myMarker2 = new Marker(markerOptions2);

        map.addMarker(myMarker2);
InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content("<h2 style=color:red;>Name:"+Liste.get(i).getName()+"</h2>"+
        "<h3 style=color:black>Type:"+Liste.get(i).getType()+"</h3>"+
       "<h4 style=color:black>Number phone:"+Liste.get(i).getNumTel()+"</h4>")
                .position(markerLatLong2);

        InfoWindow window = new InfoWindow(infoOptions);

        window.open(map, myMarker2);
       
        }
        
         }
    
        
        
            
}
