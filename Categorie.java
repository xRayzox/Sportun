/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Lenovo
 */
public class Categorie {
private int id;
private String type , name;
    public Categorie() {
    }
public Categorie(String type, String name) {
        this.type = type;
        this.name = name;
    }
    public Categorie(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Categorie{" + "type=" + type + ", name=" + name + '}';
    }
    
    
    
    
}
