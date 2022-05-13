/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import utils.MyDb;

/**
 *
 * @author Lenovo
 */

public class Categorie {
    int id;
    String type;
    String Name;
      private java.sql.Connection cnx = MyDb.getInstance().getCnx() ;
public Categorie( String type, String Name) {
        
        this.type = type;
        this.Name = Name;
    }
      
      
    public Categorie(int id, String type, String Name) {
        this.id = id;
        this.type = type;
        this.Name = Name;
    } 
    public Categorie(String Name) {
        
        this.Name = Name;
    }

     public Categorie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
public static void GetNames(Connection connection , ObservableList<String> Names){
 
        try {
       
        String querry ="SELECT name FROM `categorie` ";
        Statement stm = MyDb.getInstance().getCnx().createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Names.add(
            new String(rs.getString("name"))
            );
}
        
    
    } catch (SQLException ex) {
       ex.printStackTrace();
        }
    
    
}
   
    
}
