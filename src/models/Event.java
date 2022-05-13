/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.ObservableList;
import utils.MyDb;

/**
 *
 * @author Lenovo
 */
public class Event {
    private int id;
   
    private String nameevent,descriptionevent,promotion,newprix,image;
    private Date star,end;
    
    public  Event(){
        
    }
  public Event(  String nameevent, String descriptionevent,String promotion, String newprix) {
        
      
        this.nameevent = nameevent;
        this.descriptionevent = descriptionevent;
          this.promotion = promotion;
        this.newprix = newprix;
  }

    public Event(String nameevent, String descriptionevent, String promotion, String newprix, String image) {
        this.nameevent = nameevent;
        this.descriptionevent = descriptionevent;
        this.promotion = promotion;
        this.newprix = newprix;
        this.image = image;
    }
  
    public Event(int id, String nameevent, String descriptionevent  ,String promotion, String newprix,String image,Date star, Date end) {
        this.id = id;
       
        this.nameevent = nameevent;
        this.descriptionevent = descriptionevent;
         this.promotion = promotion;
        this.newprix = newprix;
        this.image = image;
         this.star = star;
          this.end = end;
       
    }

    public Event(String nameevent, String descriptionevent, String promotion, String newprix, String image, Date star, Date end) {
        this.nameevent = nameevent;
        this.descriptionevent = descriptionevent;
        this.promotion = promotion;
        this.newprix = newprix;
        this.image = image;
        this.star = star;
        this.end = end;
    }
    
    
    public Event(   String nameevent,String newprix, String image) {
       
        this.nameevent = nameevent;
    
         this.newprix = newprix;
        this.image = image;
       
        
        
       
    }

    public Event(String text, String text0, String text1, String text2, long time, long time0, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getNewprix() {
        return newprix;
    }

    public void setNewprix(String newprix) {
        this.newprix = newprix;
    }

    public String getNameevent() {
        return nameevent;
    }

    public void setNameevent(String nameevent) {
        this.nameevent = nameevent;
    }

    public String getDescriptionevent() {
        return descriptionevent;
    }

    public void setDescriptionevent(String descriptionevent) {
        this.descriptionevent = descriptionevent;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   

    public Date getStar() {
        return star;
    }

    public void setStar(Date star) {
        this.star = star;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

  public static void GetNames(Connection connection , ObservableList<String> Names){
 
        try {
       
        String querry ="SELECT `nameevent` FROM `event` ";
        Statement stm = MyDb.getInstance().getCnx().createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Names.add(
            new String(rs.getString("nameevent"))
            );
}
        
    
    } catch (SQLException ex) {
       ex.printStackTrace();
        }
    
    
}  

    

}
