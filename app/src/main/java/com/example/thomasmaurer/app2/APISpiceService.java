package com.example.thomasmaurer.app2;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by Thomas on 19/02/2015.
 */
public class APISpiceService extends
        RetrofitGsonSpiceService {
    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(API.class);
    }
    @Override
    protected String getServerUrl() {
        return "http://api.openweathermap.org/data/2.5/";
    }
}
