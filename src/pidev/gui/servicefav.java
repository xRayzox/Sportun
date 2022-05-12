/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;
import pidev.gui.MainApp;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pidev.entites.Fav;
import pidev.entites.Services;
import org.controlsfx.control.Notifications;
import pidev.services.CrudService;
import pidev.services.fav;

/**
 * FXML Controller class
 *
 * @author BLVCK
 */
public class servicefav implements Initializable {

    @FXML
    private AnchorPane Sample;
    @FXML
    private Button utilisateur;
    @FXML
    private ListView<String> listserv;
    @FXML
    private Label label_name;
    @FXML
    private Label label_num_tel;
    @FXML
    private Label label_type;

    CrudService work = new CrudService();
    fav workfav = new fav();
    @FXML
    private Label label_Description;
    @FXML
    private Label label_Title;
    @FXML
    private ImageView qr_image;
    @FXML
    private Button show_map;
    @FXML
    private AnchorPane servicefav;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadTableServices();
       
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

    private void LoadTableServices() {

        List<Services> listee = new ArrayList<>();
        List<String> namee = new ArrayList<>();
        listee = work.AfficherAllService();
        ObservableList<Services> Liste = FXCollections.observableArrayList(listee);
        System.out.println(Liste);
        for (int i = 0; i < Liste.size(); i++) {
            listserv.getItems().addAll(Liste.get(i).getName());
        }
        listserv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                String currentFood = listserv.getSelectionModel().getSelectedItem();
                int number = listserv.getSelectionModel().getSelectedIndex();

                label_name.setText(currentFood);

                label_num_tel.setText(String.valueOf(Liste.get(number).getNumTel()));
                label_type.setText(Liste.get(number).getType());
                label_Description.setText(Liste.get(number).getDescription());
                label_Title.setText(Liste.get(number).getTitle());
                int num = Liste.get(number).getNumTel();
                Double latt = Liste.get(number).getLocation().getLat();
                Double lngg = Liste.get(number).getLocation().getLng();

                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                String myWeb = "https://www.google.com/maps/@" + latt + "," + lngg + ",14z";
                int width = 300;
                int height = 300;
                String fileType = "png";

                BufferedImage bufferedImage = null;
                try {
                    BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
                    bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    bufferedImage.createGraphics();

                    Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(0, 0, width, height);
                    graphics.setColor(Color.BLACK);

                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            if (byteMatrix.get(i, j)) {
                                graphics.fillRect(i, j, 1, 1);
                            }
                        }
                    }

                    System.out.println("Success...");

                } catch (WriterException ex) {
                    Logger.getLogger(servicefav.class.getName()).log(Level.SEVERE, null, ex);
                }

                ImageView qrView = new ImageView();
                qr_image.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

            }

        });
    }

    @FXML
    private void map_view_test(ActionEvent event) throws IOException {
     
          Parent menu = FXMLLoader.load(getClass().getResource("map_test.fxml"));
        Sample.getChildren().removeAll();
        Sample.getChildren().setAll(menu);
          servicefav.getChildren().removeAll();
        servicefav.getChildren().setAll(menu);
 

        
    }
}
