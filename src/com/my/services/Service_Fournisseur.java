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
import com.my.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_Fournisseur {
     
    
    public ArrayList<Fournisseur> Coachs;
    public static Service_Fournisseur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Fournisseur() {
        req = new ConnectionRequest();
    }

     
    public static Service_Fournisseur getInstance() {
        if (instance == null) {
            instance = new Service_Fournisseur();
        }
        return instance;
    }
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Fournisseur> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Fournisseur coach = new Fournisseur();
                
                
                

               float id = Float.parseFloat(obj.get("id").toString());
                coach.setId((int) id);
                
              
               coach.setName(obj.get("name").toString());
               
               coach.setDescription(obj.get("description").toString());
               
               coach.setEmail(obj.get("email").toString());
               



                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

        
        
        
        
        
    public ArrayList<Fournisseur> findAll() {
        String url = Statics.Base_URL + "/fournisseur_mobile";
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    public void AddFournisseur(Fournisseur c) {
        String url = Statics.Base_URL + "/newfour_mobile/"+ c.getName()+ "/" + c.getDescription()+ "/" +c.getEmail(); //création de l'URL
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            // Coachs = parseCoach(new String(req.getResponseData()));
              //  System.out.println("coachs"+Coachs);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     //Modifier For
    public boolean ModifierFournisseur( Fournisseur c) {
        String url = Statics.Base_URL + "/updateFournisseur?id=" + c.getId()+ "&name=" + c.getName()+ "&description=" + c.getDescription()+ "&email=" + c.getEmail();
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

    
    
    
    
    
    
    
    
    
    
    

    

 public boolean deleteFour(int id) {
        String url = Statics.Base_URL + "/SupprimerFour?id=" + id;
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
    


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}
