package com.fullsail.juanacevedoroman.weatherapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.loopj.android.image.SmartImageView;
import java.util.ArrayList;


public class MyActivity extends Activity {

    //Current Views
    TextView city;
    SmartImageView currentDetailImage;
    TextView currentCondition;
    TextView currentTemp;
    TextView currentLastupdate;
    ListView currentList;

    TextView currentWind;
    TextView  currentPressure;
    TextView currentPrecip;
    TextView  currentVisibility;
    TextView  currentDewPoint;


    //Hourly Views
    TextView hour;
    SmartImageView hourDetailImage;
    TextView hourCondition;
    TextView hourTemp;
    TextView hourFeelLike;
    TextView hourLongDate;
    ListView hourList;



    //Daily Views
    SmartImageView dayDetailImage;
    TextView dayCity;
    TextView dayCondition;
    TextView dayHighTemp;
    TextView dayLowTemp;
    TextView dayLastUpdated;
    ListView dayList;



    // Ten Day Async
    ArrayList<TenDayObject> tenDayData = new ArrayList<TenDayObject>();
    TenDayForecast tenDayAsync;


    // Today async task done by Matt Dutton
    ArrayList<TodayObject> todayData = new ArrayList<TodayObject>();
    TodayAsync todayAsync;

    //Day 2 Day task
    ArrayList<HourlyPull> hourlyData = new ArrayList<HourlyPull>();
    HourlyForcast hourlyAsync;

    //Adapters
    CurrentAdapter currentAdapter;
    HourlyAdapter hourlyAdapter;
    DailyAdapter dailyAdapter;

    //PageAdapter




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //Locks orientation in Landsacpe
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Today ForeCast //




        currentAdapter = new CurrentAdapter(this, todayData, R.layout.current_list_cell);
        hourlyAdapter = new HourlyAdapter(this, hourlyData, R.layout.hour_list_cell);
        dailyAdapter = new DailyAdapter(this, tenDayData, R.layout.daylistcell);

        todayAsync = new TodayAsync(this, todayData);
        todayAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/conditions/q/FL/orlando.json");



    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(parent == hourList){
                loadUI(position, 1,false);
            }else if (parent == dayList){
                loadUI(position,2,false);
            }

        }
    };

    public void loadUI(int x, int y, boolean z){

        if (y == 0){

            //Current Views
            city = (TextView)findViewById(R.id.city);
            currentDetailImage = (SmartImageView)findViewById(R.id.current_icon);
            currentCondition = (TextView)findViewById(R.id.current_condition);
            currentTemp = (TextView)findViewById(R.id.current_temp);
            currentLastupdate= (TextView)findViewById(R.id.current_lastUpdate);
            currentWind = (TextView)findViewById(R.id.current_wind);
            currentPressure = (TextView)findViewById(R.id.current_pressure);
            currentPrecip = (TextView)findViewById(R.id.current_precip);
            currentVisibility = (TextView)findViewById(R.id.current_visibility);
            currentDewPoint = (TextView)findViewById(R.id.current_Dew);

            city.setText(todayData.get(x).city);
            currentDetailImage.setImageUrl(todayData.get(x).iconURL);
            currentCondition.setText(todayData.get(x).condition);
            currentTemp.setText(todayData.get(x).temperature);
            currentLastupdate.setText(todayData.get(x).observationTime);
            currentWind.setText(todayData.get(x).windMph);
            currentPressure.setText(todayData.get(x).pressure);
            currentPrecip.setText(todayData.get(x).precipToday);
            currentVisibility.setText(todayData.get(x).visability);
            currentDewPoint.setText(todayData.get(x).dewPoint);


        } else if (y == 1){

            //Hourly Views
            hour = (TextView)findViewById(R.id.time);
            hourDetailImage = (SmartImageView)findViewById(R.id.hour_image);
            hourCondition = (TextView)findViewById(R.id.hour_condition);
            hourTemp = (TextView)findViewById(R.id.hour_temp);
            hourFeelLike = (TextView)findViewById(R.id.hour_feelLike);
            hourLongDate = (TextView)findViewById(R.id.hour_long_date);
            hourList = (ListView)findViewById(R.id.hour_List);

            hour.setText(hourlyData.get(x).civilPull);
            hourDetailImage.setImageUrl(hourlyData.get(x).icon_urlPull);
            hourCondition.setText(hourlyData.get(x).conditionPull);
            hourTemp.setText("Temp: " + hourlyData.get(x).tempPull + " F");
            hourFeelLike.setText("Feels Like: " + hourlyData.get(x).feelsLikePull + " F");
            hourLongDate.setText(hourlyData.get(x).civilPull);

            if (z == true) {
                hourList.setAdapter(hourlyAdapter);
                hourList.setOnItemClickListener(itemClickListener);
            }



        } else if (y == 2){

            //Daily Views
            dayCity = (TextView)findViewById(R.id.city);
            dayDetailImage = (SmartImageView)findViewById(R.id.day_icon);
            dayCondition = (TextView)findViewById(R.id.day_condition);
            dayHighTemp = (TextView)findViewById(R.id.day_hi_temp);
            dayLowTemp = (TextView)findViewById(R.id.day_lo_temp);
            dayLastUpdated = (TextView)findViewById(R.id.day_last_updated);
            dayList = (ListView)findViewById(R.id.day_List);

            dayCity.setText(tenDayData.get(x).weekday);
            dayDetailImage.setImageUrl(tenDayData.get(x).iconUrl);
            dayCondition.setText(tenDayData.get(x).condition);
            dayHighTemp.setText("High: "+ tenDayData.get(x).high);
            dayLowTemp.setText("Low: " +tenDayData.get(x).low);
            //dayLastUpdated.setText(tenDayData.get(x).);

            if (z == true){
                dayList.setAdapter(dailyAdapter);
                dayList.setOnItemClickListener(itemClickListener);
            }



        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_current) {

            setContentView(R.layout.activity_my);

            todayAsync = new TodayAsync(this, todayData);
            todayAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/conditions/q/FL/orlando.json");

            return true;
        }else if (id == R.id.action_day){

            setContentView(R.layout.day_layout);


             // Ten Day ForeCast //
            tenDayAsync = new TenDayForecast(this, tenDayData);
            tenDayAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/forecast10day/q/FL/Orlando.json");


            return true;
        }else if (id == R.id.action_houu){

            setContentView(R.layout.hourly_layout);


            // Day to Day ForeCast //
            hourlyAsync = new HourlyForcast(this, hourlyData);
            hourlyAsync.execute("http://api.wunderground.com/api/7ba4162762b10c05/hourly10day/q/FL/Orlando.json");


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

