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
import static com.esprit.ServiceCommentaire.resultOk;
import com.esprit.entities.Article;
import utils.Statics;
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
public class ServiceArticle {
        public boolean resultOK;
    public ConnectionRequest req;
 private static ServiceArticle instance;
    public ServiceArticle(ConnectionRequest req) {
        this.req = req;
    }
    private ServiceArticle(){
        req=new ConnectionRequest();      
    }

    public static ServiceArticle getInstance() {
        if(instance == null){
            instance=new ServiceArticle();
        }
        return instance;
    }
    public boolean addArticle(Article article){
        String url=Statics.BASE_URL+"/addarticlejss?titre="+article.getTitre()+"&description="+article.getDescription()+"&media="+article.getMedia()+"&tag="+article.getTag()+"&text="+article.getText();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e)-> {
        String str= new String (req.getResponseData ());
            System.out.println("data == "+str);
                });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
  public ArrayList<Article>affichageArticle() {
        ArrayList<Article> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/articlesListjs";
       // String url = Statics.BASE_URL+"/displayarticles";
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapArticle = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapArticle.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps) {
                        Article re = new Article();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String titre = obj.get("titre").toString();
                        String description = obj.get("description").toString();
                        String media=obj.get("media").toString();
                        String createdAt=obj.get("created_at").toString();
                        String tag = obj.get("tag").toString();
                        String text = obj.get("text").toString();
                        float commentairet = Float.parseFloat(obj.get("commentaires").toString());
                        float like = Float.parseFloat(obj.get("like").toString());
                        re.setId((int)id);
                        re.setTitre(titre);
                        re.setDescription(description);
                        re.setMedia(media);
                        re.setTag(tag);
                        re.setText(text);
                        re.setCommentairesnb((int)commentairet);
                        re.setLikenb((int)like);
                        re.setCreatedAt(createdAt);
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
 public Article singleArticle(Article article) {
        
        String url = Statics.BASE_URL+"/detailarticlejson?"+article.getId();
        req.setUrl(url);
        Article re=new Article();
        String str  = new String(req.getResponseData());
        
req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                
                try {
                    Map<String,Object>maArticles = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) maArticles.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps) {
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        String titre = obj.get("titre").toString();
                        String description = obj.get("description").toString();
                        String media=obj.get("media").toString();
                        String createdAt=obj.get("created_at").toString();
                        String tag = obj.get("tag").toString();
                        String text = obj.get("rext").toString();
                        float commentairet = Float.parseFloat(obj.get("commentaires").toString());
                        float like = Float.parseFloat(obj.get("like").toString());

                        
                        re.setId((int)id);
                        re.setTitre(titre);
                        re.setDescription(description);
                        re.setMedia(media);
                        re.setTag(tag);
                        re.setText(text);
                        re.setCommentairesnb((int)commentairet);
                        re.setLikenb((int)like);


                       re.setCreatedAt(createdAt);
                        
                        //insert data into ArrayList result
                        
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return re;
     }
 
 //supp
     public boolean deleteArticle(int id ) {
         boolean resultOk = false;
        String url = Statics.BASE_URL +"/SupprimerArticlejs?id="+id;
        
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
    public void likeo(int id){
        String url = Statics.BASE_URL +"/articlelikejs?id="+id+"&user=";
        int iduser = 1;
        Boolean isliked=false;
        Article articlike=new Article();
        String js;
        articlike.setLikedu(false);
        req.setUrl(url+iduser);
                req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapLike = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    Map<String,Object> listM =  (Map<String,Object>) mapLike.get("root");
                    
                                        String msg = listM.get("message").toString();
                                        articlike.setLikedu(true);
                                        if(msg.equalsIgnoreCase("Liked"))
                                        {
                                        articlike.setLikedu(true);
                                        return;
                                        }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
              NetworkManager.getInstance().addToQueueAndWait(req);

              return;


    }
    public boolean LikePost(int id ,int iduser) {
           String url = Statics.BASE_URL +"/publication/"+id+"/likejs/"+iduser;
        
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
        
}