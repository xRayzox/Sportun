/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entites.Location;
//import pidev.connection.Connection;
import utils.MyDb;

import pidev.entites.Fav;
import pidev.entites.Services;

import pidev.interfaces.IServiceFav;

public class fav implements IServiceFav<Fav> {
private Connection cnx = MyDb.getInstance().getCnx() ;
    @Override
    public boolean Ajouter(Fav f) {
    Statement stm;
        try {
        String querry = "INSERT INTO Fav(`serv_id`) VALUES ('" + f.getServices() + "')";
            stm = cnx.createStatement();
            stm.executeUpdate(querry);
               System.out.println("Fav ajoutée ✔");
             return true;

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        return false;
   }

    @Override
    public List<Fav> AfficherAllFav() {
          List<Fav> ListFav = new ArrayList<>();
        
        try {
            String requete = "Select * from Fav";
                Statement pst = MyDb.getInstance().getCnx().createStatement();

            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                Fav f = new Fav();
               
                f.setId(rs.getInt(1));
               
                

                ListFav.add(f);
                

            }
            System.out.println(ListFav);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return ListFav;
    }

    @Override
    public boolean SupprimerFav(int idFav) {
     try {
            String requete = "DELETE FROM Fav where id="+idFav+"";
            PreparedStatement pst = MyDb.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);

            System.out.println("Fav supprimée ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }


}
