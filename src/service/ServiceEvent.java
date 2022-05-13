/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import models.Categorie;
import models.Event;
import utils.MyDb;
import utils.Statics;
/**
 *
 * @author Lenovo
 */
public class ServiceEvent implements IService<Event>{
      private Connection cnx = MyDb.getInstance().getCnx() ;
    
   @Override
    public void ajouter(Event c) {
                         LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
    try {
        String querry= "INSERT INTO `event`(`nameevent`, `descriptionevent`, `promotion`, `newPrix`, `image`, `star`, `end`) "
                + "VALUES ('"+c.getNameevent()+"','"+c.getDescriptionevent()+"','"+c.getPromotion()+"','"+c.getNewprix()+"','"+c.getImage()+"','"+date+"','"+date+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
}}

    
      @Override
    public List<Event> afficher() {
     List<Event> Events = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `event` join `categorie` WHERE event.categorie_id=categorie.id";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Event c = new Event();
            
            c.setId(rs.getInt(1));
            c.setPromotion(rs.getString("promotion"));
            c.setNewprix(rs.getString("newprix"));
            c.setNameevent(rs.getString("nameevent"));
            c.setDescriptionevent(rs.getString("descriptionevent"));
            c.setImage(rs.getString("image"));
             /*c.setStar(rs.getDate("star"));
              c.setEnd(rs.getDate("end"));*/
           
            Events.add(c);
}
        
    
    } catch (SQLException ex) {
       System.out.println(ex);
        }
    return Events;
    }
    
    public List<Event> affichageProducts(Event t) {
        List<Event> myList = new ArrayList<>();
        //ObservableList<Event> myList = FXCollections.observableArrayList();
        try {
            String request = "SELECT `nameevent`, `newPrix`, `image` FROM `event`";
            java.sql.Statement stm = cnx.createStatement();
            ResultSet res= stm.executeQuery(request);
            while (res.next()) {
                Event p = new Event();
                
                p.setNameevent(res.getString("nameevent"));
                p.setNewprix(res.getString("newPrix"));
                p.setImage(res.getString("image"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

   

     @Override
    public void modifier(Event c) {
        /*  try {
        String querry="UPDATE Categorie SET id = '"+c.getId()+"', name = '"+c.getName()+
                "', type = '"+c.getType()+",' WHERE Categorie.`id` = "+c.getId()+";";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(querry);
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }*/
}

    
    
    
    
    @Override
    public void supprimer(Event c) {
 try {
        String querry="DELETE FROM event WHERE event.`id` = "+c.getId();

        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());    
}
}  
    
    
    
    @Override
    public List<Event> tri() {
    List<Event> events = afficher();
    /*livreurs.stream()
        
        .map(x -> x.getNomLivreur().substring(0, 1).toUpperCase() + x.getNomLivreur().substring(1))
        // tri par ordre alphabétique
        .sorted()
        .forEach(System.out::println );*/
    List<Event> resultat=events.stream().sorted( Comparator.comparing(Event::getNameevent)).collect(Collectors.toList());
      return resultat;
      
}
    
    
    
    
    @Override
    public List<Event> recherche(String nom) {
     List<Event> events = afficher();
    

     List<Event> resultat=events.stream().filter(Event->nom.equals(Event.getNameevent())).collect(Collectors.toList());
        return resultat;   
    }

     public int getPromo(){
     String req="SELECT count(promotion) FROM event  ";
     
      int nb =0;
       
        try {
          Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }
     
  public int getPrix(){
     String req="SELECT newPrix FROM event  ";
     
      int nb =0;
       
        try {
          Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }   



   

}