/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.interfaces;

import java.util.List;

import pidev.entites.Services;

/**
 *
 * @author yassin
 */
public interface IService<P> {

    public boolean AjouterService(P p);

    public List<Services> AfficherAllService();

    public boolean SupprimerService(int idFournisseur);

}
