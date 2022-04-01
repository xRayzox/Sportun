/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import entities.Categorie;
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
import com.codename1.uikit.cleanmodern.BaseForm;
import entities.Categorie;
import services.ServiceCategorie;

/**
 *
 * @author Lenovo
 */
public class ModifierCategorieForm extends BaseForm{

    Form current;
    public ModifierCategorieForm(Resources res , Categorie r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Categorie");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField type = new TextField(r.getType() , "Type" , 20 , TextField.ANY);
        TextField name = new TextField(r.getName() , "Nom" , 20 , TextField.ANY);
               
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
        ComboBox etatCombo = new ComboBox();
        
        etatCombo.addItem("Non Traiter");
        
        etatCombo.addItem("Traiter");
        
        if(r.getName() == null ) {
            etatCombo.setSelectedIndex(0);
        }
        else 
            etatCombo.setSelectedIndex(1);
        
        
        
        
        
        type.setUIID("NewsTopLine");
        name.setUIID("NewsTopLine");
        
        
        type.setSingleLineTextArea(true);
        name.setSingleLineTextArea(true);
        
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setType(type.getText());
           r.setName(name.getText());
           
          /* if(etatCombo.getSelectedIndex() == 0 ) {
               r.setEtat(0);
           }
           else 
               r.setEtat(1);*/
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceCategorie.getInstance().modifierCategorie(r)) { // if true
           new ListCategorieForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("categorie modifier!!");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Hallo");
           new ListCategorieForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(type),
                createLineSeparator(),
                new FloatingHint(name),
               
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
