/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pidev.entites.Produit;
import pidev.interfaces.IProduit;
import pidev.connection.Connection;

public class CrudProduit implements IProduit<Produit> {

    public boolean AjouterProduit(Produit p) {
        try {
            String requete = "INSERT INTO Produit (name,type,quantity,prix,idfournisseur_id,image)"
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getNomProduit());
            pst.setString(2, p.getTypeProduit());
            pst.setInt(3, p.getQuantite());
            pst.setInt(4, p.getPrixProduit());
            pst.setInt(5, p.getIdFournisseur());
            pst.setString(6, p.getImageProduit());

            pst.executeUpdate();

            System.out.println("Produit été ajouté ✔");
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    public List<Produit> AfficherAllProduit(Produit t) {
        List<Produit> ProduitList = new ArrayList<>();
        try {
            String requete = "SELECT idfournisseur_id,name,prix,quantity,type,image,id FROM produit ORDER BY id DESC";
            Statement pst = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Produit r = new Produit();
                ///
                ImageView img = new ImageView();
                Image image;
                try {
                    if (rs.getString("image") == null) {
                    } else if (rs.getString("image") != null) {
                        image = new Image(new FileInputStream(("C:\\xampp\\htdocs\\Projet\\Uploads\\"+rs.getString("image"))));
                        img.setImage(image);
                        img.setPreserveRatio(false);
                        img.setFitWidth(50);
                        img.setFitHeight(50);

                    }
                } catch (FileNotFoundException ex) {
                    try {
                        System.out.println(ex.getMessage());
                        image = new Image(new FileInputStream(("C:\\xampp\\htdocs\\Projet\\Uploads\\"+"nophoto.jpg")));
                        img.setImage(image);
                        img.setPreserveRatio(true);
                        img.setFitWidth(50);
                        img.setFitHeight(50);
                    } catch (FileNotFoundException ex1) {
                        Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                //
                r.setImgViewProduit(img);
                //
                r.setIdFournisseur(rs.getInt(1));
                r.setNomProduit(rs.getString(2));
                r.setPrixProduit(rs.getInt(3));
                r.setQuantite(rs.getInt(4));
                r.setTypeProduit(rs.getString(5));
                r.setImageProduit(rs.getString(6));
                r.setIdProduit(rs.getInt(7));
                ProduitList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return ProduitList;
    }

    public static boolean ModifierProduit(Produit t) {
        try {
            String sql = "UPDATE produit SET name= ?,type = ?, quantity = ?, prix = ?, idfournisseur_id = ? , image = ? WHERE id = ?";
            PreparedStatement preparedStatement = Connection.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setString(1, t.getNomProduit());
            preparedStatement.setString(2, t.getTypeProduit());
            preparedStatement.setInt(3, t.getQuantite());
            preparedStatement.setInt(4, t.getPrixProduit());
            preparedStatement.setInt(5, t.getIdFournisseur());
            preparedStatement.setString(6, t.getImageProduit());
            preparedStatement.setInt(7, t.getIdProduit());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }
//    

    public boolean SupprimerProduit(int idProduit) {
        try {
            String requete = "DELETE FROM produit where id=" + String.valueOf(idProduit) + "";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);

            System.out.println("Produit supprimée ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }
    
    
        public boolean DecrementProduit(int idProduit) {
        try {
            String requete = "UPDATE produit SET quantity = IF(quantity > 0, quantity - 1, 0) where id=" + String.valueOf(idProduit) + "";
            //UPDATE  table SET     quantity = IF(quantity > 0, quantity - 1, 0) WHERE   id = $number
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);

            System.out.println("Buy Product ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

}
