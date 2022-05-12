/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.interfaces;

import java.util.List;
import pidev.entites.Fav;

import pidev.entites.Services;

/**
 *
 * @author yassin
 */
public interface IServiceFav<F> {

    public boolean Ajouter(F f);

    public List<Fav> AfficherAllFav();

    public boolean SupprimerFav(int idFav);

}
