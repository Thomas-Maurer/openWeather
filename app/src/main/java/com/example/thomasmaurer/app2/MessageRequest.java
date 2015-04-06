package com.example.thomasmaurer.app2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.thomasmaurer.app2.com.exemple.CityJson.api.Response;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Thomas on 19/02/2015.
 */
public class MessageRequest extends
        RetrofitSpiceRequest<Response, API> {
    String cityname;
    String unit;
    SharedPreferences prefs;

    public MessageRequest() {

        super(Response.class, API.class);
        this.cityname = "";
        this.unit = "metric" ;

    }
    public void changeCityName (String name) {
        this.cityname = name;
    }
    public void changeContext (Context context) {
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.getBoolean("unit",true)){
            this.unit = "metric";
        }else {
            this.unit = "imperial";
        }

    }

    @Override
    public Response loadDataFromNetwork() {
        Log.v("CreateRequest", this.cityname);
        return getService().getLastMessage(this.cityname + ",fr", this.unit);
    }
}
