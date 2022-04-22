/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIcommande;

import Model.Panier;
import Model.Panierproduits;
import Model.SendMail;
import Services.ServicePanier;
import Services.ServicePanierproduits;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class FXMLpanierController implements Initializable {

    @FXML
    private TableView<Panierproduits> tvPanier;
    @FXML
    private TableColumn<Panierproduits, Integer> colidpanierproduit;
    @FXML
    private TableColumn<Panierproduits, String> colproduitpanier;
    @FXML
    private TableColumn<Panierproduits, String> coldescriptionpanier;
    @FXML
    private TableColumn<Panierproduits, Float> colprixpanier;
    @FXML
    private TableColumn<Panierproduits, Integer> colquantitepanier;
    @FXML
    private Button increaseQuantite;
    @FXML
    private Button decreaseQuantite;
    @FXML
    private TextField panierUserId;
    @FXML
    private Button deleteAllPanier;
    @FXML
    private TextField tfpanierid;
    @FXML
    private Button redirectCommande;
    @FXML
    private TextField totalpanier;
    @FXML
    private TextField contenupanier;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPanier(14);
    }    
    
    /**
     *
     * @param idu
     */
    public void showPanier(int idu) {
       ServicePanierproduits sp=new ServicePanierproduits();
       ObservableList<Panierproduits> listp=sp.afficher(idu) ;
        
       colidpanierproduit.setCellValueFactory(new PropertyValueFactory<Panierproduits,Integer>("id"));
       colproduitpanier.setCellValueFactory (new PropertyValueFactory<Panierproduits, String>("nom_panierproduits"));
       coldescriptionpanier.setCellValueFactory (new PropertyValueFactory<Panierproduits, String>("description"));
       colprixpanier.setCellValueFactory (new PropertyValueFactory<Panierproduits, Float>("prix_unitaire"));
       colquantitepanier.setCellValueFactory (new PropertyValueFactory<Panierproduits, Integer>("quantite"));
       tvPanier.setItems(listp);
       panierUserId.setText(String.valueOf(idu));
       tfpanierid.setText(String.valueOf(sp.getpanieidd(Integer.parseInt(panierUserId.getText()))));
       String contenu="";
       String Newligne=System.getProperty("line.separator");
       Float total=0f;
       for (int i=0;i<listp.size();i++){           
           if(listp.get(i).getPanier_id()==Integer.parseInt(tfpanierid.getText())){
               contenu+=listp.get(i).getQuantite()+" X "+listp.get(i).getNom_panierproduits()+" + ";
               total=total+listp.get(i).getPrix_unitaire()*listp.get(i).getQuantite();
           }
       }
       
       totalpanier.setText(String.valueOf(total)+" DT");
       contenupanier.setText(contenu.substring(0,(contenu.length()-2)));
       
           
    }

    @FXML
    private void increaseQuantite(ActionEvent event) {
        ServicePanierproduits sp = new ServicePanierproduits();
        Panierproduits panierproduitold = tvPanier.getSelectionModel().getSelectedItem();
        Panierproduits panierproduitnew = panierproduitold;
        panierproduitnew.setQuantite(panierproduitold.getQuantite()+1);
        sp.modifier(panierproduitold, panierproduitnew);
        showPanier(Integer.parseInt(panierUserId.getText()));
    }

    @FXML
    private void decreaseQuantite(ActionEvent event) {
        ServicePanierproduits sp = new ServicePanierproduits();
        Panierproduits panierproduitold = tvPanier.getSelectionModel().getSelectedItem();
        Panierproduits panierproduitnew = panierproduitold;
        panierproduitnew.setQuantite(panierproduitold.getQuantite()-1);
        sp.modifier(panierproduitold, panierproduitnew);
        showPanier(Integer.parseInt(panierUserId.getText()));
    }

    @FXML
    private void deletePanierProduit(ActionEvent event) {
        ServicePanierproduits sp = new ServicePanierproduits();
        Panierproduits panierproduitold = tvPanier.getSelectionModel().getSelectedItem();
        sp.supprimer(panierproduitold);
        showPanier(Integer.parseInt(panierUserId.getText()));
        
    }

    @FXML
    private void deleteAllPanier(ActionEvent event) {
        ServicePanier sp = new ServicePanier();        
        sp.supprimer(Integer.parseInt(tfpanierid.getText()));
        showPanier(Integer.parseInt(tfpanierid.getText()));
    }

    @FXML
    private void redirectCommande(ActionEvent event) {
        
     FXMLLoader loader =new FXMLLoader(getClass().getResource("FXMLcommande.fxml")) ;
     try {
         
            Parent root = loader.load(); //charger les acteurs (button,textfield...)
            FXMLcommandeController cc =loader.getController();//kima jibna l fichier fxml njibou lcontrolleur mta3ha
        cc.pass1(String.valueOf(tfpanierid.getText()),String.valueOf(contenupanier.getText()),String.valueOf(totalpanier.getText()));
     
        redirectCommande.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
     //-----------------------------------{   EMAIL  }-------------------------------------------------------
     String email = "ayoub.mabrouk1@esprit.tn";
        //String cn="Votre commande SPORTUN sera traitée le plutot possible . Merci pour votre confiance ";
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now(); 
        
        //----------------------------------EmailBody
        String cn="<head> <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /></head>"
                + "<body style='font-family:Tahoma;font-size:12px;color: #333333;background-color:#FFFFFF;'>"
                + "<table align='center' border='0' cellpadding='0' cellspacing='0' style='height:842px; width:595px;font-size:12px;'>"
                + "<tr>"
            + "<td valign='top'>"
             + "   <table width='100%' cellspacing='0' cellpadding='0'>"
              + "      <tr>"
               + "         <td valign='bottom' width='50%' height='50'>"
               + "             <div align='left'><img"
                 + "                   src='https://i.pinimg.com/564x/34/be/79/34be790be5ab4e77f8a88058417303d5.jpg' width='100' height='100' />"
                 + "           </div><br />"
                 + "       </td>"

                   + "     <td width='50%'>&nbsp;</td>"
                  + "  </tr>"
               + " </table>"
              + "  <h2>Cher(e)Sportunien(ne)</h2> <br /><br />"
              + "  <table width='100%' cellspacing='0' cellpadding='0'>"
               + "     <tr>"
               + "         <td valign='top' width='35%' style='font-size:12px;'> <strong>Nom : SPORTUNIEN </strong><br />"
                  + "          [Adresse du client] <br />SIRET: [SIRET du client]<br />"

                     + "   </td>"
                    + "    <td valign='top' width='35%'>"
                       + " </td>"
                       + " <td valign='top' width='30%' style='font-size:12px;'>Date de facturation: <br />"
                       + dtf.format(now)+"<label for='current_date' id='current_date'></label> <br />"


                      + "  </td>"

                   + " </tr>"
               + " </table>"
               + " <table width='100%' height='100' cellspacing='0' cellpadding='0'>"
                 + "   <tr>"
               + "         <td>"
                    + "        <div align='center' style='font-size: 20px;font-weight: bold;color:#010563;'><strong> Facture Numero " +String.valueOf(tfpanierid.getText())+ " </strong></div>"
                    + "    </td>"
                   + " </tr>"
               + " </table>"
               + " <table width='100%' cellspacing='0' cellpadding='2' border='3' bordercolor='#4582AE'>"
                   + " <tr>"

                   + "     <td width='35%' bordercolor='#4582AE' bgcolor='#f2f2f2' style='font-size:14px;'>"
                   + "         <strong>Au Nom de</strong>"
                  + "      </td>"
                  + "      <td bordercolor='#4582AE' bgcolor='#f2f2f2' style='font-size:14px;'><strong>Produit Commandé</strong></td>"
                   + "     <td bordercolor='#4582AE' bgcolor='#f2f2f2' style='font-size:14px;'><strong>Total payé</strong></td>"

                  + "  </tr>"
                  + "  <tr>"
                    + "<td valign='top' style='font-size:14px;'>Cher Client</td>"
                    + "<td valign='top' style='font-size:14px;'> produit </td>"
                    + "<td valign='top' style='font-size:14px;'>" +String.valueOf(totalpanier.getText())+ "</td>"
                  + "  </tr>"
                    
           + " </td>"
       + " </tr>"
    + "</table>"
    + "<table width='100%' cellspacing='0' cellpadding='2'>"
       + " <tr>"
       + "     <td width='33%' style='border-top:double medium #CCCCCC;font-size:12px;' valign='top'><b>Sportun</b><br />"
        + "        Eco Fitness<br />"

        + "    </td>"
         + "   <td width='33%' style='border-top:double medium #CCCCCC; font-size:12px;' align='center' valign='top'>"
          + "      Adresse <br />"
           + "     Tunis,Tunisia,près de Esprit<br />"
           + " </td>"

         + "   <td valign='top' width='34%' style='border-top:double medium #CCCCCC;font-size:12px;' align='right'>Telephone&adresse_Email<br /> 73 000 000 <br />sportunservicecommercial@gmail.com<br />"
         + "   </td>"
      + "  </tr>"
  + "  </table>"
    
   + " </table>"
+ "</body>";
        //----------------------------------EndEmailBody

        String sb="Confirmation Commande";
        SendMail.sendMail(email,sb, cn);
        //----------------------------------{   endEMAIL  }-----------------------------------------------------
        
        
    }
    
}
