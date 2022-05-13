/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Article;
import model.Commentaire;
import utils.MyDb;

/**
 *
 * @author alaagha
 */
public class ServiceCommentaire implements IService<Commentaire>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Commentaire t) {
    try {
        String querry= "INSERT INTO `commentaire` (`article_id`, `username`, `text`) VALUES ('"+t.getArticleid()+"', '"+t.getUsername()+"', '"+t.getText()+"');";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

@Override
    public List<Commentaire> afficher() {
     List<Commentaire> commentaires = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `commentaire`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Commentaire c = new Commentaire();
            
            c.setId(rs.getInt(1));
            c.setArticleid(rs.getInt(2));
            c.setText(rs.getString(4));
            c.setUsername(rs.getString(3));
            
            commentaires.add(c);
        }
        
        
        
        return commentaires;
    } catch (SQLException ex) {
                            System.err.println(ex.getMessage());

        }
    return commentaires;
    }
        public List<Commentaire> afficherCommentArticle(int ida) {
     List<Commentaire> commentaires = new ArrayList();
        try {
       
 String querry ="SELECT * FROM `commentaire` WHERE `commentaire`.`article_id` = "+ida;        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Commentaire c = new Commentaire();
            
            c.setId(rs.getInt(1));
            c.setArticleid(rs.getInt(2));
            c.setText(rs.getString(4));
            c.setUsername(rs.getString(3));
            
            commentaires.add(c);
        }
        
        
        
        return commentaires;
    } catch (SQLException ex) {
                            System.err.println(ex.getMessage());

        }
    return commentaires;
        }
    public Commentaire CommentArticle(int t) {
                   Commentaire  a= new Commentaire();
        try {
        String querry ="SELECT * FROM `commentaire` WHERE `commentaire`.`id` = "+t;
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            rs.first();
            a.setId(rs.getInt(1));
            a.setArticleid(rs.getInt(2));
            a.setText(rs.getString(4));
            a.setUsername(rs.getString(3));
            System.out.println(a.toString());

        
        return a;
    } catch (SQLException ex) {
                    System.err.println(ex.getMessage());

        }
    return a;
    }


    @Override
    public void modifier(Commentaire t) {
    try {
        String querry="UPDATE `commentaire` SET `text` = '"+t.getText()+",' WHERE `commentaire`.`id` = "+t.getId()+";";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(querry);
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
       }

    public void supprimer(Commentaire t) {

    try {
        String querry="DELETE FROM `commentaire` WHERE `commentaire`.`id` = "+t.getId();

        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }

    }    
    
}
