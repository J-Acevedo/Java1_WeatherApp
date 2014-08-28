package com.fullsail.juanacevedoroman.weatherapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;


import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;


public class MyActivity extends Activity {

    //Current Views
    SmartImageView currentDetailImage;
    TextView currentCondition;
    TextView currentTemp;
    TextView currentLastupdate;

    SmartImageView currentCellImage;
    TextView currentCellTitle;
    TextView currentCellDesc;

    //Hourly Views
    SmartImageView hourDetailImage;
    TextView hourCondition;
    TextView hourTemp;
    TextView hourFeelLike;
    TextView hourLongDate;

    SmartImageView hourCellImage;
    TextView hourCellTitle;
    TextView hourCellDesc;

    //Daily Views
    SmartImageView dayDetailImage;
    TextView dayCondition;
    TextView dayHighTemp;
    TextView dayLowTemp;
    TextView dayLastUpdated;

    SmartImageView dayCellImage;
    TextView dayCellTitle;
    TextView dayCellHigh;
    TextView dayCellLow;

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

        //Current Views
        currentDetailImage = (SmartImageView)findViewById(R.id.current_icon);
        currentCondition = (TextView)findViewById(R.id.current_condition);
        currentTemp = (TextView)findViewById(R.id.current_temp);
        currentLastupdate= (TextView)findViewById(R.id.current_lastUpdate);

        currentCellImage = (SmartImageView)findViewById(R.id.current_cellImage);
        currentCellTitle = (TextView)findViewById(R.id.current_info_title);
        currentCellDesc = (TextView)findViewById(R.id.current_theInfo);

        //Hourly Views
        hourDetailImage = (SmartImageView)findViewById(R.id.hour_image);
        hourCondition = (TextView)findViewById(R.id.hour_condition);
        hourTemp = (TextView)findViewById(R.id.hour_temp);
        hourFeelLike = (TextView)findViewById(R.id.hour_feelLike);
        hourLongDate = (TextView)findViewById(R.id.hour_long_date);

        hourCellImage = (SmartImageView)findViewById(R.id.hour_cellImage);
        hourCellTitle = (TextView)findViewById(R.id.hour_cellTime);
        hourCellDesc = (TextView)findViewById(R.id.hour_cellTemp);

        //Daily Views
        dayDetailImage = (SmartImageView)findViewById(R.id.day_icon);
        dayCondition = (TextView)findViewById(R.id.day_condition);
        dayHighTemp = (TextView)findViewById(R.id.day_hi_temp);
        dayLowTemp = (TextView)findViewById(R.id.day_lo_temp);
        dayLastUpdated = (TextView)findViewById(R.id.day_last_updated);

        dayCellImage = (SmartImageView)findViewById(R.id.day_cellImage);
        dayCellTitle = (TextView)findViewById(R.id.day_cellDay);
        dayCellHigh = (TextView)findViewById(R.id.day_cellHigh);
        dayCellLow = (TextView)findViewById(R.id.day_cellLow);


        // Today ForeCast //

        todayAsync = new TodayAsync(this, todayData);
        todayAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/conditions/q/FL/orlando.json");


        // Ten Day ForeCast //

        tenDayAsync = new TenDayForecast(this, tenDayData);
        tenDayAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/forecast10day/q/FL/Orlando.json");

        // Ten Day ForeCast //

    }


}
