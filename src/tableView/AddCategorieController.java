/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableView;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import helpers.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import models.Categorie;

/**
 *
 * @author Lenovo
 */
class AddCategorieController implements Initializable {
 
  @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField type;
   

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Categorie Categorie = null;
    private boolean update;
    int CategorieId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void save(MouseEvent event) {

        connection = DbConnect.getConnect();
        //String name = name.getText();
        
        //String type = type.getText();
        

      /*  if (name.isEmpty() || type.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }

    }

    @FXML
    private void clean() {
        name.setText(null);
       
        type.setText(null);
       
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `Categorie`( `name`, `type`) VALUES (?,?)";

        }else{
            query = "UPDATE `student` SET "
                    + "`name`=?,"
                    + "`type`=?,"
                  
                    + " WHERE id = '"+CategorieId+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name.getText());
           
            preparedStatement.setString(3, type.getText());
          
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(int id, String name, String type) {

       CategorieId = id;
        name.setText(name);
        
        type.setText(type);
        

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
  */
}}
