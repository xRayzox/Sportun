/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxpagination;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 *
 * @author Cool IT Help
 */
public class FXMLDocumentController implements Initializable {
    
      
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Pagination pagination;
    
    File filesJpg[];
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        Stage stage = (Stage) anchor.getScene().getWindow();        
         openDirectoryChooser(stage); // calling method to open dicrectory chooser       
         
         
         //this code will create a page and load inside pagination control
         pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {  // every time when you click pagination button this method will be called
                return createPage(pageIndex);
            }
        });
       
    }
    
    private void openDirectoryChooser(Stage parent) {
        
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(parent);
 
        if (selectedDirectory != null) {
            FilenameFilter filterJpg = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg");
                }
            };
 
            filesJpg = selectedDirectory.listFiles(filterJpg);
            
        }
    }
    
    public VBox createPage(int index) {
 
        ImageView imageView = new ImageView();
 
        File file = filesJpg[index];
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            imageView.setFitWidth(400);
            imageView.setFitHeight(360);
           // imageView.setPreserveRatio(true);
            
            imageView.setSmooth(true);
            imageView.setCache(true);
        } catch (IOException ex) {
            
        }
         
        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);        
        
        return pageBox;
    }  
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        
        
    }    
    
    
    
    
 
}
