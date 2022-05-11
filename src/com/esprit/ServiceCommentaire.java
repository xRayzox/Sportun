/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent; 
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Article;
import com.esprit.entities.Commentaire;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author alaagha
 */
public class ServiceCommentaire {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    //singleton 
    public static ServiceCommentaire instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceCommentaire getInstance() {
        if(instance == null )
            instance = new ServiceCommentaire();
        return instance ;
    }
    
    
    
    public ServiceCommentaire() {
        req = new ConnectionRequest();
        
    }
    
//    
//    //ajout 
    public void ajoutComentaire(Commentaire commentaire) {
        
        String url =Statics.BASE_URL+"/commentaire/addjs/"+commentaire.getText()+"/"+commentaire.getArticleid()+"/"+commentaire.getUsername();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Commentaire>affichageComments() {
        ArrayList<Commentaire> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/commentsListjs"; 
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCommentaires = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCommentaires.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps) {
                        Commentaire re = new Commentaire();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String username = obj.get("username").toString();
                        
                        String text = obj.get("text").toString();
                        
                        
                        re.setId((int)id);
                        re.setUsername(username);
                        re.setText(text);

                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
    }   
    
    public ArrayList<Commentaire>affichagePostComments(int id) {
        ArrayList<Commentaire> resultcom = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/commentaire/"+id; 
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCommentaires = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCommentaires.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps) {
                        Commentaire re = new Commentaire();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String username = obj.get("username").toString();
                        
                        String text = obj.get("text").toString();
                                               
                        re.setId((int)id);
                        re.setUsername(username);
                        re.setText(text);

                        resultcom.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return resultcom;
    }   
    
//    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
//    
//    public Reclamation DetailRecalamation( int id , Reclamation reclamation) {
//        
//        String url = Statics.BASE_URL+"/detailReclamation?"+id;
//        req.setUrl(url);
//        
//        String str  = new String(req.getResponseData());
//        req.addResponseListener(((evt) -> {
//        
//            JSONParser jsonp = new JSONParser();
//            try {
//                
//                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
//                
//                reclamation.setObjet(obj.get("obj").toString());
//                reclamation.setDescription(obj.get("description").toString());
//                reclamation.setEtat(Integer.parseInt(obj.get("etat").toString()));
//                
//            }catch(IOException ex) {
//                System.out.println("error related to sql :( "+ex.getMessage());
//            }
//            
//            
//            System.out.println("data === "+str);
//            
//            
//            
//        }));
//        
//              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//
//              return reclamation;
//        
//        
//    }
    
    
    //Delete 
    public boolean deleteCommentaire(int id ) {
           String url = Statics.BASE_URL +"/deletecommentarejs?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
//    //Update 
    public boolean modifierCommentaire(Commentaire comment) {
        String url = Statics.BASE_URL +"/updateCommentaire?id="+comment.getId()+"&text="+comment.getText();
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
    

    
}

