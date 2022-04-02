/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.FormesCommande;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.Resources;
import com.my.entites.User;
import com.my.services.ServicesUser;
import java.util.ArrayList;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;


/**
 *
 * @author user
 */
public class ListeUsers extends BaseFormC{
    
    Form current;
       public ListeUsers(Resources res){
        initGuiBuilderComponents(res);       
          Toolbar tb =  new Toolbar();
          current=this;
          setToolBar(tb);
          getTitleArea().setUIID("container");
          setTitle("Liste Commandes");
          getContentPane().setScrollVisible(false);
          
          tb.addSearchCommand(e->{
          
              
          });
          Tabs swipe= new Tabs();
          Label l1= new Label();
          Label l2 = new Label();
          addTab(swipe,l1, res.getImage("livraison.jpg"),"","",res);
          
          //
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ArrayList<User> list = ServicesUser.getInstance().AffichageUsers();
         for(User user:list){
             addButton(user,res);
         }
       }


    private void addTab(Tabs swipe, Label spacer, Image image, String text, String string1, Resources res) {
    
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("livraison.jpg"), page1);
        
        
        
                
    }
    
        public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }

    
    
    
         private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
       
     }
         
             public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
 
    private void addButton(User user, Resources res) {
        Label nom = new Label("Nom : "+user.getNom(),"NewsTopLine2");
        Label prenom = new Label("Prenom : "+user.getPrenom(),"NewsTopLine2");
        Label adresse = new Label("Adresse : "+user.getAdresse(),"NewsTopLine2");
        Label email = new Label("Email : "+user.getEmail(),"NewsTopLine2");
        //Label telephone = new Label("Telephone : "+user.getTelephone(),"NewsTopLine2");
        //Label role = new Label("Role : "+user.getRole(),"NewsTopLine2");
        Label status = new Label("Status : "+user.getStatus(),"NewsTopLine2" );
        
        createLineSeparator();
        
        if(user.getStatus()== false ){
            status.setText("NOT VERIFIED");
        }
        else{
            status.setText("VERIFIED");
        }
               //delete button
       Label delete = new Label();
       delete.setUIID("NewsTopLine");
       Style deleteStyle= new Style(delete.getUnselectedStyle());
       deleteStyle.setFgColor(0xf21f1f);
        FontImage deleteImg=FontImage.createMaterial(FontImage.MATERIAL_DELETE, deleteStyle);
        delete.setIcon(deleteImg);
        delete.setTextPosition(RIGHT);
        
        delete.addPointerPressedListener(l->{
        Dialog dialog = new Dialog("Suppression");
        if(dialog.show("SUPPRESSION","VOULEZ-VOUS SUPPRIMER CET UTILISATEUR ?","ANNULER","OK")){
            dialog.dispose();
        }else{
            dialog.dispose();
            if(ServicesUser.getInstance().deleteUser(user.getId())){
                
                new ListeUsers(res).show();
            }
            
        }
        
        });
        
                //staus button
        Label st = new Label();
       status.setUIID("NewsTopLine");
       Style statusStyle= new Style(st.getUnselectedStyle());
       statusStyle.setFgColor(0xf7ad02);
        FontImage statusImg=FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, statusStyle);
        st.setIcon(statusImg);
        st.setTextPosition(LEFT);
        
        st.addPointerPressedListener(l->{
            user.getStatus();
            user.setStatus(true);
            if(ServicesUser.getInstance().setStatus(user)){
                
                new ListeUsers(res).show();
            }
            
        });

                 //mail button
        Label mail = new Label();
       mail.setUIID("NewsTopLine");
       Style mailStyle= new Style(mail.getUnselectedStyle());
       mailStyle.setFgColor(0xf21f1f);
        FontImage mailImg=FontImage.createMaterial(FontImage.MATERIAL_MAIL, mailStyle);
        mail.setIcon(mailImg);
        mail.setTextPosition(LEFT);
        
        mail.addPointerPressedListener(l->{
            Message m = new Message("VOTRE COMPTE EST MAINTENANT VALIDE, SOYEZ LE BIENVENUE :) \"");
                    String textAttachmentUri = "SporTun";
                    m.getAttachments().put(textAttachmentUri, "text/plain");
                   // Display.getInstance().sendMessage(new String[]{/*mail l user a faire SessionManager.getId()}, "FELICITATION !", m);
                   
Display.getInstance().sendMessage(new String[] {user.getEmail()}, "SporTun", m);
        });

        
       add(BoxLayout.encloseY(
               BoxLayout.encloseX(nom),
               BoxLayout.encloseX(prenom),
               BoxLayout.encloseX(email),
               BoxLayout.encloseX(adresse),
               BoxLayout.encloseX(status,st,mail,delete)
                ));
     
    }
}
