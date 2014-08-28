package com.fullsail.juanacevedoroman.weatherapp;

import android.os.AsyncTask;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by davidamalfitano on 8/28/14.
 */
public class HourlyForcast extends AsyncTask<String, Integer, String>{

    HourlyAdapter adapter;
    ArrayList<HourlyPull> hourPop = new ArrayList<HourlyPull>();
    HttpURLConnection hourly_Connection;

    JSONObject FCTTIME;
    JSONObject temp;
    JSONObject feelsLike;

    String prettyHolder;
    String civilHolder;
    String tempHolder;
    String conditionHolder;
    String iconHolder;
    String humidityHolder;
    String feelsHolder;


    @Override
    protected String doInBackground(String... strings) {


        URL hourly_url = null;
        try {
            hourly_url = new URL( strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            hourly_Connection = (HttpURLConnection) hourly_url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            hourly_Connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream hourly_Input = hourly_Connection.getInputStream();
            String hourlyPuller = IOUtils.toString(hourly_Input);
            hourly_Input.close();

            hourly_Connection.disconnect();

            JSONObject totalHour = new JSONObject(hourlyPuller);

            if (totalHour.has("hourly_forecast")){

                JSONArray hourlyForcast = new JSONArray("hourly_forecast");

                for (int hourIndex = 0; hourIndex <hourlyForcast.length(); hourIndex ++){

                    JSONObject houroBJ = hourlyForcast.getJSONObject(hourIndex);

                    if (houroBJ.has("FCTTIME")){

                       FCTTIME = houroBJ.getJSONObject("FCTTIME");

                        if (FCTTIME.has("pretty")){
                            prettyHolder = houroBJ.getString("pretty");
                        }

                        if (FCTTIME.has("civil")){
                            civilHolder = houroBJ.getString("civil");
                        }
                    }

                    if (houroBJ.has("temp")){
                        temp = houroBJ.getJSONObject("temp");

                        if (temp.has("english")){
                            tempHolder = houroBJ.getString("english");
                        }

                    }

                    if (houroBJ.has("condition")){
                       conditionHolder =  houroBJ.getString("condition");

                    }

                    if (houroBJ.has("icon_url")){
                        iconHolder = houroBJ.getString("icon_url");
                    }

                    if (houroBJ.has("humidity")){
                        humidityHolder = houroBJ.getString("humidity");
                    }

                    if (houroBJ.has("feelslike")){

                        feelsLike = houroBJ.getJSONObject("feelslike");

                        if (feelsLike.has("english")){
                            feelsHolder = feelsLike.getString("english");
                        }

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}