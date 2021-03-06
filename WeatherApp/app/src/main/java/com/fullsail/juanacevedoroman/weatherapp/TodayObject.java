package com.fullsail.juanacevedoroman.weatherapp;

import java.util.HashMap;

/**
 * Created by Matthew Dutton on 8/28/14.
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

    HashMap<String,String> currentInfo = new HashMap<String, String>();

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


        currentInfo.put("wind", _windMph);
        currentInfo.put("pressure", _pressure);
        currentInfo.put("visibility", _visability);
        currentInfo.put("dew", _dewPoint);
        currentInfo.put("precip", _precipToday);




    }

}
