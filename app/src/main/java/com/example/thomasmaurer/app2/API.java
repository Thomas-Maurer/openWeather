package com.example.thomasmaurer.app2;

import com.example.thomasmaurer.app2.com.exemple.CityJson.api.Response;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Thomas on 19/02/2015.
 */
public interface API {
    @GET("/weather")
    Response getLastMessage(@Query("q") String Cityname, @Query("units") String Unit);
}
