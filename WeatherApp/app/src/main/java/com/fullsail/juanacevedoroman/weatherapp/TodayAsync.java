package com.fullsail.juanacevedoroman.weatherapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Matthew Dutton on 8/28/14.
 */

public class TodayAsync extends AsyncTask<String, Integer, String> {

    protected Context context;

    public ArrayList<TodayObject> today_data;

    protected String today_JSON;

    protected String city;
    protected String condition;
    protected String temperature;
    protected String humidity;
    protected String feelsLike;
    protected String iconURL;
    protected String observationTime;
    private int wind;
    protected String windMph;
    protected String pressure;
    protected String visability;
    protected String dewPoint;
    protected String precipToday;

    private HttpURLConnection today_Connection;


    public TodayAsync(Context context, ArrayList<TodayObject> _today_data) {

        this.context = context;
        this.today_data = _today_data;

    }

    @Override
    protected String doInBackground(String... params) {

        try{

            URL today_url = new URL( params[0] );

            today_Connection = (HttpURLConnection) today_url.openConnection();

            today_Connection.connect();

            InputStream today_Input = today_Connection.getInputStream();

            today_JSON = IOUtils.toString(today_Input);

            today_Input.close();

            //the entire JSON return
            JSONObject outerObj = new JSONObject(today_JSON);

            // gets the meat of the return, everything that is needed is in here.
            JSONObject current_observation = outerObj.getJSONObject("current_observation");

            // gets the information about the location being observed
            JSONObject display_location = current_observation.getJSONObject("display_location");

            if ( display_location.has("full") ) {
                city = display_location.getString("full");
            }
            if ( current_observation.has("temperature_string") ) {
                temperature = current_observation.getString("temperature_string");
            }
            if (current_observation.has("weather")){
                condition = current_observation.getString("weather");
            }
            if ( current_observation.has("relative_humidity") ) {
                humidity = current_observation.getString("relative_humidity");
            }
            if (current_observation.has("feelslike_string")){
                feelsLike = current_observation.getString("feelslike_string");
            }
            if (current_observation.has("icon_url") ){
                iconURL = current_observation.getString("icon_url");
            }
            if ( current_observation.has("observation_time") ){
                observationTime = current_observation.getString("observation_time");
            }
            if ( current_observation.has("wind_mph") ) {
                wind = current_observation.getInt("wind_mph");
                windMph = Integer.toString(wind);
            }
            if (current_observation.has("pressure_in")){
                pressure = current_observation.getString("pressure_in");
            }
            if (current_observation.has("visibility_mi") ){
                visability = current_observation.getString("visibility_mi");
            }
            if ( current_observation.has("dewpoint_string") ){
                dewPoint = current_observation.getString("dewpoint_string");
            }
            if ( current_observation.has("precip_today_string") ){
                precipToday = current_observation.getString("precip_today_string");
            }


            /*
            Log.d("DUTTON", "\n"+city+"\n"+temperature+"\n"+condition+"\n"+humidity+"\n"+feelsLike+"\n"+
            iconURL+"\n"+observationTime+"\n"+windMph+"\n"+pressure+"\n"+visability+"\n"+dewPoint+"\n"+precipToday);
            */

            today_data.add(new TodayObject(city, temperature, condition, humidity, feelsLike, iconURL,
                    observationTime, windMph, pressure, visability, dewPoint, precipToday));


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e ){
            e.printStackTrace();

        } catch (JSONException e){
            e.printStackTrace();

        } finally {

            today_Connection.disconnect();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        MyActivity activity = (MyActivity)context;

        activity.todayData.addAll(0,today_data);

        activity.loadUI(0,0);

    }
}
