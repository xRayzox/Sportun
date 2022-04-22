package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohamed
 */
public class Panier {
 private int id,user_id ;
 
 
 
    public Panier() {
    }

    public Panier(int id,int user_id) {
        this.id = id;
        this.user_id = user_id;
        
    }
    public Panier(int user_id) {
        this.user_id = user_id;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
    
@Override
    public String toString() {
        return "Panier{" + "id=" +id+ ", user_id=" +user_id+"\n";
    }
    
 


}
