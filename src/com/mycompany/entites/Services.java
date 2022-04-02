/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author BLVCK
 */
public class Services {

    private int id, NumTel;
    private String name, Description, title, Type;
    private Location location;

    public Services() {
    }

    public Services(int id, int NumTel, String name, String Description, String title, String Type, Location location) {
        this.id = id;
        this.NumTel = NumTel;
        this.name = name;
        this.Description = Description;
        this.title = title;
        this.Type = Type;
        this.location = location;
    }
    
   

    public Services(int Num_Tel, String name, String Description, String title, String Type) {
        this.NumTel = NumTel;
        this.name = name;
        this.Description = Description;
        this.title = title;
        this.Type = Type;
    }

    public Services(int NumTel, String name, String Description, String title, String Type, Location location) {
        this.NumTel = NumTel;
        this.name = name;
        this.Description = Description;
        this.title = title;
        this.Type = Type;
        this.location = location;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setNum_Tel(int NumTel) {
        this.NumTel = NumTel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getId() {
        return id;
    }

    public int getNum_Tel() {
        return NumTel;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return Type;
    }

    public int getNumTel() {
        return NumTel;
    }

    public Location getLocation() {
        return location;
    }

    public void setNumTel(int NumTel) {
        this.NumTel = NumTel;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Services{" + "id=" + id + ", NumTel=" + NumTel + ", name=" + name + ", Description=" + Description + ", title=" + title + ", Type=" + Type + ", location=" + location + '}';
    }

  
}
