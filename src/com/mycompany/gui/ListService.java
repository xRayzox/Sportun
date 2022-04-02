/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Services;
import com.mycompany.entites.Location;
import com.mycompany.services.ServiceC;
import com.mycompany.gui.ModfierService;
import com.mycompany.gui.ModifierQuestion;
import com.mycompany.gui.modaj;

import java.util.ArrayList;

/**
 *
 * @author BLVCK
 */
public class ListService extends BaseFormC {

    Form current;

    public ListService(Resources res) {

       super("Services", BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste des Services");
        getContentPane().setScrollVisible(false);

        tb.addSearchCommand(e -> {

        });

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe, s1, res.getImage("back-logo.jpeg"), "", "", res);

        // Welcome current user
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
        RadioButton mesListes = RadioButton.createToggle("Mes Services", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("ajouter Service", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Reclamer", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener((e) -> {
            /*InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();*/
  ///////Notification//////////
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Liste des Services");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Services");
  ///////////////////////////////
             ListService a = new ListService(res);
             a.show();
            refreshTheme();
            
        });
         liste.addActionListener((e) -> {
            /*InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();*/
              ///////Notification//////////
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Ajouter");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Services");
  ///////////////////////////////

             new  AjouterService(res).show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, liste),
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
          TextField nom=new TextField("","entrer le nom de client desiré !");
        nom.setUIID("TextFieldBlack");
        //-------
        this.add(nom);
 Button btnss = new Button ("search") ;
        this.add(btnss);
        btnss.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nomcurrent=String.valueOf(nom.getText());
                if(nomcurrent.equals("")) {
                    Dialog.show("OOPS !!","Veuillez vérifier les données 🙁", "OK",null);
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    
                    new search(res,nomcurrent).show();
                    //--
                    
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
        }
    });
       ArrayList<Services> list = ServiceC.getInstance().affichageServices();

        for (Services rec : list) {
            String urlImage = "news-item-4.jpg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 

            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);

            addButton(urlim, rec, res);
            /*ServiceC.setInstance(new Commande());
                Commande.setInstance(new Commande());*/
            ScaleImageLabel image = new ScaleImageLabel(urlim);

            Container containerImg = new Container();

            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }

    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
         
      
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        imageScale,
                        overLay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", res.getImage("news-item-4.jpg"), page1);

    }

    public void bindButtonSelection(Button btn, Label l) {

        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }

    private void addButton(Image img, Services rec, Resources res) {

        int height = Display.getInstance().convertToPixels(5.5f);
        int width = Display.getInstance().convertToPixels(10f);

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

            if (dig.show("Suppression", "Vous voulez supprimer cette commande ?", "Supprimer", "Annuler")) {
                dig.dispose();
                if (ServiceC.getInstance().deleteServices(rec.getId())) {
                    new ListService(res).show();
                }
            } else {
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

    /*
    public Component addGUIs() {
        Container compo = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        compo.setScrollableY(true);

        ArrayList<PasserCommande> listEvenements = Commande.getInstance().getAll();
        ArrayList componentModels = new ArrayList<>();

        TextField searchTF = new TextField("", "Chercher un evenement");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (int i = 0; i < componentModels.size(); i++) {
                    compo.removeComponent((Component) componentModels.get(i));
                    componentModels.remove(i);
                }
            }
            for (int i = 0; i < listEvenements.size(); i++) {
                if (listEvenements.get(i).getNom_utilisateur().startsWith(searchTF.getText())) {
                    Component model = makeEvenementModel(listEvenements.get(i));
                    compo.add(model);
                    componentModels.add(model);
                }
            }
            compo.revalidate();
        });
        compo.add(searchTF);

        if (listEvenements.size() > 0) {
            for (PasserCommande listEvenement : listEvenements) {
                Component model = makeEvenementModel(listEvenement);
                compo.add(model);
                componentModels.add(model);
            }
        } else {
            compo.add(new Label("Aucune donnee"));
        }

        return compo;
    }
     */
}