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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.my.utils.Statics;
import com.mycompany.FormesCommande.SessionManager;
import java.util.Map;



/**
 *
 * @author user
 */
public class serviceutili {
     //singleton 
    public static serviceutili instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static serviceutili getInstance() {
        if(instance == null )
            instance = new serviceutili();
        return instance ;
    }
    public serviceutili() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField prenom,TextField nom,TextField email,TextField adresse,TextField telephone,TextField password,TextField confirmpassword,ComboBox<String> roles , Resources res ) {
        
     
        
        String url = Statics.Base_URL+"/signupjson?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&email="+email.getText().toString()
                +"&adresse="+adresse.getText().toString()+"&role="+roles.getSelectedItem()+"&telephone="+telephone.getText().toString()+"&password="+password.getText().toString();
   
        req.setUrl(url);
       
        //Control sais
        if(nom.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {
            
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
      //SignIn
    
    public boolean signin(TextField nom,TextField password, Resources rs ) {
        
        
        String url = Statics.Base_URL+"/signin?nom="+nom.getText()+"&password="+password.getText();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent e) {
                JSONParser j = new JSONParser(); 
                String json = new String(req.getResponseData()) + "";
                try {
                    
                    if(json.equals("failed")) {
                        Dialog.show("Echec d'authentification","Nom ou mot de passe éronné","OK",null);
                        resultOk= false;
                    }
                    else {
                        System.out.println("data =="+json);
                        
                        if(json.contains("found")){
                            resultOk= false;
                        }else{
                            Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        
                    
                        //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setPrenom(user.get("prenom").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setAdresse(user.get("adresse").toString());
//                SessionManager.setTelephone(user.get("telephone").toString());
                        //photo
                        
                       /* if(user.get("photo") != null)
                            SessionManager.setPhoto(user.get("photo").toString());*/
                        
                        
                        if(user.size() >0 ) // l9a user
                            // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                            
                        resultOk=true;
                        }
                        
                    }
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
    
    
    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.Base_URL+"/login?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }

}