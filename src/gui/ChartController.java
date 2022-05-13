/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ChartController implements Initializable {

    private BarChart<?, ?> promotion;
    @FXML
    private StackPane StckFrontProduit;
    @FXML
    private Pane PaneBlur;
    @FXML
    private PieChart statistique;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Hackaton",50 ,"%"));
        set1.getData().add(new XYChart.Data("Equitation",40,"%"));
        set1.getData().add(new XYChart.Data("Club santé",10,"%"));
        set1.getData().add(new XYChart.Data("Sport",80,"%"));
        set1.getData().add(new XYChart.Data("Hackaton",60,"%"));
    promotion.getData().addAll(set1);
    }    

    @FXML
    private void GoToFournisseur(MouseEvent event) {
    }

    @FXML
    private void GoToProduit(MouseEvent event) {
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) {
    }

    @FXML
    private void CloseWindowClicked(MouseEvent event) {
    }
    
}
