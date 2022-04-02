/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.FormesCommande;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Asus
 */
public class Web extends Form{
    
    Resources res1;
    public Web(){
        setTitle("Co takwira");
    setLayout(new BorderLayout());
    BrowserComponent browser = new BrowserComponent();
    res1 = UIManager.initFirstTheme("/theme");
    
    

    browser = new BrowserComponent();
    browser.setURL("http://localhost:8000/produit/front");
    this.addComponent(BorderLayout.CENTER, browser);
    this.show();
    
    
    
         
        
  
           this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
               try {
                   new NewsfeedFormC(res1).showBack();
               } catch (Exception ex) {
            
               }
               
               
               
        });

    
    }
    
}
