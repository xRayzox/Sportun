/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.FormesCommande;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.ServiceCommentaire;
import com.esprit.entities.Commentaire;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alaagha
 */
public class Addcomment extends BaseFormC{
        public Addcomment(Resources res){

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter commentaire ");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("Ajouter comment", "BottomPad");
        facebook.setTextPosition(BOTTOM);        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook
                    )
                )
        ));

        TextField username = new TextField("usrname");
        username.setUIID("TextFieldBlack");
        addStringValue("", username);
        
        TextField comment = new TextField("sandeep");
        comment.setUIID("TextFieldBlack");
        addStringValue("", comment);
        Button addcomment = new Button("commenter");
        addcomment.requestFocus();
        add(addcomment);
        
        addcomment.addActionListener((e) -> {
            
            
            try {
                
                if(username.getText().equals("") || comment.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)
                    Commentaire r = new Commentaire(String.valueOf(username.getText()).toString(),3,
                                  String.valueOf(comment.getText()).toString()
                                 // format.format(new Date()),
                       //           0,SessionManager.getId()
                    );
                    
                    System.out.println("commentaire == "+r);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceCommentaire.getInstance().ajoutComentaire(r);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListREclamationForm
                    new ListComments(res).show();
                                        
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }

        });

    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
