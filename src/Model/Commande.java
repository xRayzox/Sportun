package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohamed
 */
public class Commande {
 private int id ;
 private String nom_utilisateur ,nom_produit ;
 private float total;

    public Commande() {
    }

    public Commande(int id, String nom_utilisateur, String nom_produit,float total) {
        this.id = id;
        this.nom_utilisateur = nom_utilisateur;
        this.nom_produit = nom_produit;
        this.total=total;
    }

    public Commande(String nom_utilisateur, String nom_produit, float total) {
        this.nom_utilisateur = nom_utilisateur;
        this.nom_produit = nom_produit;
        this.total=total;    
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

    public void setNom_produit(String prenom) {
        this.nom_produit = prenom;
    }
    
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom_utilisateur + ", nom_produit=" + nom_produit + ", total=" + total +"\n";
    }
 
 


}
