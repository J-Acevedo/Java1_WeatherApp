package com.fullsail.juanacevedoroman.weatherapp;

import android.content.Context;
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

    Context mContext;
    ArrayList<HourlyPull> data;

    public HourlyForcast(Context _mContext, ArrayList<HourlyPull> _data){

        mContext = _mContext;
        data = _data;

    }


    @Override
    protected String doInBackground(String... strings) {

        URL hourly_url = null;

        try {

            hourly_url = new URL( strings[0]);
            hourly_Connection = (HttpURLConnection) hourly_url.openConnection();
            hourly_Connection.connect();

            InputStream hourly_Input = hourly_Connection.getInputStream();
            String hourlyPuller = IOUtils.toString(hourly_Input);
            hourly_Input.close();

            JSONObject totalHour = new JSONObject(hourlyPuller);

            if (totalHour.has("hourly_forecast")){

                JSONArray hourlyForcast = totalHour.getJSONArray("hourly_forecast");

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

                    data.add(new HourlyPull(prettyHolder, civilHolder, tempHolder, feelsHolder, conditionHolder, humidityHolder, iconHolder));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            hourly_Connection.disconnect();
        }


        return null;
    }
}