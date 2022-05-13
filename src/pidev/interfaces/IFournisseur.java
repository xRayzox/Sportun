/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.interfaces;

import java.util.List;
import pidev.entites.Fournisseur;

/**
 *
 * @author yassin
 */
public interface IFournisseur<P> {

    public boolean AjouterFournisseur(P p);

    public List<Fournisseur> AfficherAllFournisseur(P p);

    public boolean SupprimerFournisseur(int idFournisseur);

}
