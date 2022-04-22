/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIcommande;

import Model.Commande;
import Services.ServiceCommande;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import GUIcommande.FXMLpanierController;
import javafx.scene.control.TextArea;
/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class FXMLcommandeController implements Initializable {

    @FXML
    private TextField txpanieridd;
    @FXML
    private TextField txidCommande;
    @FXML
    private TextField txnomCommande;
    @FXML
    private TextField txproduitCommande;
    @FXML
    private TextField txtotalCommande;
    @FXML
    private TableView<Commande> tvCommande;
    @FXML
    private TableColumn<Commande, Integer> colid;
    @FXML
    private TableColumn<Commande, String> colnom;
    @FXML
    private TableColumn<Commande, String> colproduit;
    @FXML
    private TableColumn<Commande, Float> coltotal;
    @FXML
    private Button btnAjouterCommande;
    @FXML
    private Button btnModifierCommande;
    @FXML
    private Button btnSupprimerCommande;
    @FXML
    private TextField total;
    @FXML
    private TextArea contenu;

    
    
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         showCommande();
        // System.out.println(FXMLpanierController.tfpanierid.getText());
    }    

    /*private void ajouterpersonne(ActionEvent event) {
        ServiceCommande sc = new ServiceCommande();
        sc.ajouter(new Commande(fxnom.getText(),fxnom.getText(),50f));
    }*/
    
            
    private void affichePersonne(ActionEvent event) {
        ServiceCommande sc = new ServiceCommande();
       // affichageCommande.setText(sc.afficher().toString());
    }
    public void showCommande () {
       ServiceCommande sc=new ServiceCommande();
       ObservableList<Commande> listc=sc.afficher() ;
       //ObservableList<Commande> listc=sc.afficher(Integer.parseInt(String.valueOf(txpanieridd.getText()))) ;

       colid.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));
       colnom.setCellValueFactory (new PropertyValueFactory<Commande, String>("nom_utilisateur"));
       colproduit.setCellValueFactory (new PropertyValueFactory<Commande, String>("nom_produit"));
       coltotal.setCellValueFactory (new PropertyValueFactory<Commande, Float>("total"));
       tvCommande.setItems(listc);
       
    }
    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Commande commande =tvCommande.getSelectionModel().getSelectedItem();
    txidCommande.setText(String.valueOf(commande.getId()));
    txnomCommande.setText(commande.getNom_utilisateur());
    txproduitCommande.setText(commande.getNom_produit());
    txtotalCommande.setText(String.valueOf(commande.getTotal()));
    }

    @FXML
    private void updateCommande(ActionEvent event) {
        Commande commandeOld =tvCommande.getSelectionModel().getSelectedItem();
        Commande commandenew=new Commande(txnomCommande.getText(),txproduitCommande.getText(),parseFloat(txtotalCommande.getText()));
        ServiceCommande sc=new ServiceCommande();
        sc.modifier(commandeOld, commandenew);
        txidCommande.setText("");
        txnomCommande.setText("");
        txproduitCommande.setText("");
        txtotalCommande.setText("");
        showCommande();
    }

    @FXML
    private void deleteCommande(ActionEvent event) {
        ServiceCommande sc=new ServiceCommande();
        Commande commandeOld =tvCommande.getSelectionModel().getSelectedItem();
        sc.supprimer(commandeOld);
        txidCommande.setText("");
        txnomCommande.setText("");
        txproduitCommande.setText("");
        txtotalCommande.setText("");
        showCommande();
    }

    @FXML
    private void addCommande(ActionEvent event) {
        ServiceCommande sc=new ServiceCommande();
        Commande commandenew=new Commande(txnomCommande.getText(),txproduitCommande.getText(),parseFloat(txtotalCommande.getText()));
        sc.ajouter(commandenew);
        txidCommande.setText("");
        txnomCommande.setText("");
        txproduitCommande.setText("");
        txtotalCommande.setText("");
        showCommande();
    }

    /**
     *
     * @param idpanierd
     * @param contenuu
     * @param totall
     */
    public  void pass1(String idpanierd,String contenuu,String totall){
        txpanieridd.setText(idpanierd);
        contenu.setText(contenuu);
        total.setText(totall);
    }
   
    
    

}

