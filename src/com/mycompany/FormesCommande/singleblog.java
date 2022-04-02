/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.FormesCommande;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.share.FacebookShare;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.ServiceArticle;
import com.esprit.ServiceCommentaire;
import com.esprit.entities.Article;
import com.esprit.entities.Commentaire;
import utils.Statics;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author alaagha
 */
public class singleblog extends BaseFormC{
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    String imgurl = Statics.BASE_URL+"/upload/images/";

    public singleblog(Resources res,Article art){

        super("singleblog", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("blog");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        tb.setBackCommand("", e -> new ListArticle(res).show());
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        ServiceArticle.getInstance().likeo(art.getId());
        addTab(swipe,art.getMedia(), spacer1,art.getLikenb()+" likes",art.getCommentairesnb()+" Comments",art.getTitre(),art.isLikedu(),art,res);
                //boolean liked = ServiceArticle.getInstance().likeo(art.getId());
        System.out.println(art.isLikedu());
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
         //Article articlo=ServiceArticle.getInstance().singleArticle(art);
         
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        articleElement(art,res);
                ArrayList<Commentaire>list = ServiceCommentaire.getInstance().affichagePostComments(art.getId());
        
        for(Commentaire rec : list ) {
             //String urlImage ="back-logo.jpeg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             String urlImage ="news-item.jpg";
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                addButtoncom(urlim,rec,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
        
        
        //boucle for lil item mta3 article
//        addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);
//        addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
//        addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
//       addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
        //end boucle for mta3 article
                Button addcomment = new Button("commenter");
                  addcomment.requestFocus();
                  Addcomment(res, art);
        addcomment.addActionListener(e -> new NewsfeedFormC(res).show());

    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, String img, Label spacer, String likesStr, String commentsStr, String text,boolean liked,Article rec , Resources res) {
                     String urlImage =imgurl+img;

          Image placeHolder = Image.createImage( Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight() / 3);

             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                //addButton(urlim,rec,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                //Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
        
        //FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        //likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);
//        //click delete icon
likes.addPointerPressedListener(l -> {
         if(ServiceArticle.getInstance().LikePost(rec.getId(),1)) {
                    new ListArticle(res).show();
                }
        });  
        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
       // ScaleImageLabel image = new ScaleImageLabel(urlim);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
   private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount,Article art) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
     private void articleElement(Article rec , Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
         Form f2 = new Form(BoxLayout.y());
        
        Label titreTxt = new Label("titre : "+rec.getTitre(),"NewsTopLine2");
        Label descriptionTxt = new Label("description : "+rec.getDescription(),"NewsTopLine2" );
        Label mediaTxt = new Label("Media : "+rec.getMedia(),"NewsTopLine2");
        Label dateTxt = new Label("created at : "+rec.getCreatedAt(),"NewsTopLine2");
        Label commentairesTxt = new Label("commentaies : "+rec.getCommentairesnb(),"NewsTopLine2");
        Label likeTxt = new Label("like : "+rec.getLikenb(),"NewsTopLine2");
        Label textxt = new Label(""+rec.getText(),"NewsTopLine2");
        Label tagtxt = new Label("tag "+rec.getTag(),"NewsTopLine2");
         System.out.println("tiraratirara "+textxt);
        createLineSeparator();
        
   
        f2.add(titreTxt);
        f2.add(descriptionTxt);
        f2.add(dateTxt);
        f2.add(tagtxt);
        f2.add(textxt);
     
        add(f2);
        
    }
    private void addButtoncom(Image img,Commentaire rec , Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
        Label titreTxt = new Label("username : "+rec.getUsername(),"NewsTopLine2");
        Label descriptionTxt = new Label("text : "+rec.getText(),"NewsTopLine2" );
        //Label mediaTxt = new Label("Media : "+rec.getMedia(),"NewsTopLine2");

        createLineSeparator();
        
       
        
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
//        
//        //click delete icon
lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer cet commentaire ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
         if(ServiceCommentaire.getInstance().deleteCommentaire(rec.getId())) {
                    new ListArticle(res).show();
                }
        });
//        
        //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new ModifierComments(res,rec).show();
        });
        String username="ag.gharbi";
                        if(username.equals(rec.getUsername())) {
                            cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(titreTxt),
                BoxLayout.encloseX(descriptionTxt),
                //BoxLayout.encloseX(imgv),
                       BoxLayout.encloseX(lModifier,lSupprimer)
        ));
                }
                
                else {
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(titreTxt),
                BoxLayout.encloseX(descriptionTxt)
                //BoxLayout.encloseX(imgv),
        ));}
        
        
              // image.addActionListener(e ->new singleblog(res,).show());

        add(cnt);
        
    }
         void Addcomment(Resources res,Article art){
String username="ag.gharbi";
        TextField comment = new TextField("sandeep");
        comment.setUIID("TextFieldBlack");
        addStringValue("", comment);
        Button addcomment = new Button("commenter");
        addcomment.requestFocus();
        add(addcomment);
        
        addcomment.addActionListener((e) -> {
            
            
            try {
                
                if(username.equals("") || comment.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)
                    Commentaire r = new Commentaire(String.valueOf(username).toString(),art.getId(),
                                  String.valueOf(comment.getText()).toString()
                                 // format.format(new Date()),
                       //           0,SessionManager.getId()
                    );
                    
                    System.out.println("commentaire == "+r);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceCommentaire.getInstance().ajoutComentaire(r);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListREclamationForm
                    new singleblog(res,art).show();
                                        
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
