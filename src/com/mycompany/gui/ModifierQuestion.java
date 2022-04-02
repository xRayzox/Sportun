/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Form;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceC;
import com.mycompany.entites.Services;
import com.mycompany.entites.Location;

/**
 *
 * @author Moez
 */
public class ModifierQuestion extends BaseForm {
    
    Form current;
    public ModifierQuestion(Resources res , Services r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField contenu = new TextField(r.getName() , "Objet" , 20 , TextField.ANY);
        
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
       
        
        
        
        
        
        
        
        contenu.setUIID("NewsTopLine");
        
        
        contenu.setSingleLineTextArea(true);
        
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setName(contenu.getText());
           
           
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceC.getInstance().modifierServices(r)) { // if true
           new ListService(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListService(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(contenu),
                createLineSeparator(),
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
}
