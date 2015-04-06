package com.example.thomasmaurer.app2.com.exemple.CityJson.api;

import com.example.thomasmaurer.app2.com.exemple.CityJson.api.Weather;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Thomas on 19/02/2015.
 */
public class Response {
    public String name;
    public Weather[] weather;
    @SerializedName("wind")
    public Wind windspeed;
    public Sys sys;
    public int id;
    public Main main;



    public class Wind {
        public float speed;
    }
    public class Sys {
        public String country;
    }
    public class Main {
        public float temp;
    }

    public String toString () {
        return name + " | " + id + " | ";
    }
}
