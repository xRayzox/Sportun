/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Services;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.mycompany.entites.Location;

/**
 *
 * @author BLVCK
 */
public class ServiceC {
      //initilisation connection request 
    private ConnectionRequest req;
     //singleton 
    public static ServiceC instance = null ;
    
    public static boolean resultOk = true;
    
     public static ServiceC getInstance() {
        if(instance == null )
            instance = new ServiceC();
        return instance ;
    }
         public ServiceC() {
        req = new ConnectionRequest();
        
    }
          //ajout 
    public void ajoutService(Services Service) {
        
        String url =Statics.BASE_URL+"/AddServbox?name="+Service.getName()+"&Num_Tel="+Service.getNum_Tel()+"&Description="+Service.getDescription()+"&title="+Service.getTitle()+"&Type="+Service.getType()+"&lat="+Service.getLocation().getLat()+"&lng="+Service.getLocation().getLng()+"&region="+Service.getLocation().getRegion(); // aa sorry n3adi getId lyheya mech ta3 user ta3 Service
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
//affichage
    
    public ArrayList<Services>affichageServices() {
        ArrayList<Services> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/listService";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapServicess = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapServicess.get("root");
                    System.out.print(listOfMaps);
                    for(Map<String, Object> obj : listOfMaps) {
                        Services re = new Services();
                        Location le = new Location();
                     
                      //Location loc= (Location) obj.get("Location");
                     
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String name = obj.get("name").toString();
                          String Description = obj.get("Description").toString();
                            String Title = obj.get("Title").toString();
                            String Type = obj.get("Type").toString();
                           float Num_Tel = Float.parseFloat(obj.get("Num_tel").toString());
                           
                          double  lat=Float.parseFloat(obj.get("lat").toString());
                           double lng=Float.parseFloat(obj.get("lng").toString());
                           String region=obj.get("region").toString();
                        
                        le.setLat(lat);
                        le.setLng(lng);
                        le.setRegion(region);
                         
                       
                        
                        re.setId((int)id);
                        re.setName(name);
                       re.setNum_Tel((int)Num_Tel);
                       re.setTitle(Title);
                       re.setType(Type);
                       re.setDescription(Description);
                       re.setLocation(le);
                      
                     
                        
                       
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
    
    
    
  /*  //Detail Services bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Services DetailServices( int id , Services Service) {
        
        String url = Statics.BASE_URL+"/detailServices?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                Service.setObjet(obj.get("obj").toString());
                Service.setDescription(obj.get("description").toString());
                Service.setEtat(Integer.parseInt(obj.get("etat").toString()));
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return Service;
        
        
    }
    
    */
    //Delete 
    public boolean deleteServices(int id ) {
        String url = Statics.BASE_URL +"/deleteService?id="+id;
        
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
    
    
    
    //Update 
    public boolean modifierServices(Services Service) {
        String url = Statics.BASE_URL +"/updateService?id="+Service.getId()+"&name="+Service.getName()+"&Num_Tel="+Service.getNum_Tel()+"&Description="+Service.getDescription()+"&title="+Service.getTitle()+"&Type="+Service.getType()+"&lat="+Service.getLocation().getLat()+"&lng="+Service.getLocation().getLng()+"&region="+Service.getLocation().getRegion();
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
     public void Notification(){
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_beep-01a.mp3");
            // alert sound file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
    }
     
     public ArrayList<Services> Search(String searchValue){
//affichage
    
    
        ArrayList<Services> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/search?searchValue="+searchValue;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapServicess = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapServicess.get("root");
                    System.out.print(listOfMaps);
                    for(Map<String, Object> obj : listOfMaps) {
                        Services re = new Services();
                        Location le = new Location();
                     
                      //Location loc= (Location) obj.get("Location");
                     
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String name = obj.get("name").toString();
                          String Description = obj.get("Description").toString();
                            String Title = obj.get("Title").toString();
                            String Type = obj.get("Type").toString();
                           float Num_Tel = Float.parseFloat(obj.get("Num_tel").toString());
                           
                          double  lat=Float.parseFloat(obj.get("lat").toString());
                           double lng=Float.parseFloat(obj.get("lng").toString());
                           String region=obj.get("region").toString();
                        
                        le.setLat(lat);
                        le.setLng(lng);
                        le.setRegion(region);
                         
                       
                        
                        re.setId((int)id);
                        re.setName(name);
                       re.setNum_Tel((int)Num_Tel);
                       re.setTitle(Title);
                       re.setType(Type);
                       re.setDescription(Description);
                       re.setLocation(le);
                      
                     
                        
                       
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
    
}