/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

/**
 *
 * @author Lenovo
 */
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


public class Web extends Form{
    
    Resources res1;
    public Web(){
        setTitle("Co takwira");
    setLayout(new BorderLayout());
    BrowserComponent browser = new BrowserComponent();
    //res1 = UIManager.initFirstTheme("/theme");
    
    

    browser = new BrowserComponent();
    browser.setURL("http://localhost:8000/event/liste");
    this.addComponent(BorderLayout.CENTER, browser);
    this.show();
    
    
    
         
        
  
          /* this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
               try {
                   new NewsfeedForm(res1).showBack();
               } catch (Exception ex) {
            
               }
               
               
               
        });*/

    
    }
    
}
