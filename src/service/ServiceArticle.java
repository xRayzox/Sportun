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
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Article;
import model.Commentaire;
import model.PostLikes;
import utils.MyDb;

/**
 *
 * @author alaagha
 */
public class ServiceArticle implements IService<Article>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Article t) {
    try {
         LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        System.out.println(date);
        String querry= "INSERT INTO `article` (`titre`, `text`, `media`, `decription`, `tag`, `created_at`) VALUES ('"+t.getTitre()+"', '"+t.getText()+"', '"+t.getMedia()+"', '"+t.getDescription()+"', '"+t.getTag()+"', '"+date+"');";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<Article> afficher() {
     List<Article> articles = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `article`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Article a = new Article();
            
            a.setId(rs.getInt(1));
            a.setTitre(rs.getString(2));
            a.setTag(rs.getString(5));            
            a.setDescription(rs.getString(6));
            a.setMedia(rs.getString(4));
            a.setText(rs.getString(3));
            a.setCreatedAt(rs.getString(7));
            
            articles.add(a);
        }
        
        
        
        return articles;
    } catch (SQLException ex) {
                            System.err.println(ex.getMessage());

        }
    return articles;
    }
    public Article ArticlleDetail(int t) {
                    Article a= new Article();
        try {
        String querry ="SELECT * FROM `article` WHERE `article`.`id` = "+t;
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            rs.first();
            a.setId(rs.getInt(1));
            a.setTitre(rs.getString(2));
            a.setTag(rs.getString(5));            
            a.setDescription(rs.getString(6));
            a.setMedia(rs.getString(4));
            a.setText(rs.getString(3));
            a.setCreatedAt(rs.getString(7));
            System.out.println(a.toString());

        
        return a;
    } catch (SQLException ex) {
                    System.err.println(ex.getMessage());

        }
    return a;
    }


    @Override
    public void modifier(Article t) {
    try {
        String querry="UPDATE `article` SET `titre` = '"+t.getTitre()+"', `text` = '"+t.getText()+"', `media` = '"+t.getMedia()+"', `tag` = '"+t.getTag()+",' WHERE `article`.`id` = "+t.getId()+";";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(querry);
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
       }


    public void supprimer(Article t) {

    try {
        String querry="DELETE FROM `article` WHERE `article`.`id` = "+t.getId();

        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }

  }
    
    
    public List<Article> tri() {
    List<Article> articless = afficher();
    List<Article> resultat=articless.stream().sorted( Comparator.comparing(Article::getTag)).collect(Collectors.toList());
      return resultat;
      
}
    /**
     *
     * @param tag
     * @return
     */
    public List<Article> filtreByTag(String tag) {
     List<Article> articles = afficher();
     List<Article> resultat=articles.stream().filter(article->tag.equals(article.getTag())).collect(Collectors.toList());
        return resultat;   
    }

    public List<Article> recherche(String ti) {
     List<Article> articles = afficher();
     List<Article> resultat=articles.stream().filter(article->ti.contains(article.getTitre())).collect(Collectors.toList());
          List<Article> result=articles.stream().filter(article->ti.contains(article.getText())).collect(Collectors.toList());
     List<Article> resulta=articles.stream().filter(article->ti.contains(article.getDescription())).collect(Collectors.toList());
List<Article> newList = Stream.of(resultat, result, resulta).flatMap(Collection::stream).collect(Collectors.toList());
        return newList;   
    }
    
    
    
    public PostLikes Poslikes(int t) {
                    PostLikes a= new PostLikes();
        try {
        String querry ="SELECT * FROM `post_like` WHERE `article_id` = "+t;
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            rs.first();
            a.setId(rs.getInt(1));
           a.setArticleId(rs.getInt(2));
            a.setUserId(rs.getInt(3));
            System.out.println(a.toString());

        
        return a;
    } catch (SQLException ex) {
                    System.err.println(ex.getMessage());

        }
    return a;
    }
    public List<PostLikes> afficherpostlikes(int t) {
     List<PostLikes> likes = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `post_like`WHERE `article_id`="+t;
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            
            PostLikes a = new PostLikes();
            a.setId(rs.getInt(1));
            a.setArticleId(rs.getInt(2));
            a.setUserId(rs.getInt(3));
            likes.add(a);
                        System.out.println(a.toString());
        }
        
        
        
        return likes;
    } catch (SQLException ex) {
                            System.err.println(ex.getMessage());

        }
    return likes;
    }
    public int likesCount(int i){
             List<PostLikes> pos = afficherpostlikes(i);
     int resultat=(int)pos.stream().count();
        System.out.println(resultat);
        return resultat;   
    }
    
    public boolean PoslikedByUser(int t,int idu) {
                    PostLikes a= new PostLikes();
        try {
        String querry ="SELECT * FROM `post_like` WHERE `article_id`="+t+" AND`user`="+idu;
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            rs.first();
            a.setId(rs.getInt(1));
           a.setArticleId(rs.getInt(2));
            a.setUserId(rs.getInt(3));
            System.out.println(a.toString());
            System.out.println(true);

        return true;
    } catch (SQLException ex) {
                    System.err.println(ex.getMessage());

        }
    return false;
    }
    
       public void supprimerlikes(int t,int idu) {

    try {
        String querry="DELETE FROM `post_like` WHERE `article_id`="+t+" AND`user`="+idu;

        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
            System.out.println("suppression valider");

    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }

  } 

    public void liker(PostLikes t) {
    try {
        String querry= "INSERT INTO `post_like` (`article_id`, `user`) VALUES ('"+t.getArticleId()+"', '"+t.getUserId()+"');";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

}
