/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.FormesCommande;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.my.entites.Fournisseur;
import com.my.services.Service_Fournisseur;

import java.util.ArrayList;


public class ListeFournisseur extends BaseFormC{
    Form current;

    public ListeFournisseur(Resources res){
        
        super("Fournisseurs",BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Fournisseur");
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand(e -> {
            
        
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1,res.getImage("back-logoo.jpg"),"","",res);
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
        RadioButton mesListes = RadioButton.createToggle("Les Fournisseurs", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Fournisseurs", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        
        
        

        
        
        
        
        
                mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();
            //  ListReclamationForm a = new ListReclamationForm(res);
            //  a.show();
            refreshTheme();
            new ListeFournisseur(res).show();
        });
        
        
        
         
        
                
                
            


        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        ArrayList<Fournisseur>list = Service_Fournisseur.getInstance().findAll();
        
        for(Fournisseur art:list){
           
            String urlImage = "back-logoo.jpg";
            
            Image placeholder = Image.createImage(120,90);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc,urlImage, urlImage, URLImage.RESIZE_SCALE);
            
            addButton(urlim,art,res);
            
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            
            Container containerImg = new Container();
            
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                 
            
        }
        
        
    }
    
    
    
    private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size){
            image = image.scaledHeight(size);
        }
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ){
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
            
        }
        
        ScaleImageLabel imageScale   = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverLay");
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                            overLay,
                            BorderLayout.south(
                            BoxLayout.encloseY(
                            new SpanLabel(text,"LargeWhiteText"),
                                    spacer
                                    ))
        );
        
        swipe.addTab("",res.getImage("back-logoo.jpg"),page1);
        
        
        
            
           this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
               try {
                   new NewsfeedFormC(res).showBack();
               } catch (Exception ex) {
            
               }
               
               
               
        });
        

    }
    
    public void bindButtonSelection(Button btn , Label l){
        
        btn.addActionListener(e->{
            if(btn.isSelected()){
                updateArrowPosition(btn,l);
            }
            });
        
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }

    
    
    
    
    

    
    
    
    
    
    
    private void addButton(Image img, Fournisseur art,Resources res) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        

        
        Label titreTxt = new Label("Name : "+art.getName(),"NewsTopLine2");
        Label dateTxt = new Label("Email : "+art.getEmail(),"NewsTopLine2");
      


        
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupprimer.setIcon(supprimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            Dialog dig = new Dialog("Suppression");
            if(dig.show("Suppression","Vous voulez supprimer ce Fournisseur ?","Annuler","Oui")){
                dig.dispose();
            }
            else{
                dig.dispose();
                
                if(Service_Fournisseur.getInstance().deleteFour(art.getId())){
                    new ListeFournisseur(res).show();                    
                }
            }
        
        
        });
        
        //update icon
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        lModifier.addPointerPressedListener(l ->{
            System.out.println("");
            new ModifierFournisseurForm(res, art).show();
        
        });
        
        
             
        
        
        
        
        Label lQrCode = new Label(" ");
        lQrCode.setUIID("NewsTopLine");
        Style lQrCodeStyle = new Style(lQrCode.getUnselectedStyle());
        lQrCodeStyle.setFgColor(0x7ad02);

        FontImage mFontImagee = FontImage.createMaterial(FontImage.MATERIAL_DETAILS, lQrCodeStyle);
        lQrCode.setIcon(mFontImagee);
        lQrCode.setTextPosition(LEFT);

        lQrCode.addPointerPressedListener(l -> {

    
          new Youtube().show();

            
        });
        
        
        
   
        
      
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                BoxLayout.encloseX(titreTxt),
                BoxLayout.encloseX(dateTxt),
               // BoxLayout.encloseX(duree),
                
               //BoxLayout.encloseX(date),

       
                BoxLayout.encloseX(lModifier,lSupprimer,lQrCode)
                
                 ));
                 
        add(cnt);

   
    }
    
}
