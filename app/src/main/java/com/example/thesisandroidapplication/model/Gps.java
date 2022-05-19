package com.example.thesisandroidapplication.model;

public class Gps {
    private double lat, lng, speed;

    public Gps()
    {

    }

/*    public Gps(String new_lat, String new_lng, String new_speed) {
        lat = new_lat;
        lng = new_lng;
        speed = new_speed;
    }*/

    public Gps(double new_lat, double new_lng)
    {
        lat = new_lat;
        lng = new_lng;
    }

    public double getLat(){
        return lat;
    }

    public double getLng()
    {
        return lng;
    }
}
