/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author Mohamed
 */
public interface IServicePanierproduits <T> {
    
    public void  ajouter(T t);
    public List<T> afficher(int id);
    public void  modifier (T t,T t1);
    public void supprimer (T t);
    public int getpanieidd(int id);
    /*
    ....
TO DO  
tri 
recherche 
.... 
    */

}
