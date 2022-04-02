/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.FormesCommande;

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
import com.my.entites.Services;
import com.my.entites.Location;

/**
 *
 * @author BLVCK
 */
public class ModifierService extends BaseFormC {
 Form current; 
    public ModifierService(Resources res, Services r) {
     super("Newsfeed", BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
System.out.print(""
        + r);
Location LoS = new Location();

        super.addSideMenu(res);

        TextField name = new TextField(r.getName(), "Objet", 20, TextField.ANY);
        name.setUIID("NewsTopLine");
        name.setSingleLineTextArea(true);
        
        TextField Description = new TextField(r.getDescription(), "Objet", 20, TextField.ANY);
        Description.setUIID("NewsTopLine");
        Description.setSingleLineTextArea(true);
        
        TextField title = new TextField(r.getTitle(), "Objet", 20, TextField.ANY);
        title.setUIID("NewsTopLine");
        title.setSingleLineTextArea(true);
        
        TextField Type = new TextField(r.getType(), "Objet", 20, TextField.ANY);
        Type.setUIID("NewsTopLine");
        Type.setSingleLineTextArea(true);
        
        TextField Num_Tel = new TextField(String.valueOf(r.getNumTel()), "Objet", 20, TextField.ANY);
        Num_Tel.setUIID("NewsTopLine");
        Num_Tel.setSingleLineTextArea(true);
        
        TextField Lat = new TextField(String.valueOf(r.getLocation().getLat()), "Objet", 20, TextField.ANY);
        Lat.setUIID("NewsTopLine");
        Lat.setSingleLineTextArea(true);
        
        TextField Lng = new TextField(String.valueOf(r.getLocation().getLng()), "Objet", 20, TextField.ANY);
        Lng.setUIID("NewsTopLine");
        Lng.setSingleLineTextArea(true);
        
        TextField region = new TextField(r.getLocation().getRegion(), "Objet", 20, TextField.ANY);
        region.setUIID("NewsTopLine");
        region.setSingleLineTextArea(true);

        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
     
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //Event onclick btnModifer
        Location ll = null;
        ll.setLat(Double.parseDouble(Lat.getText()));
        ll.setLng(Double.parseDouble(Lng.getText()));
        ll.setRegion(region.getText());
        btnModifier.addPointerPressedListener(l -> {

            r.setName(name.getText());
            r.setDescription(Description.getText());
            r.setTitle(title.getText());
            r.setType(Type.getText());
            r.setNumTel(Integer.parseInt(Num_Tel.getText()));
            r.setLocation(ll);

            //appel fonction modfier reclamation men service
            if (ServiceC.getInstance().modifierServices(r)) { // if true
                new ListService(res).show();
            }
        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListService(res).show();
        });
    }
}
