package com.fullsail.juanacevedoroman.weatherapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;


import java.util.ArrayList;


public class MyActivity extends Activity {

    ArrayList<TenDayObject> tenDayData = new ArrayList<TenDayObject>();
    TenDayForecast tenDayAsync;


    // Today async task done by Matt Dutton
    ArrayList<TodayObject> todayData = new ArrayList<TodayObject>();
    TodayAsync todayAsync;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //Locks orientation in Landsacpe
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        // Today ForeCast //

        todayAsync = new TodayAsync(this, todayData);
        todayAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/conditions/q/FL/orlando.json");


        // Ten Day ForeCast //

        tenDayAsync = new TenDayForecast(this, tenDayData);
        tenDayAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/forecast10day/q/FL/Orlando.json");

        // Ten Day ForeCast //

    }


}
