/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.my.entites.Fournisseur;
import com.my.entites.Produit;
import com.my.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_Produit {
    
    public ArrayList<Produit> Coachs;
    public static Service_Produit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Produit() {
        req = new ConnectionRequest();
    }

     
    public static Service_Produit getInstance() {
        if (instance == null) {
            instance = new Service_Produit();
        }
        return instance;
    }
    
    
    
    

    
    
    
    
    
    public ArrayList<Produit> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Produit coach = new Produit();
                
                
                

               float id = Float.parseFloat(obj.get("id").toString());
                coach.setId((int) id);
                
              
               coach.setName(obj.get("name").toString());
               coach.setType(obj.get("type").toString());
               coach.setImage(obj.get("image").toString());
               
               
     

                float prix = Float.parseFloat(obj.get("prix").toString());
                coach.setPrix((int) prix);
                
                float quantity = Float.parseFloat(obj.get("quantity").toString());
                coach.setQuantity((int) quantity);
                
                    
              
               /* float idfournisseur = Float.parseFloat(obj.get("idfournisseur").toString());
                coach.setIdfournisseur((int) idfournisseur);
                */

                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Produit> findAll() {
        String url = Statics.Base_URL + "/produit_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Coachs = parseCoach(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Coachs;
    }
    
    
    
    
    
    
    
    
    
    
    
        public void AddProsuit(Produit c) {
        String url = Statics.Base_URL + "/newproduit_mobile/"+ c.getName()+ "/" + c.getPrix()+ "/" +c.getQuantity()+ "/" + c.getType()+ "/" +c.getIdfournisseur()+ "/" +c.getImage(); //création de l'URL
      //  String url = Statics.BASE_URL + "newproduit_mobile/"+ c.getName()+ "/" + c.getPrix()+ "/" +c.getQuantity()+ "/" + c.getType()+ "/" +c.getImage(); //création de l'URL

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   
    }
    
    
    
    
    
    
        

 public boolean deleteProd(int id) {
        String url = Statics.Base_URL + "/SupprimerProd?id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
                    
        
      
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
    }
    //Affichage
    

 
 
 
 
 
 
 
 
 
 
  
    public ArrayList<Produit> getStat() {
        String url = Statics.Base_URL + "/prd_mobile_stat";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               Coachs = parseStat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return Coachs;
    }


 
 
    
    
 
 
 
  public ArrayList<Produit> parseStat(String jsonText) {
        
        try {
            Coachs = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
          
             
            String LibC = obj.get("name").toString();         
            int  quantite =  (int) Float.parseFloat(obj.get("quantity").toString());
           
   
            Produit ab = new Produit();
            ab.setQuantity((int)quantite);
            ab.setName(LibC);
            Coachs.add(ab);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Coachs;
    }
    
    
   
      //Modifier For
    public boolean ModifierProduit( Produit c) {
        String url = Statics.Base_URL + "/updateProduit/" + c.getId()+ "?name=" + c.getName()+ "&prix=" + c.getPrix()+ "&quantity=" + c.getQuantity()+ "&type=" + c.getType();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        //System.out.println("url ==> " + url);
        //System.out.println("data ==> " + req);
        return resultOK;

    }


    
    
    
    
  
    
}
