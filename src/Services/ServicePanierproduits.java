/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Panier;
import Model.Panierproduits;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDb;

/**
 *
 * @author Mohamed
 */
public class ServicePanierproduits implements IServicePanierproduits<Panierproduits>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Panierproduits t) {
    try {
        String querry= "INSERT INTO panierproduits(`panier_id`, `nom_panierproduits`,`prixunitaire`,`quantite`,`description`) VALUES ('"+t.getPanier_id()+"','"+t.getNom_panierproduits()+"','"+t.getPrix_unitaire()+"','"+t.getQuantite()+"','"+t.getDescription()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    String querrya1 ="SELECT * FROM `panierproduits` ORDER BY `id` DESC LIMIT 1";
        
        Statement stm1 = cnx.createStatement();
            ResultSet rs= stm1.executeQuery(querrya1);
        while (rs.next()){
            
            t.setId(rs.getInt(1));
            
        }
    
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    
    @Override
    public ObservableList<Panierproduits> afficher(int id) {
     ObservableList<Panierproduits> personnes = FXCollections.observableArrayList();
        try {
       
        String querrya ="SELECT *FROM panierproduits LEFT JOIN panier ON panierproduits.panier_id = panier.id RIGHT JOIN user ON panier.user_id=user.id WHERE user.id="+id+";";
       // String querrya1 ="SELECT * FROM `panierproduits` ORDER BY `id` DESC LIMIT 1";
        
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querrya);
        while (rs.next()){
            Panierproduits p = new Panierproduits();
            
            p.setId(rs.getInt(1));
            p.setPanier_id(rs.getInt(2));
            p.setNom_panierproduits(rs.getString(3));
            p.setPrix_unitaire(rs.getFloat(4));
            p.setQuantite(rs.getInt(5));
            p.setDescription(rs.getString(6));
            personnes.add(p);
        }
        
        
        
        return personnes;
    } catch (SQLException ex) {
        }
    return personnes;
    }

    @Override
    public void modifier(Panierproduits t,Panierproduits t1) {
        
        try {
            Statement stm = cnx.createStatement();
            
        String querrymodifier="UPDATE `panierproduits` SET `panier_id` = '"+t1.getPanier_id()+"', `nom_panierproduits` = '"+t1.getNom_panierproduits()+"', `prixunitaire` = '"+t1.getPrix_unitaire()+"', `quantite` = '"+t1.getQuantite()+"', `description` = '"+t1.getDescription()+"' WHERE `panierproduits`.`id` = '"+t.getId()+"';";             
        stm.executeUpdate(querrymodifier);
        
        // ----> facultatif debut : juste pour que l'objet utlisisé soit mis a jour lors de l'execution
            t.setPanier_id(t1.getPanier_id());
            t.setNom_panierproduits(t1.getNom_panierproduits());
            t.setPrix_unitaire(t1.getPrix_unitaire());
            t.setQuantite(t1.getQuantite());
            t.setDescription(t1.getDescription());
        // ----> facultatif fin
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        
        
    }

    @Override
    public void supprimer(Panierproduits t) {
        
        try {
        Statement stm = cnx.createStatement();
           // DELETE FROM `Panierproduits` WHERE `Panierproduits`.`id` = 122
           String querrydelete="DELETE FROM `panierproduits` WHERE `Panierproduits`.`id` = '"+t.getId()+"';";             

            //String querrymodifier="UPDATE `Panierproduits` SET `nom_utilisateur` = '"+t.getNom_utilisateur()+"', `nom_produit` = '"+t1.getNom_produit()+"', `total` = '"+t1.getTotal()+"' WHERE `commandem`.`id` = '"+t.getId()+"';";             
            stm.executeUpdate(querrydelete);

             }
              catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    
    @Override
    public int getpanieidd(int id) {
     Panier p = new Panier();
        try {
       
        String querrya ="SELECT * FROM `panier` WHERE panier.user_id="+id+";";        
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querrya);
            
            while (rs.next()){
            p.setId(rs.getInt(1));
            p.setUser_id(rs.getInt(2));           
            }
        
        
        
        return p.getId();
    } catch (SQLException ex) {
        }
    return p.getId();
    }
    
    
    
    
}
