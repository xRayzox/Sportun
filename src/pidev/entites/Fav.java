/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

/**
 *
 * @author BLVCK
 */
public class Fav {
   private int id;
   private Services services;


 

    public Fav() {
    }

    public Fav(int id, Services services) {
        this.id = id;
        this.services = services;
       
    }

    public Fav(Services services) {
        this.services = services;
       
    }

    public int getId() {
        return id;
    }

    public Services getServices() {
        return services;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setServices(Services services) {
        this.services = services;
    }

  

    @Override
    public String toString() {
        return "Fav{" + "id=" + id + ", services=" + services +'}';
    }
   
}
