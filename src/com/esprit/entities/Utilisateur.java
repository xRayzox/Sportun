/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author Lenovo
 */
//taw n7oto fi description
public class Utilisateur {
    
    private int id;
    private String username;
    private String email;
    private String photo;
    private String nom;
    private String prenom;
    private String motdepasse;

    public Utilisateur(String username, String email, String photo, String nom, String prenom, String motdepasse) {
        this.username = username;
        this.email = email;
        this.photo = photo;
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
    }
    
    

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(String username, String email) {
        this.username = username;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", adresse=" + email + ", photoDeProfil=" + photo + '}';
    }

    public Utilisateur(int id, String username, String email, String photo, String nom, String prenom) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.photo = photo;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur(String username, String email, String photo, String nom, String prenom) {
        this.username = username;
        this.email = email;
        this.photo = photo;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    
    
    
    
}