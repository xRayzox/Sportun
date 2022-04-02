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

import com.mycompany.myapp.MyApplication;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class AcceuilCommande extends BaseFormC {
    Form current;
    public AcceuilCommande(Resources res) {
        super(new BorderLayout());
        current =this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> new NewsfeedFormC(res).show());
        setUIID("Activate");
                ImageViewer img=new ImageViewer(MyApplication.theme.getImage("AcceuilCommande1.jpg"));

        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("smily.png"), "LogoLabel"),
                        new Label("Welcome  !", "LogoLabel"),img
                )
        );
        
        
        
        Button commandebut = new Button("Passer Votre Commande");
        
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);        
        Button Admin = new Button("Admin");
        
        
        Admin.setUIID("CenterLink");
        Button Home = new Button("Home");
        Home.addActionListener(e -> new NewsfeedFormC(res).show());
        Home.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
                commandebut,
                createLineSeparator(),                
                new SpanLabel("Espace reserve aux Admins,Priere de retaper le code Sportun", "CenterLabel"),
                password,
                Admin,
                
                FlowLayout.encloseRightBottom(Home)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        commandebut.requestFocus();
        commandebut.addActionListener(l -> new com.mycompany.FormesCommande.AjoutCommandeForm(res).show());
        
        
        
        Admin.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                
                if(!password.getText().toString().equals("1234"))
                {
                    Dialog.show("OOPS !!","Veuillez vérifier le Mot de Passe :(", "OK",null);
                }
                else{
                    
                 
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                   
                    
                        new ListAllCommandeForm(res).show();
                    
                        
                    
                    
                    
                       refreshTheme();//Actualisation     
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
        
        }
    });
        
        
    
        
        
    }

            
}
