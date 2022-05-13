/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;


import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import models.Event;

import utils.Statics;
/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ItemeventController  {

    @FXML
    private Label nameevent;
    @FXML
    private Label prixevent;
    @FXML
    private ImageView imgevent;

 private Event eventt;
    private MyListener myListener;
    
    private void click(MouseEvent event) {
        myListener.onClickListener(eventt);
    }

   
   
    public void setData(Event event,MyListener myListener) {
        this.eventt =event;
        System.out.println(event);
        this.myListener = myListener;
            nameevent.setText(event.getNameevent());
            prixevent.setText(JavaFXApplicationEvent.CURRENCY +event.getNewprix());
            String path=Statics.PUBLIC_PATH2+ event.getImage();
            System.out.println(path);
            Image image = new Image(path);
            imgevent.setImage(image);
   
    
    }}
