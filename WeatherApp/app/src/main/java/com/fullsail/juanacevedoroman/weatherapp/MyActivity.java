package com.fullsail.juanacevedoroman.weatherapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;


public class MyActivity extends Activity {

    //Current Views
    SmartImageView currentDetailImage;
    TextView city;
    TextView currentCondition;
    TextView currentTemp;
    TextView currentLastupdate;
    ListView currentList;


    //Hourly Views
    SmartImageView hourDetailImage;
    TextView hourCondition;
    TextView hourTemp;
    TextView hourFeelLike;
    TextView hourLongDate;
    ListView hourList;



    //Daily Views
    SmartImageView dayDetailImage;
    TextView dayCondition;
    TextView dayHighTemp;
    TextView dayLowTemp;
    TextView dayLastUpdated;
    ListView dayList;



    // Ten Day Async
    ArrayList<TenDayObject> tenDayData = new ArrayList<TenDayObject>();
    TenDayForecast tenDayAsync;


    // Today async task done by Matt Dutton
    static ArrayList<TodayObject> todayData = new ArrayList<TodayObject>();
    TodayAsync todayAsync;

    //Day 2 Day task
    ArrayList<HourlyPull> hourlyData = new ArrayList<HourlyPull>();
    HourlyForcast dayAsync;

    //Adapters
    static CurrentAdapter currentAdapter;
    HourlyAdapter hourlyAdapter;
    DailyAdapter dailyAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Locks orientation in Landsacpe
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_my);


        //Current Views
        currentDetailImage = (SmartImageView) findViewById(R.id.current_icon);
        city = (TextView)findViewById(R.id.city);
        currentCondition = (TextView)findViewById(R.id.current_temp);
        currentTemp = (TextView)findViewById(R.id.current_condition);
        currentLastupdate = (TextView)findViewById(R.id.current_lastUpdate);
        currentList = (ListView)findViewById(R.id.current_List);
        currentAdapter = new CurrentAdapter(this, todayData, R.layout.current_list_cell);


        // Today ForeCast //
        todayAsync = new TodayAsync(this, todayData);
        todayAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/conditions/q/FL/orlando.json");





/*
        //Hourly Views
        hourDetailImage = (SmartImageView)findViewById(R.id.hour_image);
        hourCondition = (TextView)findViewById(R.id.hour_condition);
        hourTemp = (TextView)findViewById(R.id.hour_temp);
        hourFeelLike = (TextView)findViewById(R.id.hour_feelLike);
        hourLongDate = (TextView)findViewById(R.id.hour_long_date);
        hourList = (ListView)findViewById(R.id.hour_List);

        //Daily Views
        dayDetailImage = (SmartImageView)findViewById(R.id.day_icon);
        dayCondition = (TextView)findViewById(R.id.day_condition);
        dayHighTemp = (TextView)findViewById(R.id.day_hi_temp);
        dayLowTemp = (TextView)findViewById(R.id.day_lo_temp);
        dayLastUpdated = (TextView)findViewById(R.id.day_last_updated);
        dayList = (ListView)findViewById(R.id.day_List);


        hourlyAdapter = new HourlyAdapter(this, hourlyData, R.layout.hour_list_cell);
        dailyAdapter = new DailyAdapter(this, tenDayData, R.layout.daylistcell);
*/


/*
        // Ten Day ForeCast //
        tenDayAsync = new TenDayForecast(this, tenDayData);
        tenDayAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/forecast10day/q/FL/Orlando.json");

        // Day to Day ForeCast //
        dayAsync = new HourlyForcast(this, hourlyData);
        dayAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/hourly10day/q/FL/Orlando.json");

      //  currentDetailImage.setImageUrl(todayData.get(0).iconURL);
        currentCondition.setText(todayData.get(0).condition);
        currentTemp.setText(todayData.get(0).temperature);
        currentLastupdate.setText(todayData.get(0).observationTime);
        currentList.setAdapter(currentAdapter);

        */

    }

    public void loadCurruentUI(int pos){
        if (todayData.size() > 0) {
            Log.d("CON", todayData.get(pos).condition.toString());
            Log.d("TEMP", todayData.get(pos).temperature.toString());
            Log.d("OBS", todayData.get(pos).observationTime.toString());

            if (currentCondition != null) {

                currentDetailImage.setImageUrl(todayData.get(pos).iconURL);
                city.setText(todayData.get(pos).city);
                currentCondition.setText(todayData.get(pos).condition.toString());
                currentTemp.setText(todayData.get(pos).temperature.toString());
                currentLastupdate.setText(todayData.get(pos).observationTime.toString());
                currentList.setAdapter(currentAdapter);
           } else {

                Log.d("NULL", todayData.get(pos).condition.toString());
            }


        }else {

           Log.d("EMPTY", "Array is empty");

        }

        Log.d("COUNT", "Array local " +todayData.size());

    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

}
