package com.fullsail.juanacevedoroman.weatherapp;

/**
 * Created by juanacevedoroman on 8/28/14.
 */
public class TenDayObject {

    String weekday = "";
    String pretty = "";
    String high = "";
    String low = "";
    String condition = "";
    String iconUrl = "";

     public TenDayObject(String _weekday, String _pretty, String _high, String _low, String _condition, String _iconURL){

         weekday = _weekday;
         pretty = _pretty;
         high = _high;
         low = _low;
         condition = _condition;
         iconUrl = _iconURL;

     }

}
