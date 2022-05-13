/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportunjava;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import java.lang.Object;
import java.lang.String;
import javafx.application.Application;
import javafx.scene.image.Image;
import javax.swing.text.StyleConstants;
/**
 *
 * @author alaagha
 */
public class SportunJava extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
//                String filtred="";
//        
//        try {
//            OkHttpClient client = new OkHttpClient().newBuilder().build();
//            
//            MediaType mediaType = MediaType.parse("text/plain");
//            RequestBody body = RequestBody.create(mediaType, "mother fucker bitch");
//            
//            Request request = new Request.Builder()
//                    .url("https://api.apilayer.com/bad_words?censor_character=*")
//                    .addHeader("apikey", "XSMFQDr2F7FEEYvsg2TpxnE7CXeF4lvb")
//                    .method("POST", body)
//                    .build();
//            Response response = client.newCall(request).execute();
//                        JSONObject jArray = new JSONObject(response.body().string());
//                     //               System.out.println(response.body().string());
//                  String deviceId = (String) jArray.toString();
//                  System.out.println(deviceId);
//                 System.out.println(jArray.get("censored_content"));
//                    
////  JSONObject efaf =response.json();
//                    //  String jsonString =response.body().string();
////JSONObject obj = new JSONObject(jsonString);
////filtred = obj.getString("censored_content");
////           Gson gson = new Gson(); 
////Article entity = gson.fromJson(responseBody.string(), Article.class);
////
////Assert.assertNotNull(entity);
////Assert.assertEquals(.getName(), entity.getName());
//               } catch (IOException ex) {
//            Logger.getLogger(SportunJava.class.getName()).log(Level.SEVERE, null, ex);
//        }
              // launch(args);
        // TODO code application logic here
//                 Scanner s = new Scanner(System.in);
//
//        ServiceArticle sa =  new ServiceArticle();
//                ServiceCommentaire sc =  new ServiceCommentaire();
//                        Commentaire c=new Commentaire();
//                       Commentaire nc=new Commentaire("ag",3,"good job");
//                       Commentaire mc=new Commentaire(26,"good job");
//
//        Article a=new Article(9);
//        Article art=new Article("nice work", "sports", "tunisian habbits with sports", "tunsports.jpg", "qmlflmkfqmlkqfkmlfqkmlqfmlfql");
//        Article artmodif=new Article(9,"nice work city", "sportun", "tunisian habbits with sports rajhhggghhg", "tunsportsuppdate.jpg", "qmlflmkfqmlkqfkmlfqkmlqflkkjkjmlfqlaaaaaa");
//        PostLikes p=new PostLikes(3, 1);
//        System.out.println(sc.afficher());
        //System.out.println(sa.ArticlleDetail(a).toString());
        //ajout mrigla
        
        //sa.ajouter(art);
        //sa.modifier(artmodif);
        
               //affichage yemchi mrigl
        
//        
          //  System.out.println(sa.afficher().toString());
          
//        List<Article> k = sa.tri();
//             for(Article elem: k)
//       {
//       	 System.out.println (elem.toString());
//       }

 //List<Article>rl=sa.recherche("n");
   //     System.out.println(rl);
   
//        List<Article> r = sa.filtreByTag("sports");
//             for(Article elem: r)
//       {
//       	 System.out.println (elem.toString());
//       }
// 
        //System.out.println(art);
        
        //sa.ArticlleDetail(9);
               
        //supprimer temchi mrigla
        //sa.Poslikes(5);
        //            System.out.println(sa.PoslikedByUser(5, 1));
      //  sa.supprimerlikes(5, 1);
        //sa.liker(p);
        
        //sa.supprimer(a);
//                       System.out.println(sc.afficher().toString());
//                       sc.ajouter(nc);
//                       sc.modifier(mc);
//                       //sc.supprimer(mc);
                      //                        System.out.println(sc.afficherCommentArticle(3).toString());

//                                              
//           System.out.print("Veuillez saisir l'opération a/ajouter s/supprimer m/modifier A/afficher : ");
//            String enter1 = s.nextLine();
//if(enter1.equals("A")){
//                    System.out.print("voulez vous afficher article ou commentaire ?:");
//                    String type = s.nextLine();
//                    if(type.equals("article")){
//                    System.out.println(sa.afficher().toString());
//                    }else if(type.equals("commentaire")){
//                    System.out.println(sc.afficher().toString());
//                    }
//            }else if(enter1.equals("a")){
//                System.out.print("vous allez saisir article ou commentaire ?:");
//                String type = s.nextLine();
//                    if(type.equals("article")){
//                        System.out.print("saisir le titre :");
//                        String titre = s.nextLine();
//                        System.out.print("saisir le tag :");
//                        String tag = s.nextLine();
//                        System.out.print("saisir le description :");
//                        String desc = s.nextLine();
//                        System.out.print("saisir le nom de image :");
//                        String img = s.nextLine();  
//                        System.out.print("saisir le text :");
//                        String txt = s.nextLine();                       
//                        Article artcle =new Article(titre,tag, desc,img,txt);
//                        sa.ajouter(artcle);
//                    }else if(type.equals("commentaire")){
//                        System.out.print("saisir le commentaire :");
//                        String comm = s.nextLine();
//                              System.out.print("saisir le commentaire :");
//                        int artid = Integer. parseInt(s.nextLine());
//
//                        Commentaire t =new Commentaire("ag",artid,comm);
//                        sc.ajouter(t);
//                    }else{
//                        System.out.println("choix uncorrecte!");
//                    }
//            }else if(enter1.equals("s")){
//                System.out.print("voulez vous supprimer un article ou un commentaire ?:");
//                String type2 = s.nextLine();
//                if(type2.equals("article")){
//                    System.out.println(sa.afficher().toString());
//                    System.out.print("choisir l'id du article :");
//                    int articleid = Integer. parseInt(s.nextLine());
//                    
//                   // sa.supprimer(articleid);
//                }else if(type2.equals("commentaire")){
//                    System.out.println(sc.afficher().toString());
//                    System.out.print("choisir le teamname du team :");
//                    int commentaireid = Integer. parseInt(s.nextLine());
//                   // sc.supprimer(commentaireid);
//                }else{
//                    System.out.println("choix uncorrecte!");
//                }
//            }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
     try {
          // URL fxURL = getClass().getResource("../gui1/ReservationServiceGUI.fxml");
            //URL fxURL = getClass().getResource("../gui1/ReservationPackGUI.fxml");
          // URL fxURL = getClass().getResource("../gui1/GestiondesReservations.fxml");
         // URL fxURL = getClass().getResource("../GUI/SingleAritcleFronto.fxml");
               URL fxURL = getClass().getResource("../GUI/ListArticleFrontoo.fxml");
               //                  URL fxURL = getClass().getResource("../GUI/AjouterArticleFXML.fxml");

              //URL fxURL = getClass().getResource("../gui1/Payment.fxml");
            Parent root = FXMLLoader.load(fxURL);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("SPORTUN");
            StyleConstants.setIcon(a, Image(new Image() ));
            primaryStage.show();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }    }

    
     public String filter(String msg){
        String filtred="";
        
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "mother fucker");
            
            Request request = new Request.Builder()
                    .url("https://api.apilayer.com/bad_words?censor_character={censor_character}")
                    .addHeader("apikey", "XSMFQDr2F7FEEYvsg2TpxnE7CXeF4lvb")
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
            ResponseBody responseBody = response.body();
           String jsonString =response.body().string();
JSONObject obj = new JSONObject(jsonString);
filtred = obj.getString("censored_content");

//
//           Gson gson = new Gson(); 
//Article entity = gson.fromJson(responseBody.string(), Article.class);
//
//Assert.assertNotNull(entity);
//Assert.assertEquals(.getName(), entity.getName());
               } catch (IOException ex) {
            Logger.getLogger(SportunJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filtred;
    }
}
