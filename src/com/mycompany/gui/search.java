/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
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
import com.mycompany.entites.Services;
import com.mycompany.entites.Location;
import com.mycompany.services.ServiceC;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import static java.util.Collections.list;
/**
 *
 * @author Ayoub
 */
public class search extends BaseFormC {
    
        Form current;

    public search(Resources res,String nomcurrent)
    {
        super ("Newsteed", BoxLayout.y()); //herigate men Newateed w 1 formulaire vertical
    Toolbar tb = new Toolbar (true);
    current = this;
    setToolbar (tb) ;
    tb.setBackCommand("", e -> new ListAllCommandeForm(res).show());
    getTitleArea().setUIID("Container");
   // setTitle ("Passer Votre Commande");
    getContentPane ().setScrollVisible (false);
    
    
    
    
    
    
    /*tb.addSearchCommand(e ->  {
            
        });*/
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("AyoubBack.jpg"),"","",res);
        
        
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

        


        
       
        
        
        
      
        // appel affichage 
    ArrayList <Services>list = ServiceC.getInstance().Search(nomcurrent);
    
    for( Services rec : list  ) {
             String urlImage ="AyoubBack.jpg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                addButton(urlim,rec,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
    
    Button btnAjouter = new Button ("Supprimer tout les commandes du Client") ;
        btnAjouter.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
                        
            if(dig.show("Suppression","Etes Vous Sure de Supprimer tout les commandes du Client ?","Supprimer","Annuler")) {
                dig.dispose();
                /*if(ServiceC.getInstance().deleteServices(nomcurrent)) {
                    new ListAllCommandeForm(res).show();
                }*/
            }
            else {
                dig.dispose();
                 }
           
        });  
this.add(btnAjouter);
    
    
    
        
    }
    
     
    
    
    
    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
       
        
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
        
        swipe.addTab("",res.getImage("AyoubBack.jpg"), page1);
        
           
        
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
    
    private void addButton(Image img , Services rec,Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
       
        Container cnt = BorderLayout.west(image);
        
      /* TextArea ta=new TextArea(objet);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);*/
       
        Label Name = new Label("Name : " + rec.getName(), "NewsTopLine2");
        Label Description = new Label("Description : " + rec.getDescription(), "NewsTopLine2");
        Label Title = new Label("Title : " + rec.getTitle() + "", "NewsTopLine2");
        Label Type = new Label("Type : " + rec.getType() + "", "NewsTopLine2");
        Label Num_Tel = new Label("Num_Tel : " + rec.getNumTel() + " ", "NewsTopLine2");
        Label Lat = new Label("Lat : " + rec.getLocation().getLat() + " ", "NewsTopLine2");
        Label Lng = new Label("Lng : " + rec.getLocation().getLng() + "", "NewsTopLine2");
        Label Region = new Label("Region : " + rec.getLocation().getRegion() + "", "NewsTopLine2");
        Label line1233 = new Label("--------------------------", "NewsTopLine2");
       
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
                if(ServiceC.getInstance().deleteServices(rec.getId())) {
                    new ListAllCommandeForm(res).show();
                }
            }
            else {
                dig.dispose();
                 }
           
        });
        
        
//------------------------------------------------Stat------------------------------------
        //supprimer button
        Label lstat = new Label(" ");
        lstat.setUIID("NewsTopLine");
        Style statStyle = new Style(lstat.getUnselectedStyle());
        statStyle.setFgColor(0xf21f1f);

        FontImage statImage = FontImage.createMaterial(FontImage.MATERIAL_EDIT, statStyle);
        lstat.setIcon(statImage);
        lstat.setTextPosition(RIGHT);

        //click delete icon
        lstat.addPointerPressedListener((ActionEvent l) -> {

            Dialog dig = new Dialog("Modifier");

                System.out.println("in modifier buton");

                new ModfierService(res,rec).show();
        });

        //click delete icon
        /*lstat.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
                        
            //if(dig.show("Suppression","Vous voulez supprimer cette commande ?","Supprimer","Annuler")) {
           //     dig.dispose();
           
                String nomcurrent=rec.getName().toString();
                int nbp=Commande.getInstance().nbpetit(nomcurrent);
                int nbg=Commande.getInstance().nbgrand(nomcurrent);
                
                if(nbp>0) {
                    new StatisticCommandeForm(res,nbp,nbg).show();
                }
           // }
           // else {
            //    dig.dispose();
             //    }
           
        });*/
        
        //Update icon 
        /*Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            System.out.println("hello update");
            new ModificationCommandeForm(res,rec).show();
        });*/
       
          cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(),
                BoxLayout.encloseX(Name, lSupprimer, lstat), BoxLayout.encloseX(Description), BoxLayout.encloseX(Title), BoxLayout.encloseX(Type), BoxLayout.encloseX(Lat), BoxLayout.encloseX(Lng), BoxLayout.encloseX(Region), BoxLayout.encloseX(Num_Tel), BoxLayout.encloseX(line1233)));

        add(cnt);
    }
    
   
}