/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import pidev.entites.Location;
import pidev.entites.Services;
import utils.MyDb;
import pidev.services.CrudService;

/**
 *
 * @author BLVCK
 */
public class Crud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //MyDb a= MyDb.getInstance();
       
       //System.out.println(a.hashCode());
         CrudService s =  new CrudService();
         
       //System.out.println(s.afficher().toString());
       
       Location l1=new Location (44,55,"wwwww");
       Location l2=new Location (424,66666,"zzzzzz");
       Services S = new Services(1223123,"ddd", "sss", "ddd", "dddd", l1);
       Services S2 = new Services(9999999,"www", "ww", "ww", "wwwww", l1);
       Services S3 = new Services(5,2345667,"www", "ww", "ww", "wwwww", l2);
        Location l4=new Location (7,3,6,"z");
       Services S4 = new Services(7,11111,"tawww", "aa", "aaa", "aaa", l4);
       //s.AjouterService(S2);
      // s.ajouter(S2);
       s.AfficherAllService();
       //s.SupprimerService(6);
       //s.supprimer(80);
       //s.ModifierService(S4);
      
    }
    
}
