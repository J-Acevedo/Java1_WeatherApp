package com.fullsail.juanacevedoroman.weatherapp;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Matt on 8/28/14.
 */
public class TodayObject {



    String city;
    String condition;
    String temperature;
    String humidity;
    String feelsLike;
    String iconURL;
    String observationTime;
    String windMph;
    String pressure;
    String visability;
    String dewPoint;
    String precipToday;


    public TodayObject(String _city, String _condition, String _temperature, String _humidity, String _feelsLike, String _iconURL, String _observationTime, String _windMph, String _pressure, String _visability, String _dewPoint, String _precipToday) {


        city = _city;
        condition = _condition;
        temperature = _temperature;
        humidity = _humidity;
        feelsLike = _feelsLike;
        iconURL = _iconURL;
        observationTime = _observationTime;
        windMph = _windMph;
        pressure = _pressure;
        visability = _visability;
        dewPoint = _dewPoint;
        precipToday = _precipToday;


    }


    public TodayObject(Context context, ArrayList<TodayObject> todayData) {




    }
}
