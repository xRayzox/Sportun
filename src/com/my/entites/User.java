/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.entites;

/**
 *
 * @author user
 */
public class User {
    
    private int id;
    private String password;
    private String email;     
    private String telephone;         
    private String adresse;
    private String nom;
    private String prenom;
    private String  diplome;                                            
    private String role	;
    private String activation_token;	
    private boolean status;	

    public User() {
    }
    
    public User(int id, String password, String email, String telephone, String adresse, String nom, String prenom, String diplome, String role, String activation_token, boolean status) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.nom = nom;
        this.prenom = prenom;
        this.diplome = diplome;
        this.role = role;
        this.activation_token = activation_token;
        this.status = status;
    }

    public User(String password, String email, String telephone, String adresse, String nom, String prenom, String diplome, String role, String activation_token, boolean status) {
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.nom = nom;
        this.prenom = prenom;
        this.diplome = diplome;
        this.role = role;
        this.activation_token = activation_token;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDate(String dateString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
