package com.example.thomasmaurer.app2;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.thomasmaurer.app2.com.exemple.CityJson.api.Response;

import java.util.ArrayList;

/**
 * Created by Thomas on 18/02/2015.
 */
public class City implements Parcelable{
   public String name, country;
    public int CityCode;
    public float temperature;
    public float windSpeed;
    //public ArrayList<Response> futureDays;

    public City (String cname,String ccountry, int ccode, float ctemperature, float cwindspeed){
        this.name = cname;
        this.country = ccountry;
        this.temperature = ctemperature;
        this.CityCode = ccode;
        this.windSpeed = cwindspeed;
    }

    @Override
    public String toString() {
        return this.name;
    }


    @Override
    public int describeContents() {
        return 0;
    }
    public City (Parcel source) {
        name = source.readString();
        country = source.readString();
        CityCode = source.readInt();
        temperature = source.readInt();
        windSpeed = source.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(country);
        dest.writeInt(CityCode);
        dest.writeFloat(temperature);
        dest.writeFloat(windSpeed);

    }
    public static final Creator<City> CREATOR = new Creator<City>()  {

        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
