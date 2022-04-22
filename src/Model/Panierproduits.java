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
public class Panierproduits {
 private int id,quantite,panier_id ;
 private String nom_panierproduits ,description ;
 private float prix_unitaire;

 
 
    public Panierproduits() {
    }

    public Panierproduits(int id,int panier_id, String nom_panierproduits, float prix_unistaire,int quantite,String description) {
        this.id = id;
        this.panier_id = panier_id;
        this.nom_panierproduits = nom_panierproduits;
        this.prix_unitaire = prix_unistaire;
        this.quantite = quantite;
        this.description = description;
    }
    public Panierproduits(int panier_id, String nom_panierproduits, float prix_unistaire,int quantite,String description) {
        this.panier_id = panier_id;
        this.nom_panierproduits = nom_panierproduits;
        this.prix_unitaire = prix_unitaire;
        this.quantite = quantite;
        this.description = description;    
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(int panier_id) {
        this.panier_id = panier_id;
    }

    public String getNom_panierproduits() {
        return nom_panierproduits;
    }

    public void setNom_panierproduits(String nom_panierproduits) {
        this.nom_panierproduits = nom_panierproduits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(float prix_unistaire) {
        this.prix_unitaire = prix_unistaire;
    }

    
    
@Override
    public String toString() {
        return "Panierproduits{" + "id=" + id + ", panier_id=" + panier_id + ", nom_panierproduits=" + nom_panierproduits + ", prix_unistaire=" + prix_unitaire+ ", quantite=" + quantite+ ", description=" + description +"\n";
    }
    
 


}
