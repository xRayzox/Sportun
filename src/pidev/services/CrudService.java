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
import pidev.entites.Location;
//import pidev.connection.Connection;
import utils.MyDb;

import pidev.entites.Services;
import pidev.interfaces.IService;

public class CrudService implements IService<Services> {
////////////////////////Ajuter///////////////////////////

    @Override
    public boolean AjouterService(Services p) {
        try {
            String requete = "INSERT INTO location (lat,lng,region)"
                    + "VALUES (?,?,?)";
            PreparedStatement pst = MyDb.getInstance().getCnx().prepareStatement(requete);
            pst.setDouble(1, p.getLocation().getLat());
            pst.setDouble(2, p.getLocation().getLng());
            pst.setString(3, p.getLocation().getRegion());
            String requetee = "INSERT INTO Service (location_id,name,description,title,type,num_tel,image)"
                    + "VALUES (LAST_INSERT_ID(),?,?,?,?,?,?)";
            PreparedStatement pst1 = MyDb.getInstance().getCnx().prepareStatement(requetee);
            pst1.setString(1, p.getName());
            pst1.setString(2, p.getDescription());
            pst1.setString(3, p.getTitle());
            pst1.setString(4, p.getType());
            pst1.setInt(5, p.getNum_Tel());
            pst1.setString(6, "ssss");

            pst.executeUpdate();
            pst1.executeUpdate();

            System.out.println("Service été ajouté ✔");
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public List<Services> AfficherAllService() {
        List<Services> ListServices = new ArrayList<>();
        List<Location> ListLocation = new ArrayList<>();
        try {
            String requete = "SELECT name,description,title,type,num_tel,lat,lng,region,location_id FROM `service` join `location` WHERE service.location_id=location.id";
                Statement pst = MyDb.getInstance().getCnx().createStatement();

            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                Services S = new Services();
                Location l = new Location();
                l.setId(rs.getInt(9));
                l.setLat(rs.getFloat(6));
                l.setLng(rs.getFloat(7));
                l.setRegion(rs.getString(8));
                S.setId(rs.getInt(9));
                S.setName(rs.getString(1));
                S.setDescription(rs.getString(2));
                S.setTitle(rs.getString(3));
                S.setType(rs.getString(4));
                S.setNum_Tel(rs.getInt(5));
                S.setLocation(l);

                ListServices.add(S);
                ListLocation.add(l);

            }
            System.out.println(ListServices);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return ListServices;

    }

////////////////////////////////Modifier///////////////////////////////
    public static boolean ModifierService(Services t) {
        try {
            String sql = "UPDATE location SET lat= ?,lng = ?, region = ? WHERE id = ?";
            PreparedStatement preparedStatement = MyDb.getInstance().getCnx().prepareStatement(sql);
            preparedStatement.setDouble(1, t.getLocation().getLat());
            preparedStatement.setDouble(2, t.getLocation().getLng());
            preparedStatement.setString(3, t.getLocation().getRegion());
            preparedStatement.setInt(4, t.getLocation().getId());

            String sql1 = "UPDATE Service SET name= ?,description = ?, title = ? ,type = ? ,num_tel=?,image=? WHERE id = ?";
            PreparedStatement preparedStatement1 = MyDb.getInstance().getCnx().prepareStatement(sql1);
            preparedStatement1.setString(1, t.getName());
            preparedStatement1.setString(2, t.getDescription());
            preparedStatement1.setString(3, t.getTitle());
            preparedStatement1.setString(4, t.getType());
            preparedStatement1.setInt(5, t.getNum_Tel());
            preparedStatement1.setString(6, "ddd");
            preparedStatement1.setInt(7, t.getId());

            System.out.println("Service été modifié ✔");

            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();

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
    public boolean SupprimerService(int idFournisseur) {
        try {
            String requete = "DELETE service , location  FROM `service`  INNER JOIN `location` WHERE service.location_id= location.id and service.id =" + String.valueOf(idFournisseur) + "";
            PreparedStatement pst = MyDb.getInstance().getCnx().prepareStatement(requete);
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
