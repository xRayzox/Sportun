/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.entites;

/**
 *
 * @author user
 */
public class Caracteristique {
    
  private int   id	;
  private String  poids	;
  private String weight;	
  private String height;

    public Caracteristique(int id, String poids, String weight, String height) {
        this.id = id;
        this.poids = poids;
        this.weight = weight;
        this.height = height;
    }

    public Caracteristique() {
    }

    public Caracteristique(String poids, String weight, String height) {
        this.poids = poids;
        this.weight = weight;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
  
}

