/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Event {
    private int id;
   
    private String nameevent,descriptionevent,promotion,newprix,image;
    private Date star,end;
    
    public  Event(){
        
    }

    public Event(int id, String promotion, String newprix, String nameevent, String descriptionevent ,String image) {
        this.id = id;
        this.promotion = promotion;
        this.newprix = newprix;
        this.nameevent = nameevent;
        this.descriptionevent = descriptionevent;
        this.image = image;
       
    }
    public Event( String newprix,  String nameevent, String descriptionevent, String promotion, String image) {
        this.newprix = newprix;
        this.nameevent = nameevent;
        this.descriptionevent = descriptionevent;
        this.promotion = promotion;
        this.image = image;
        
        
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getNewprix() {
        return newprix;
    }

    public void setNewprix(String newprix) {
        this.newprix = newprix;
    }

    public String getNameevent() {
        return nameevent;
    }

    public void setNameevent(String nameevent) {
        this.nameevent = nameevent;
    }

    public String getDescriptionevent() {
        return descriptionevent;
    }

    public void setDescriptionevent(String descriptionevent) {
        this.descriptionevent = descriptionevent;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   /* public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getStar() {
        return star;
    }

    public void setStar(Date star) {
        this.star = star;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }*/

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", promotion=" + promotion + ", newprix=" + newprix + ", nameevent=" + nameevent + ", descriptionevent=" + descriptionevent + '}';
    }

    
    
}
