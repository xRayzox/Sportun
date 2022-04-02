/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.FormesCommande;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.commande.Commande;
import com.mycompany.entitiesCommande.PasserCommande;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import static java.util.Collections.list;
/**
 *
 * @author Ayoub
 */
public class ListCommandeForm extends BaseFormC {
    
        Form current;

    public ListCommandeForm(Resources res,String nomcurrent)
    {
        super ("Newsteed", BoxLayout.y()); //herigate men Newateed w 1 formulaire vertical
    Toolbar tb = new Toolbar (true);
    current = this;
    setToolbar (tb) ;
    tb.setBackCommand("", e -> new AjoutCommandeForm(res).show());
    getTitleArea().setUIID("Container");
   // setTitle ("Passer Votre Commande");
    getContentPane ().setScrollVisible (false);
    
    
    
    
    
    
    /*tb.addSearchCommand(e ->  {
            
        });*/
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("AyoubAjout.jpg"),"","",res);
        
        
        //
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Commandes", barGroup);
        mesListes.setUIID("SelectBar");
        /*RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");*/
        RadioButton partage = RadioButton.createToggle("Commander", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2,partage,mesListes),
                FlowLayout.encloseBottom(arrow)
        ));

        mesListes.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(mesListes, arrow);
        });
        
        bindButtonSelection(mesListes, arrow);
       
        bindButtonSelection(partage, arrow);
                partage.addActionListener((e1)->{new AjoutCommandeForm(res).show();
            });
        
        
        
        
        //bindButtonSelection(liste, arrow);
        //bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        //
        // appel affichage 
    ArrayList <PasserCommande>list = Commande.getInstance().afficherCommande(nomcurrent);
    
    for( PasserCommande rec : list  ) {
             String urlImage ="AyoubAjout.jpg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                addButton(urlim,rec,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
    
    Button btnAjouter = new Button ("Vider Panier") ;
        btnAjouter.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
                        
            if(dig.show("Suppression","Etes Vous Sure de vouloire Vider tout le panier de commandes ?","Supprimer","Annuler")) {
                dig.dispose();
                if(Commande.getInstance().deleteAllCommande(nomcurrent)) {
                    new ListCommandeForm(res,nomcurrent).show();
                }
            }
            else {
                dig.dispose();
                 }
           
        });  
this.add(btnAjouter);
    
    
    
        
    }
    
     
    
    
    
    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
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
        
        swipe.addTab("",res.getImage("AyoubAjout.jpg"), page1);
        
           
        
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
    
    private void addButton(Image img , PasserCommande rec,Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
       
        Container cnt = BorderLayout.west(image);
        
      /* TextArea ta=new TextArea(objet);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);*/
       
       Label nomText = new Label("Nom : "+rec.getNom_utilisateur(),"NewsTopLine2");
       Label produitText = new Label("Produit : "+rec.getNom_produit(),"NewsTopLine2");
       Label prixtext = new Label("Prix : "+rec.getTotal()+" DT","NewsTopLine2");
       Label line1233 = new Label("--------------------------","NewsTopLine2");
       
       createLineSeparator();
       
       
       //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
                        
            if(dig.show("Suppression","Vous voulez supprimer cette commande ?","Supprimer","Annuler")) {
                dig.dispose();
                if(Commande.getInstance().deleteCommande(rec.getId())) {
                    new ListCommandeForm(res,rec.getNom_utilisateur()).show();
                }
            }
            else {
                dig.dispose();
                 }
           
        });
        
        //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            System.out.println("hello update");
            new ModificationCommandeForm(res,rec).show();
        });
       
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(nomText),
                BoxLayout.encloseX(produitText,lModifier,lSupprimer),BoxLayout.encloseX(prixtext),BoxLayout.encloseX(line1233)));
        
        add(cnt);
        
    
    
    }
    
   
}
