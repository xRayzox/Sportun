/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AyoubCommande;

import Model.Commande;
import Model.Panier;
import Model.Panierproduits;
import Services.ServiceCommande;
import Services.ServicePanier;
import Services.ServicePanierproduits;
import utils.MyDb;

/**
 *
 * @author Mohamed
 */

public class AyoubCommande {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  Commande c1 = new Commande("AyoubMabrouk" , "Bulk Shaker",50);
  Commande c2 = new Commande("AyoubMb123" , "Bulk Shaker",20);
  
  Panierproduits pr1 = new Panierproduits(134,"Bulk Shaker",100f,3,"excellent");
  Panierproduits pr2 = new Panierproduits(134,"Proteines",200f,4,"excellent");
  
  Panier pn1 = new Panier(11);
  Panier pn2 = new Panier(12);

  
  ServicePanierproduits sp =  new ServicePanierproduits();
        System.out.println("----------------------------------------------------------");
        System.out.println("##################### PanierProduits ######################");
        System.out.println("----------------------------------------------------------");
        System.out.println("----------------------{   AJOUT   }-----------------------");
        sp.ajouter(pr1);
        
        System.out.println("----------------------{ AFFICHAGE }-----------------------");
        System.out.println("----------------->> #ALL# ");
        System.out.println(sp.afficher(14).toString());
        
        System.out.println("----------------->> #LAST ONE#");
        System.out.println("-----> AVANT MODIFICATION");
        System.out.println(pr1);               
        System.out.println("----------------------{MODIFICATION}----------------------");
        sp.modifier(pr1,pr2);
        
        System.out.println("-----> APRES MODIFICATION");
        System.out.println(pr1);
        
        System.out.println("-----------------------{SUPPRESSION}----------------------");
        sp.supprimer(pr1);
        
  ServicePanier spn =  new ServicePanier();
        System.out.println("----------------------------------------------------------");
        System.out.println("######################### Panier #########################");
        System.out.println("----------------------------------------------------------");
        System.out.println("----------------------{   AJOUT   }-----------------------");
        spn.ajouter(pn1);
        
        System.out.println("----------------------{ AFFICHAGE }-----------------------");
        System.out.println("----------------->> #ALL# ");
        System.out.println(spn.afficher().toString());
        
        System.out.println("----------------->> #LAST ONE#");
        System.out.println("-----> AVANT MODIFICATION");
        System.out.println(pn1);               
        System.out.println("----------------------{MODIFICATION}----------------------");
        spn.modifier(pn1,pn2);
        
        System.out.println("-----> APRES MODIFICATION");
        System.out.println(pn1);
        
        System.out.println("-----------------------{SUPPRESSION}----------------------");
        //spn.supprimer(pn1);    
  ServiceCommande sc =  new ServiceCommande();
        System.out.println("----------------------------------------------------------");
        System.out.println("######################## Commande ########################");
        System.out.println("----------------------------------------------------------");
        System.out.println("----------------------{   AJOUT   }-----------------------");
        sc.ajouter(c1);
        
        System.out.println("----------------------{ AFFICHAGE }-----------------------");
        System.out.println("----------------->> #ALL# ");
       // System.out.println(sc.afficher().toString());
        
        System.out.println("----------------->> #LAST ONE#");
        System.out.println("-----> AVANT MODIFICATION");
        System.out.println(c1);               
        System.out.println("----------------------{MODIFICATION}----------------------");
        sc.modifier(c1,c2);
        
        System.out.println("-----> APRES MODIFICATION");
        System.out.println(c1);
        
        System.out.println("-----------------------{SUPPRESSION}----------------------");
        sc.supprimer(c1);
    
    }
   
}
