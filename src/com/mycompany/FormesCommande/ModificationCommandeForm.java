/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.FormesCommande;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.commande.Commande;
import com.mycompany.entitiesCommande.PasserCommande;

/**
 *
 * @author Ayoub
 */
public class ModificationCommandeForm extends BaseFormC{
    Form current;
    public ModificationCommandeForm (Resources res,PasserCommande r) {
    super ("Newsteed", BoxLayout.y()); //herigate men Newateed w 1 formulaire vertical
    Toolbar tb = new Toolbar (true);
    current = this;
    setToolbar (tb) ;
    getTitleArea().setUIID("Container");
    setTitle ("Passer Votre Commande");
    getContentPane ().setScrollVisible (false);
    
    super.addSideMenu(res);
    
    TextField nom_utilisateur = new TextField(r.getNom_utilisateur() , "Nom :");
    ComboBox<String> cb1=new ComboBox(r.getNom_produit());
    cb1.addItem("Optimum Nutrition Gold");
    cb1.addItem("Bulk Shaker Iconic");
    cb1.addItem("Optimum Nutrition Serious Mass");
    cb1.addItem("EAFIT Pure Whey"); 
    
    nom_utilisateur.setUIID("TextFieldBlack");
    addStringValue ("NomUtilisateur", nom_utilisateur);
    
    
    //nom_utilisateur.setSingleLineTextArea(true);
    this.add(cb1);
    Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom_utilisateur(nom_utilisateur.getText());
           r.setNom_produit(String.valueOf(cb1.getSelectedItem()).toString());
           
           
           float flous = 0;
                    if (cb1.getSelectedItem().equals("Optimum Nutrition Gold")){
                        flous=90;
                    }
                    if (cb1.getSelectedItem().equals("Bulk Shaker Iconic")){
                        flous=30;
                    }
                    if (cb1.getSelectedItem().equals("Optimum Nutrition Serious Mass")){
                        flous=150;
                    }
                    if (cb1.getSelectedItem().equals("EAFIT Pure Whey")){
                        flous=75;
                    }
                    String nomcurrent=String.valueOf(nom_utilisateur.getText());
       
       //appel fonction modfier reclamation men service
       
       if(Commande.getInstance().updateCommande(r)) { // if true
           new ListCommandeForm(res, nomcurrent).show();
       }
        });
       
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListCommandeForm(res, r.getNom_utilisateur()).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                //new FloatingHint(nom_utilisateur),
                createLineSeparator(),               
                
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
       
       
       
       
       
    
    }

    private void addStringValue(String s, Component v) {
        add (BorderLayout.west(new Label (s, "PaddedLabel")).add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));

    }
    
}
