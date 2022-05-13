/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author yassin
 */
public class Produit {

    private String NomProduit, ImageProduit, typeProduit;

    int idProduit, idFournisseur, Quantite, PrixProduit;

    ImageView imgViewProduit;

    public Produit() {

    }

    public Produit(String NomProduit, String ImageProduit, String typeProduit, int idProduit, int idFournisseur, int Quantite, int PrixProduit, ImageView imgViewProduit) {
        this.NomProduit = NomProduit;
        this.ImageProduit = ImageProduit;
        this.typeProduit = typeProduit;
        this.idProduit = idProduit;
        this.idFournisseur = idFournisseur;
        this.Quantite = Quantite;
        this.PrixProduit = PrixProduit;
        this.imgViewProduit = imgViewProduit;
    }
    
    

    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit = NomProduit;
    }

    public String getImageProduit() {
        return ImageProduit;
    }

    public void setImageProduit(String ImageProduit) {
        this.ImageProduit = ImageProduit;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public int getPrixProduit() {
        return PrixProduit;
    }

    public void setPrixProduit(int PrixProduit) {
        this.PrixProduit = PrixProduit;
    }

    public ImageView getImgViewProduit() {
        return imgViewProduit;
    }

    public void setImgViewProduit(ImageView imgViewProduit) {
        this.imgViewProduit = imgViewProduit;
    }

    @Override
    public String toString() {
        return "Produit{" + "NomProduit=" + NomProduit + ", ImageProduit=" + ImageProduit + ", typeProduit=" + typeProduit + ", idProduit=" + idProduit + ", idFournisseur=" + idFournisseur + ", Quantite=" + Quantite + ", PrixProduit=" + PrixProduit + ", imgViewProduit=" + imgViewProduit + '}';
    }

     

}
