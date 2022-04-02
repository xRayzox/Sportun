/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.FormesCommande;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseFormC extends Form {

    public BaseFormC() {
    }

    public BaseFormC(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseFormC(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        
        tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new NewsfeedFormC(res).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
        tb.addMaterialCommandToSideMenu("Service&Localisation", FontImage.MATERIAL_ADD_LOCATION, e -> new ListService(res).show());
        
        tb.addMaterialCommandToSideMenu("Founiseurs Management", FontImage.MATERIAL_EXIT_TO_APP, e -> new FournisseurForm(this).show());
       
       tb.addMaterialCommandToSideMenu("Liste Fournisseurs", FontImage.MATERIAL_EXIT_TO_APP, e -> new ListeFournisseur(res).show());

        tb.addMaterialCommandToSideMenu("Prosuit anagement", FontImage.MATERIAL_EXIT_TO_APP, e -> new ProduitForm(this).show());
         
        tb.addMaterialCommandToSideMenu("List Produits", FontImage.MATERIAL_EXIT_TO_APP, e -> new ListeProduit(res).show());
        
        
        tb.addMaterialCommandToSideMenu("Catégorie", FontImage.MATERIAL_UPDATE, e -> new AjoutCategorieForm(res).show());
        tb.addMaterialCommandToSideMenu("Evénnements", FontImage.MATERIAL_UPDATE, e -> new AjoutEventForm(res).show());
        
        tb.addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_ARTICLE, e -> new ListArticle(res).show());
        tb.addMaterialCommandToSideMenu("ArticleBack", FontImage.MATERIAL_ARTICLE, e -> new ListBackArticle(res).show());
        
        tb.addMaterialCommandToSideMenu("Commande", FontImage.MATERIAL_AGRICULTURE, e -> new AcceuilCommande(res).show());
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruFormC(res).show());
    }
}
