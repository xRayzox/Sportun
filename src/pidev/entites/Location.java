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
public class Location {
   private  int id;
   private double lat,lng;
   private String region;
   


    public Location() {
    }

    public Location(int id, double lat, double lng, String region) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.region = region;
    }

    public Location(double lat, double lng, String region) {
        this.lat = lat;
        this.lng = lng;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getRegion() {
        return region;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", lat=" + lat + ", lng=" + lng + ", region=" + region + '}';
    }
}
