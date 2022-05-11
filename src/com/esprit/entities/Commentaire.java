/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author alaagha
 */
public class Commentaire {
 private int id;
    private String username;
    private int articleid;
    private String text;
    
    public Commentaire(int id, String username, int articleid, String text) {
        this.id = id;
        this.username = username;
        this.articleid = articleid;
        this.text = text;
    }

    public Commentaire(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Commentaire(String username, int articleid, String text) {
        this.username = username;
        this.articleid = articleid;
        this.text = text;
    }

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
