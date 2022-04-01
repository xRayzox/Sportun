/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Image;


import com.codename1.ui.events.ActionListener;
import entities.Categorie;
import entities.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import utils.Statics;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class ServiceEvent {
      public static ServiceEvent instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
      public static ServiceEvent getInstance() {
        if(instance == null )
            instance = new ServiceEvent();
        return instance ;
    }
    
    
    
    public ServiceEvent() {
        req = new ConnectionRequest();
        
    }
    
     //ajout 
    public void ajoutEvent(Event event) {
        
        String url =Statics.BASE_URL+"/newevent?nameevent="+event.getNameevent()+"&descriptionEvent="+event.getDescriptionevent()+"&promotion="+event.getPromotion()+"&newPrix="+event.getNewprix()+"&image="+event.getImage(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
   //affichage
    
   public ArrayList<Event>affichageEvent() {
        ArrayList<Event> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/event";
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
                    Event re = new Event();
                    
                    //dima id fi codename one float 5outhouha
                    float id = Float.parseFloat(obj.get("id").toString());
                    
                    String nameevent = (String) obj.get("nameevent");
                    String descriptionevent = (String) obj.get("descriptionEvent");
                    String promotion = obj.get("promotion").toString();
                    String newprix = (String) obj.get("newprix");
                    String image = obj.get("image").toString();
                    // String star = obj.get("star").toString();
                    //String end = obj.get("end").toString();
                    re.setImage(image);
                    
                    re.setId((int)id);
                    re.setNameevent(nameevent);
                    re.setDescriptionevent(descriptionevent);
                    re.setPromotion(promotion);
                    re.setNewprix(newprix);
                    re.setImage(image);
                    

                    //Date
                   // String DateConverter =  obj.get("star").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                    
                    //Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                    
                    //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
    public boolean modifierEvent(Event event) {
       String url = Statics.BASE_URL +"/editevent?id="+event.getId()+"&nameevent="+event.getNameevent()+"&descriptionEvent="+event.getDescriptionevent()+"&promotion="+event.getPromotion()+"&newPrix="+event.getNewprix();
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
    public boolean deleteEvent(int id ) {
        String url = Statics.BASE_URL +"/deleteevent/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
                    ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Event supprimé !!");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Hallo");
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
