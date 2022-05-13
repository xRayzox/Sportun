/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Categorie;
import helpers.DbConnect;
import java.util.Comparator;
import java.util.stream.Collectors;
import utils.MyDb;

/**
 *
 * @author Lenovo
 */
public class ServiceCategorie implements IService<Categorie>{
      private Connection cnx = MyDb.getInstance().getCnx() ;
    
    @Override
    public void ajouter(Categorie c) {
    try {
        String querry= "INSERT INTO categorie(`id`, `name` , `type`) VALUES ('"+c.getId()+"','"+c.getName()+"','"+c.getType()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        
    
}
}
     @Override
    public List<Categorie> afficher() {
     List<Categorie> categories = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `Categorie`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Categorie c = new Categorie();
            
            c.setId(rs.getInt(1));
            c.setName(rs.getString("name"));
            c.setType(rs.getString("type"));
           
            categories.add(c);
}
        
    
    } catch (SQLException ex) {
       System.out.println(ex);
        }
    return categories;
    }

   

     @Override
    public void modifier(Categorie c) {
          try {
        String querry="UPDATE Categorie SET id = '"+c.getId()+"', name = '"+c.getName()+
                "', type = '"+c.getType()+",' WHERE Categorie.`id` = "+c.getId()+";";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(querry);
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }
}

    
    
    
    
    @Override
    public void supprimer(Categorie c) {
 try {
        String querry="DELETE FROM Categorie WHERE Categorie.`id` = "+c.getId();

        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());    
}
}  
    
    
    
    @Override
    public List<Categorie> tri() {
    List<Categorie> categories = afficher();
    /*livreurs.stream()
        
        .map(x -> x.getNomLivreur().substring(0, 1).toUpperCase() + x.getNomLivreur().substring(1))
        // tri par ordre alphabétique
        .sorted()
        .forEach(System.out::println );*/
    List<Categorie> resultat=categories.stream().sorted( Comparator.comparing(Categorie::getName)).collect(Collectors.toList());
      return resultat;
      
}
    
//metier= favoris+suggéré    
    
    
    @Override
    public List<Categorie> recherche(String nomLivreur) {
     List<Categorie> categories = afficher();
    

     List<Categorie> resultat=categories.stream().filter(livreur->nomLivreur.equals(livreur.getName())).collect(Collectors.toList());
        return resultat;   
    }
}
