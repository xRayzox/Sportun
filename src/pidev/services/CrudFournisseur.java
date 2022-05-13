/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev.connection.Connection;
import pidev.entites.Fournisseur;
import pidev.interfaces.IFournisseur;

public class CrudFournisseur implements IFournisseur<Fournisseur> {
////////////////////////Ajuter///////////////////////////
    @Override
    public boolean AjouterFournisseur(Fournisseur p) {
        try {
            String requete = "INSERT INTO fournisseur (name,email,description)"
                    + "VALUES (?,?,?)";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getNomFournisseur());
            pst.setString(2, p.getEmailFournisseur());
            pst.setString(3, p.getDescriptionFournisseur());

            pst.executeUpdate();

            System.out.println("fournisseur été ajouté ✔");
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public List<Fournisseur> AfficherAllFournisseur(Fournisseur t) {
            List<Fournisseur> ListFournisseur = new ArrayList<>();
        try {
            String requete = "SELECT name,email,description,id FROM fournisseur ORDER BY id DESC";
            Statement pst = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Fournisseur r = new Fournisseur();
                r.setNomFournisseur(rs.getString(1));
                r.setEmailFournisseur(rs.getString(2));
                r.setDescriptionFournisseur(rs.getString(3));
                r.setIdFornisseur(rs.getInt(4));

                ListFournisseur.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return ListFournisseur;
    }
////////////////////////////////Modifier///////////////////////////////
    
    public static boolean ModifierFournisseur(Fournisseur t) {
        try {
            String sql = "UPDATE fournisseur SET name= ?,email = ?, description = ? WHERE id = ?";
            PreparedStatement preparedStatement = Connection.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, t.getNomFournisseur());
            preparedStatement.setString(2, t.getEmailFournisseur());
            preparedStatement.setString(3, t.getDescriptionFournisseur());
            preparedStatement.setInt(4, t.getIdFornisseur());

            System.out.println("Fournisseur été modifié ✔");

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }
////////////////////////////////supprimer////////////////
    @Override
    public boolean SupprimerFournisseur(int idFournisseur) {
        try {
            String requete = "DELETE FROM fournisseur where id=" + String.valueOf(idFournisseur) + "";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);

            System.out.println("Fournisseur supprimée ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

  
      

    
 
  
}
