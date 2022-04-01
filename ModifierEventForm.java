/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Categorie;
import entities.Event;
import services.ServiceCategorie;
import com.codename1.uikit.cleanmodern.BaseForm;
import services.ServiceEvent;

/**
 *
 * @author Lenovo
 */
public class ModifierEventForm extends BaseForm{

 Form current;
    public ModifierEventForm(Resources res , Event r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Event");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField nameevent = new TextField(r.getNameevent(), "nameevent" , 20 , TextField.ANY);
        TextField descriptionevent = new TextField(r.getDescriptionevent() , "Descriptionevent" , 20 , TextField.ANY);
        TextField promotion = new TextField(r.getPromotion(), "promotion" , 20 , TextField.ANY);
        TextField newprix = new TextField(r.getNewprix() , "newprix" , 20 , TextField.ANY);
        //TextField image = new TextField(r.getImage() , "image" , 20 , TextField.ANY);
        
               
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
        ComboBox etatCombo = new ComboBox();
        
        etatCombo.addItem("Non Traiter");
        
        etatCombo.addItem("Traiter");
        
        if(r.getNameevent()== null ) {
            etatCombo.setSelectedIndex(0);
        }
        else 
            etatCombo.setSelectedIndex(1);
        
        
        
        
        
        nameevent.setUIID("NewsTopLine");
        descriptionevent.setUIID("NewsTopLine");
         promotion.setUIID("NewsTopLine");
        newprix.setUIID("NewsTopLine");
        //image.setUIID("NewsTopLine");
        
        
        nameevent.setSingleLineTextArea(true);
        descriptionevent.setSingleLineTextArea(true);
         promotion.setSingleLineTextArea(true);
        newprix.setSingleLineTextArea(true);
         //image.setSingleLineTextArea(true);
        
        
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
            
           
           r.setNameevent(nameevent.getText());
           r.setDescriptionevent(descriptionevent.getText());
            r.setPromotion(promotion.getText());
           r.setNewprix(newprix.getText());
            //r.setImage(image.getText());
           
           
          /* if(etatCombo.getSelectedIndex() == 0 ) {
               r.setEtat(0);
           }
           else 
               r.setEtat(1);*/
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceEvent.getInstance().modifierEvent(r)) { // if true
           new ListEventForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Evennnnttt modifier");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("event");
           new ListEventForm(res).show();
           
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(nameevent),
                createLineSeparator(),
                new FloatingHint(descriptionevent),
               
                createLineSeparator(),
                new FloatingHint(promotion),
                createLineSeparator(),
                new FloatingHint(newprix),
                createLineSeparator(),
                //ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
