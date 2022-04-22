/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Panier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDb;

/**
 *
 * @author Mohamed
 */
public class ServicePanier implements IServicePanier<Panier>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Panier t) {
    try {
        String querry= "INSERT INTO panier(`user_id`) VALUES ('"+t.getUser_id()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    String querrya1 ="SELECT * FROM `panier` ORDER BY `id` DESC LIMIT 1";
        
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
    public List<Panier> afficher() {
     List<Panier> personnes = new ArrayList();
        try {
       
        String querrya ="SELECT * FROM `panier` ORDER BY `id` ASC";
        String querrya1 ="SELECT * FROM `panier` ORDER BY `id` DESC LIMIT 1";
        
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querrya);
        while (rs.next()){
            Panier p = new Panier();
            
            p.setId(rs.getInt(1));
            p.setUser_id(rs.getInt(2));           
            personnes.add(p);
        }
        
        
        
        return personnes;
    } catch (SQLException ex) {
        }
    return personnes;
    }

    @Override
    public void modifier(Panier t,Panier t1) {
        
        try {
            Statement stm = cnx.createStatement();
            
        String querrymodifier="UPDATE `panier` SET `user_id` = '"+"' WHERE `panierproduits`.`id` = '"+t.getUser_id()+"';";             
        stm.executeUpdate(querrymodifier);
        
        // ----> facultatif debut : juste pour que l'objet utlisisé soit mis a jour lors de l'execution
            t.setUser_id(t1.getUser_id());           
        // ----> facultatif fin
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        
        
    }

    @Override
    public void supprimer(int idp) {
        
        try {
        Statement stm = cnx.createStatement();
           // DELETE FROM `Panierproduits` WHERE `Panierproduits`.`id` = 122
            String querrydelete="DELETE FROM `panierproduits` WHERE panier_id="+idp+";";             

            //String querrymodifier="UPDATE `Panierproduits` SET `nom_utilisateur` = '"+t.getNom_utilisateur()+"', `nom_produit` = '"+t1.getNom_produit()+"', `total` = '"+t1.getTotal()+"' WHERE `commandem`.`id` = '"+t.getId()+"';";             
            stm.executeUpdate(querrydelete);

             }
              catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    
}
