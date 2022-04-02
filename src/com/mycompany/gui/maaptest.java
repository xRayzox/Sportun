/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author BLVCK
 */
public class maaptest {
    Form f = new Form();
  MapContainer cnt = null;
     public maaptest() {   
    try{
        cnt = new MapContainer("AIzaSyCy-fMWerzvXcPCV0FDI07hW2DAzs_mnpY");
    }catch(Exception ex) {
        ex.printStackTrace();
    }
    
    
}
}
