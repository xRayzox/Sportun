/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entitiesCommande;

/**
 *
 * @author Ayoub
 */
public class PasserCommande {
    private int id;
    private String nom_utilisateur;
    private String nom_produit;
    private float total;

    
    
    public PasserCommande() {
        this.id=0;
        this.nom_utilisateur="";
        this.nom_produit="";
        this.total=0;
    }

    public PasserCommande(String nom_utilisateur, String nom_produit, float total) {
        this.nom_utilisateur = nom_utilisateur;
        this.nom_produit = nom_produit;
        this.total = total;
    }
   
    public PasserCommande(int id, String nom_utilisateur, String nom_produit, float total) {
        this.id = id;
        this.nom_utilisateur = nom_utilisateur;
        this.nom_produit = nom_produit;
        this.total = total;
    }

   

    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    
    
    
}
