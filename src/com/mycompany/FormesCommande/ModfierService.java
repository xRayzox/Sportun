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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.my.entites.Location;
import com.my.entites.Services;
import com.mycompany.services.ServiceC;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author BLVCK
 */
public class ModfierService extends BaseFormC {

    Form current;

    public ModfierService(Resources res,Services rec) {
        super("Services", BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Service");
        getContentPane().setScrollVisible(false);

        tb.addSearchCommand(e -> {

        });

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();
        Label s3 = new Label();
        Label s4 = new Label();
        Label s5 = new Label();

        addTab(swipe, s1, res.getImage("back-logo.jpeg"), "", "", res);

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
        RadioButton mesListes = RadioButton.createToggle("Mes Services", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Ajouter Service", barGroup);
        liste.setUIID("SelectBar");
       
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener((e) -> {
            /*InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();*/

             ListService a = new ListService(res);
             a.show();
            refreshTheme();
        });
         liste.addActionListener((e) -> {
            /*InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();*/

             new  AjouterService(res).show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, liste),
                FlowLayout.encloseBottom(arrow)
        ));

    
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        //
        TextField name = new TextField(rec.getName());
        name.setUIID("TextFieldBlack");
        addStringValue("Name", name);

        TextField description = new TextField(rec.getDescription());
        description.setUIID("TextFieldBlack");
        addStringValue("Description", description);

        TextField title = new TextField(rec.getTitle());
        title.setUIID("TextFieldBlack");
        addStringValue("title", title);

        TextField Type = new TextField(rec.getType());
        Type.setUIID("TextFieldBlack");
        addStringValue("type", Type);

        TextField NumTel = new TextField(String.valueOf(rec.getNumTel()));
        NumTel.setUIID("TextFieldBlack");
        addStringValue("NumTel", NumTel);
        
        TextField lat = new TextField(String.valueOf(rec.getLocation().getLat()));
        lat.setUIID("TextFieldBlack");
        addStringValue("lat", lat);
        
        TextField lng = new TextField(String.valueOf(rec.getLocation().getLng()));
        lng.setUIID("TextFieldBlack");
        addStringValue("lng", lng);
        
        TextField region = new TextField(rec.getLocation().getRegion());
        region.setUIID("TextFieldBlack");
        addStringValue("region", region);
        

        Button btnAjouter = new Button("modifier");
        addStringValue("", btnAjouter);

        //onclick button event 
        btnAjouter.addActionListener((e) -> {

            try {

                if (name.getText().equals("") || description.getText().equals("") || title.getText().equals("") || Type.getText().equals("") || NumTel.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data

                    final Dialog iDialog = ip.showInfiniteBlocking();
Location l = new Location( Float.parseFloat(lat.getText()),
                            Float.parseFloat(lng.getText()),
                            String.valueOf(region.getText()).toString()
                            );
                    //njibo iduser men session (current user)
                    Services r = new Services(rec.getId(),Integer.parseInt(NumTel.getText()),
                            String.valueOf(name.getText()).toString(),
                            String.valueOf(description.getText()).toString(),
                            String.valueOf(title.getText()).toString(),
                            String.valueOf(Type.getText()).toString(),
                            l
                           
                    );
                            

                    System.out.println("data  Service == " +r);

                    //appelle methode ajouterService mt3 service Service bch nzido données ta3na fi base 
                    ServiceC.getInstance().modifierServices(r);
                    

                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    Dialog.show("Ajout avec success","Ok");
                    //ba3d ajout net3adaw lel ListREclamationForm
                    
               ListService a = new ListService(res);
             a.show();
            refreshTheme();
                    
                    
                    
                    
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }

    private void addStringValue(String s, Component v) {

        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
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

        swipe.addTab("", res.getImage("back-logo.jpeg"), page1);

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

}
