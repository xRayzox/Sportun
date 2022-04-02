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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.my.entites.User;
import com.my.utils.Statics;
import com.mycompany.FormesCommande.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServicesUser {
        
    //singleton 
    public static ServicesUser instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServicesUser getInstance() {
        if(instance == null )
            instance = new ServicesUser();
        return instance ;
    }
    
    
    
    public ServicesUser() {
        req = new ConnectionRequest();
    }
    
         //ajout 
    public void ajoutUser(User user) {
        
        //String url =Statics.BASE_URL+"/addUser?nom="+user.getNom()+"&prenom="+user.getPrenom()+"&email="+user.getEmail()+"&telephone="+user.getTelephone()+"&adresse="+user.getAdresse()+"&password="+user.getPassword(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        String url = Statics.Base_URL+"/addUser?nom="+user.getNom()+"&prenom="+user.getPrenom()+"&email="+user.getEmail()+"&telephone="+user.getTelephone()+"&adresse="+user.getAdresse()+"&password="+user.getPassword();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            //System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    
      //Delete 
    public boolean deleteUser(int id ) {
        String url = Statics.Base_URL+"/supprimerjson?id="+id;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
     //Update 
    public boolean modifierUser(TextField nom,TextField prenom, TextField password , TextField email,TextField adresse,TextField telephone, Resources res) {
      
        String url = Statics.Base_URL+"/modifierjson?id="+SessionManager.getId()+"&nom="+SessionManager.getNom().toString()+
                "&prenom="+SessionManager.getPrenom().toString()+
                "&email="+SessionManager.getEmail().toString()+
                "&adresse="+SessionManager.getAdresse().toString()+"&password="+SessionManager.getPassowrd();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
            
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    
    SessionManager.setNom(nom.getText().toString());
    SessionManager.setPrenom(prenom.getText().toString());
    SessionManager.setEmail(email.getText().toString());
    SessionManager.setAdresse(adresse.getText().toString());
    SessionManager.setTelephone(telephone.getText().toString());
    SessionManager.setPassowrd(password.getText().toString());
    
    return resultOk;
            
            
        
    }
    
    
    
    
    /*SET STATUS */
    public boolean setStatus(User user){
            String url=Statics.Base_URL+"/valider?id="+user.getId()+"&status="+user.getStatus();
            req.setUrl(url);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    
                    resultOk=req.getResponseCode()==200;
                     req.removeResponseCodeListener(this);
                                 }
            });
            
             NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk; 
    }
    
    
    
    /*METHODE D'AFFICHAGE */
    public ArrayList<User> AffichageUsers(){
        
        ArrayList<User> liste = new ArrayList<>();
        String url=Statics.Base_URL+"/array";
        req.setUrl(url);
        req.addResponseListener( new ActionListener<NetworkEvent>(){
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp= new JSONParser();
                
                        try{                            
                            Map<String, Object>mapUsers = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));    
                            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUsers.get("root");
                            
                            for(Map<String, Object> obj : listOfMaps){
                                    
                                User user= new User();
                                float id = Float.parseFloat(obj.get("id").toString());
                                String nom = obj.get("nom").toString();
                                String prenom = obj.get("prenom").toString();
                                String email = obj.get("email").toString();
                                String telephone = "25654654";

                                String adresse = obj.get("adresse").toString();
                                //String status = obj.get("status").toString();                                
                                
                                user.setId((int)id);
                                user.setAdresse(adresse);
                                user.setNom(nom);
                                user.setPrenom(prenom);
                                user.setEmail(email);
                                user.setTelephone(telephone);
                              
                                user.setStatus(false);
                                
                                // insert data into liste 
                                liste.add(user);
                                
                        }  
                        }
                        catch(Exception e){ 
                            
                            e.printStackTrace();
                        }
            }
        
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return liste;
    }
    
    
    
    
    
    
    
    
}





/////////////////////////////////////////////////////