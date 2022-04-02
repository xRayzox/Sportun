/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.commande;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.utilsCommande.StatisticsCommande;
import com.mycompany.entitiesCommande.PasserCommande;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Ayoub
 */
public class Commande {

    public static void setInstance(Commande instance) {
        Commande.instance = instance;
    }
    public static Commande instance=null;
    
    public static boolean resultOk = true;

    //inisialisation connection request
    private ConnectionRequest req;
    
    
    public static Commande getInstance (){
    if(instance == null )
        instance = new Commande ();
    return instance;
    }
    
    public Commande () {
        req = new ConnectionRequest();
    }
    //---------------------------------------------------
    //----------------------Ajouter----------------------
    //---------------------------------------------------
    public void ajouterCommande(PasserCommande commande){
        String url =StatisticsCommande.BASE_URL+"/ajoutercommandem/"+commande.getNom_utilisateur()+","+commande.getNom_produit()+","+commande.getTotal();
        req.setUrl(url);
        req.addResponseListener((e)->{
           String str=new String(req.getResponseData());
           System.out.println("data --"+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
    //---------------------------------------------------
    //----------------------Afficher---------------------
    //---------------------------------------------------
    
    public ArrayList<PasserCommande>afficherCommande(String nomcurrent){
        ArrayList<PasserCommande> result=new ArrayList<>();
        String url = StatisticsCommande.BASE_URL+"/affichercommandem";
        req.setUrl (url);
        req.setHttpMethod("GET");
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
              
                
                JSONParser jsonp;
                jsonp=new JSONParser();
                
                try {
                    Map<String,Object>mapPasserCommande=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List <Map<String,Object>> ListOfMaps=(List<Map<String,Object>>) mapPasserCommande.get("root");
                
                for (Map<String, Object>obj: ListOfMaps){
                    
                    PasserCommande pc = new PasserCommande ();
                    
                        
                    
                    
                    float id=Float.parseFloat(obj.get("id").toString());
                    String nom_utilisateur=obj.get("nom_utilisateur").toString();
                    String nom_produit=obj.get("nom_produit").toString();
                    float total=Float.parseFloat(obj.get("total").toString());
                    
                    pc.setId((int) id);
                    pc.setNom_utilisateur(nom_utilisateur);
                    pc.setNom_produit(nom_produit);
                    pc.setTotal((float)total);
                    if(pc.getNom_utilisateur().toUpperCase().equals(nomcurrent.toUpperCase()) ){
                    result.add(pc);
                } 
                }
                    
                
                }catch(Exception ex){
                    ex.printStackTrace();
                    
                    
                }
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }
    //---------------------------------------------------
    //----------------------AfficherAll------------------
    //---------------------------------------------------
    
    public ArrayList<PasserCommande>afficherallCommande(){
        ArrayList<PasserCommande> result=new ArrayList<>();
        String url = StatisticsCommande.BASE_URL+"/affichercommandem";
        req.setUrl (url);
        req.setHttpMethod("GET");
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
              
                
                JSONParser jsonp;
                jsonp=new JSONParser();
                
                try {
                    Map<String,Object>mapPasserCommande=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List <Map<String,Object>> ListOfMaps=(List<Map<String,Object>>) mapPasserCommande.get("root");
                
                for (Map<String, Object>obj: ListOfMaps){
                    
                    PasserCommande pc = new PasserCommande ();
                    
                        
                    
                    
                    float id=Float.parseFloat(obj.get("id").toString());
                    String nom_utilisateur=obj.get("nom_utilisateur").toString();
                    String nom_produit=obj.get("nom_produit").toString();
                    float total=Float.parseFloat(obj.get("total").toString());
                    
                    pc.setId((int) id);
                    pc.setNom_utilisateur(nom_utilisateur);
                    pc.setNom_produit(nom_produit);
                    pc.setTotal((float)total);
                    
                    result.add(pc);
                }
                    
                
                }catch(Exception ex){
                    ex.printStackTrace();
                    
                    
                }
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }
    
    //---------------------------------------------------
    //----------------------Delete-----------------------
    //---------------------------------------------------
    
    public boolean deleteCommande(int id ) {
        String url = StatisticsCommande.BASE_URL +"/supprimercommandem?id="+id;
        
        req.setUrl(url);
        req.setHttpMethod("DELETE");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    //---------------------------------------------------
    //----------------------Update-----------------------
    //---------------------------------------------------
    public boolean updateCommande(PasserCommande commande) {
        String url = StatisticsCommande.BASE_URL +"/modifiercommandem/"+commande.getId()+","+commande.getNom_utilisateur()+","+commande.getNom_produit()+","+commande.getTotal();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    //-------------------------------------------------------------------------------
    
   //--------------------------------------------------------------------------------
    //----------------------StatistiqueCompteurPetit---------------------------------
    //-------------------------------------------------------------------------------
    public int  nbpetit =0;
    public int nbgrand =0;
    public int nbpetit(String nomcurrent){
        Commande.instance.nbpetit=0;
        ArrayList<PasserCommande> result=new ArrayList<>();
        String url = StatisticsCommande.BASE_URL+"/affichercommandem";
        req.setUrl (url);
        req.setHttpMethod("GET");
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
              
                
                JSONParser jsonp;
                jsonp=new JSONParser();
                
                try {
                    Map<String,Object>mapPasserCommande=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List <Map<String,Object>> ListOfMaps=(List<Map<String,Object>>) mapPasserCommande.get("root");
                
                for (Map<String, Object>obj: ListOfMaps){
                    
                    PasserCommande pc = new PasserCommande ();
                    
                        
                    
                    
                    float id=Float.parseFloat(obj.get("id").toString());
                    String nom_utilisateur=obj.get("nom_utilisateur").toString();
                    String nom_produit=obj.get("nom_produit").toString();
                    float total=Float.parseFloat(obj.get("total").toString());
                    
                    pc.setId((int) id);
                    pc.setNom_utilisateur(nom_utilisateur);
                    pc.setNom_produit(nom_produit);
                    pc.setTotal((float)total);
                    
                    if(pc.getNom_utilisateur().toUpperCase().equals(nomcurrent.toUpperCase()) ){
                    result.add(pc);
                    nbpetit=nbpetit+1;
                } 
                }
                    
                
                }catch(Exception ex){
                    ex.printStackTrace();
                    
                    
                }
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nbpetit;

    }
    
    
    
    //-------------------------------------------------------------------
    //----------------------StatistiqueCompteurGrand---------------------
    //-------------------------------------------------------------------
    
    public int nbgrand(String nomcurrent){
        
        ArrayList<PasserCommande> result=new ArrayList<>();
        String url = StatisticsCommande.BASE_URL+"/affichercommandem";
        req.setUrl (url);
        req.setHttpMethod("GET");
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
              
                
                JSONParser jsonp;
                jsonp=new JSONParser();
                
                try {
                    Map<String,Object>mapPasserCommande=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List <Map<String,Object>> ListOfMaps=(List<Map<String,Object>>) mapPasserCommande.get("root");
                
                for (Map<String, Object>obj: ListOfMaps){
                    
                    PasserCommande pc = new PasserCommande ();
                    
                        
                    
                    
                    float id=Float.parseFloat(obj.get("id").toString());
                    String nom_utilisateur=obj.get("nom_utilisateur").toString();
                    String nom_produit=obj.get("nom_produit").toString();
                    float total=Float.parseFloat(obj.get("total").toString());
                    
                    pc.setId((int) id);
                    pc.setNom_utilisateur(nom_utilisateur);
                    pc.setNom_produit(nom_produit);
                    pc.setTotal((float)total);
                    
                    if(pc.getNom_utilisateur().toUpperCase().equals(nomcurrent.toUpperCase()) ){
                    result.add(pc);
                    
                } nbgrand=nbgrand+1;
                }
                    
                
                }catch(Exception ex){
                    ex.printStackTrace();
                    
                    
                }
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nbgrand;

    }
    //--------------------------------------------------------------------------
    //------------------------------DeleteAll-----------------------------------
    //--------------------------------------------------------------------------
    public boolean deleteAllCommande(String nom ) {
        String url = StatisticsCommande.BASE_URL +"/viderpanierm/"+nom;
        
        req.setUrl(url);
        req.setHttpMethod("DELETE");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
}
