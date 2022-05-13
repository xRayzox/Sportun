/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.sql.Date;
/**
 *
 * @author yassin
 */
public class Fournisseur {

    private String  NomFournisseur , DescriptionFournisseur ,EmailFournisseur;
    
    int idFornisseur  ;

    public Fournisseur() {
    }

    public Fournisseur(String NomFournisseur, String DescriptionFournisseur, String EmailFournisseur, int idFornisseur) {
        this.NomFournisseur = NomFournisseur;
        this.DescriptionFournisseur = DescriptionFournisseur;
        this.EmailFournisseur = EmailFournisseur;
        this.idFornisseur = idFornisseur;
    }

    public String getNomFournisseur() {
        return NomFournisseur;
    }

    public void setNomFournisseur(String NomFournisseur) {
        this.NomFournisseur = NomFournisseur;
    }

    public String getDescriptionFournisseur() {
        return DescriptionFournisseur;
    }

    public void setDescriptionFournisseur(String DescriptionFournisseur) {
        this.DescriptionFournisseur = DescriptionFournisseur;
    }

    public String getEmailFournisseur() {
        return EmailFournisseur;
    }

    public void setEmailFournisseur(String EmailFournisseur) {
        this.EmailFournisseur = EmailFournisseur;
    }

    public int getIdFornisseur() {
        return idFornisseur;
    }

    public void setIdFornisseur(int idFornisseur) {
        this.idFornisseur = idFornisseur;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "NomFournisseur=" + NomFournisseur + ", DescriptionFournisseur=" + DescriptionFournisseur + ", EmailFournisseur=" + EmailFournisseur + ", idFornisseur=" + idFornisseur + '}';
    }
             
    
}
