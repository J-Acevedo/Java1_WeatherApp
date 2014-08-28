package com.fullsail.juanacevedoroman.weatherapp;

import android.graphics.drawable.Drawable;

import java.security.PublicKey;

/**
 * Created by davidamalfitano on 8/28/14.
 */
public class HourlyPull {

    public String prettyPull;
    public String civilPull;
    public String tempPull;
    public String feelsLikePull;
    public String conditionPull;
    public String humidityPull;
    public String icon_urlPull;

    /**
     *
     * @param _pretty
     * @param _civil
     * @param _temp
     * @param _feelsLike
     * @param _condition
     * @param _humidity
     * @param _icon
     */
    public HourlyPull(String _pretty, String _civil, String _temp, String _feelsLike, String _condition, String _humidity, String _icon){

        prettyPull = _pretty;
        civilPull = _civil;
        tempPull = _temp;
        feelsLikePull = _feelsLike;
        conditionPull = _condition;
        humidityPull = _humidity;
        icon_urlPull = _icon;

    }




}
