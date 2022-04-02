/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.FormesCommande;

import com.mycompany.myapp.MyApplication;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
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


/**
 *
 * @author Ayoub
 */
public class AjoutCommandeForm extends BaseFormC {
    
    Form current;
    //public static Resources theme;
    static String nomcurrent=SessionManager.getNom();
    public AjoutCommandeForm (Resources res) {
        
    super ("Newsfeed", BoxLayout.y()); //herigate men Newateed w 1 formulaire vertical
    Toolbar tb = new Toolbar (true);
    
    current = this;
    setToolbar (tb) ;
    tb.setBackCommand("", e -> new AcceuilCommande(res).show());
    getTitleArea().setUIID("Container");
    setTitle ("Passer Votre Commande");
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

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        
        
        
        
       // bindButtonSelection(liste, arrow);
        //bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        //
        
        
        
        
        
        
    
     
    SpanButton sb=new SpanButton("Vous souhaitez augmentez votre masse musculaire ?\n" +
    "Rien n'est plus efficace que nos propositions !\n" +
    "RICHES EN CALORIES !\n" +
    "EXCELLENT RAPPORT QUALITÉ-PRIX !");
    this.add(sb);
    ImageViewer img=new ImageViewer(MyApplication.theme.getImage("profile-pic.jpg"));
    
        
    TextField nom_utilisateur=new TextField("","entrer votre nom !");
    this.add(img);
    nom_utilisateur.setUIID("TextFieldBlack");
    addStringValue ("NomUtilisateur", nom_utilisateur);
    
    
    
    bindButtonSelection(mesListes, arrow);
                mesListes.addActionListener((e1)->{
                    
                    if(!String.valueOf(nom_utilisateur.getText()).equals("")){
                        nomcurrent=String.valueOf(nom_utilisateur.getText());
                    }
                    
                    new ListCommandeForm(res,nomcurrent).show();
        });
    bindButtonSelection(partage, arrow);
                /*partage.addActionListener((e1)->{new AjoutCommandeForm(res).show();
        });*/
                
                
    Label l1=new Label("Choisissez une option"
            + "");
    this.add(l1);
    ComboBox<String> cb1=new ComboBox("");
    cb1.addItem("Optimum Nutrition Gold");
    cb1.addItem("Bulk Shaker Iconic");
    cb1.addItem("Optimum Nutrition Serious Mass");
    cb1.addItem("EAFIT Pure Whey");    
    this.add(cb1);
    
    Button btnAjouter = new Button ("Ajouter") ;
    addStringValue ("",btnAjouter) ;
    
    btnAjouter.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                if((nomcurrent.equals("")&&nom_utilisateur.getText().equals("") )|| cb1.getSelectedItem().equals("")) {
                    Dialog.show("OOPS !!","Veuillez vérifier les données :(", "OK",null);
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    if(!String.valueOf(nom_utilisateur.getText()).equals("")){
                        nomcurrent=String.valueOf(nom_utilisateur.getText());
                    }
                    
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
                    
                    //njibo iduser men session (current user)
                    PasserCommande r=new PasserCommande(nomcurrent.toString(),
                            String.valueOf(cb1.getSelectedItem()).toString(),
                            flous);
                    
                    System.out.println("data  Commande == "+r);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    Commande.getInstance().ajouterCommande(r);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                   /* if(liste.isSelected()){
                        new AjoutCommandeForm(res).show();
                        refreshTheme();
                    }*/
                    //--
                    
                    
                    new ListCommandeForm(res,nomcurrent).show();
                    //--
                    
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
        }
    });
        
        
    }

    
    

    private void addStringValue(String s, Component v) {
        add (BorderLayout.west(new Label (s, "PaddedLabel")).add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));

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
   
   
   
   
   
   
   
   
   
}
    
