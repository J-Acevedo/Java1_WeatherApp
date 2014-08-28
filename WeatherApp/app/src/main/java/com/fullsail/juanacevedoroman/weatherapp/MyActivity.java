package com.fullsail.juanacevedoroman.weatherapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;


public class MyActivity extends Activity {


    String todayAPI = "http://api.wunderground.com/api/7ba4162762b10c05/conditions/q/FL/Orlando.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        TodayForecast today = new TodayForecast();
        today.execute( todayAPI );

        Log.d("DUTTON", "today");

    }




}
