/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Categorie;
import entities.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Lenovo
 */
public class ServiceCategorie {
       public static ServiceCategorie instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
      public static ServiceCategorie getInstance() {
        if(instance == null )
            instance = new ServiceCategorie();
        return instance ;
    }
    
    
    
    public ServiceCategorie() {
        req = new ConnectionRequest();
        
    }
    
     //ajout 
    public void ajoutCategorie(Categorie categorie) {
        
        String url =Statics.BASE_URL+"/new?type="+categorie.getType()+"&name="+categorie.getName(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
   //affichage
    
   public ArrayList<Categorie>affichageCategories() {
        ArrayList<Categorie> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/categorie";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapEvents = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapEvents.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Categorie re = new Categorie();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                       
                         String type = obj.get("type").toString();
                        String name = obj.get("name").toString();
                      
                        
                        re.setId((int)id);
                        re.setType(type);
                        re.setName(name);
                       
                        
                        //Date 
                       /* String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");*/
                        //String dateString = formatter.format(currentTime);
                        //re.setDate(dateString);
                        
                        //insert data into ArrayList result
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
    
    //Update 
    public boolean modifierCategorie(Categorie categorie) {
        String url = Statics.BASE_URL +"/eddit?id="+categorie.getId()+"&type="+categorie.getType()+"&name="+categorie.getName();
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
    //Delete 
    public boolean deleteCategorie(int id ) {
        String url = Statics.BASE_URL +"/deleteCategorie/"+id;
        
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
     //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Event DetailEvent( int id , Event event) {
        
        String url = Statics.BASE_URL+id+"/detail?";
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                 
                        
                       
                         String nameevent = obj.get("nameevent").toString();
                        String descriptionevent = obj.get("descriptionevent").toString();
                        int promotion =Integer.parseInt(obj.get("promotion").toString());
                        int newprix =Integer.parseInt(obj.get("newprix").toString());
                        
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return event;
        
        
    }
    
}
