/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.menuapp;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.ServiceArticle;
import com.esprit.ServiceCommentaire;
import com.esprit.entities.Article;
import com.esprit.entities.Commentaire;
import java.text.SimpleDateFormat;

/**
 *
 * @author alaagha
 */
public class AddArticle extends BaseForm{

   public AddArticle(Resources res){

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Article ");
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

        Label facebook = new Label("Ajouter article", "BottomPad");
        facebook.setTextPosition(BOTTOM);        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook
                    )
                )
        ));

        TextField titre = new TextField("");
        titre.setUIID("TextFieldBlack");
        addStringValue("Titre", titre);
        
        TextField description = new TextField("");
                TextField tag = new TextField("");
        TextField text = new TextField("");
       // TextField description = new TextField("");

        description.setUIID("TextFieldBlack");
                text.setUIID("TextFieldBlack");
        tag.setUIID("TextFieldBlack");
     //   description.setUIID("TextFieldBlack");

        addStringValue("Descrition", description);
        addStringValue("Text", text);
        addStringValue("Tag", tag);
        //addStringValue("", description);

        Button addcomment = new Button("ajouter aricle");
        addcomment.requestFocus();
        add(addcomment);
        
        addcomment.addActionListener((e) -> {
            
            
            try {
                
                if(titre.getText().equals("") || description.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)
                    Article a = new Article(String.valueOf(titre.getText()).toString(),
                                  String.valueOf(tag.getText()).toString(),
                                  String.valueOf(description.getText()).toString(),
                                  String.valueOf(tag.getText()).toString(), //media
                                  String.valueOf(text.getText()).toString()
                                 // format.format(new Date()),
                       //           0,SessionManager.getId()
                    );
                    
                    System.out.println("article == "+a);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceArticle.getInstance().addArticle(a);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    new ListBackArticle(res).show();
                                        
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
