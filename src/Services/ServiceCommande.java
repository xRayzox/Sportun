/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Commande;
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
public class ServiceCommande implements IService<Commande>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Commande t) {
    try {
        String querry= "INSERT INTO commandem(`nom_utilisateur`, `nom_produit`,`total`) VALUES ('"+t.getNom_utilisateur()+"','"+t.getNom_produit()+"','"+t.getTotal()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    String querrya1 ="SELECT * FROM `commandem` ORDER BY `id` DESC LIMIT 1";
        
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
    public ObservableList<Commande> afficher() {
     ObservableList<Commande> personnes = FXCollections.observableArrayList();
        try {
       
        String querrya ="SELECT * FROM `commandem` ORDER BY `id` ASC";
        String querrya1 ="SELECT * FROM `commandem` ORDER BY `id` DESC LIMIT 1";
        
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querrya);
        while (rs.next()){
            Commande p = new Commande();
            
            p.setId(rs.getInt(1));
            p.setNom_utilisateur(rs.getString(2));
            p.setNom_produit(rs.getString(3));
            p.setTotal(rs.getFloat(4));
            personnes.add(p);
        }
        
        
        
        return personnes;
    } catch (SQLException ex) {
        }
    return personnes;
    }

    @Override
    public void modifier(Commande t,Commande t1) {
        
        try {
            Statement stm = cnx.createStatement();
            
        String querrymodifier="UPDATE `commandem` SET `nom_utilisateur` = '"+t1.getNom_utilisateur()+"', `nom_produit` = '"+t1.getNom_produit()+"', `total` = '"+t1.getTotal()+"' WHERE `commandem`.`id` = '"+t.getId()+"';";             
        stm.executeUpdate(querrymodifier);
        
        // ----> facultatif debut : juste pour que l'objet utlisisé soit mis a jour lors de l'execution
            t.setNom_utilisateur(t1.getNom_utilisateur());
            t.setNom_produit(t1.getNom_produit());
            t.setTotal(t1.getTotal());
        // ----> facultatif fin
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        
        
    }

    @Override
    public void supprimer(Commande t) {
        
        try {
        Statement stm = cnx.createStatement();
           // DELETE FROM `commandem` WHERE `commandem`.`id` = 122
           String querrydelete="DELETE FROM `commandem` WHERE `commandem`.`id` = '"+t.getId()+"';";             

            //String querrymodifier="UPDATE `commandem` SET `nom_utilisateur` = '"+t.getNom_utilisateur()+"', `nom_produit` = '"+t1.getNom_produit()+"', `total` = '"+t1.getTotal()+"' WHERE `commandem`.`id` = '"+t.getId()+"';";             
            stm.executeUpdate(querrydelete);

             }
              catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

   
}
