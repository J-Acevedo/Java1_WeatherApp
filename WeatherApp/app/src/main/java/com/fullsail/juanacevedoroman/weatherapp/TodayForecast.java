package com.fullsail.juanacevedoroman.weatherapp;

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

/**
 * Created by Matt on 8/28/14.
 */

public class TodayForecast extends AsyncTask<String, Integer, String> {

    String today_JSON;

    String city;
    String condition;
    String temperature;
    String humidity;
    String feelsLike;
    String iconURL;
    String observationTime;
    int wind;
    String windMph;
    String pressure;
    String visability;
    String dewPoint;
    String precipToday;

    HttpURLConnection today_Connection;


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
            if ( display_location.has("temperature_string") ) {
                temperature = display_location.getString("temperature_string");
            }
            if (display_location.has("weather")){
                condition = display_location.getString("weather");
            }
            if ( display_location.has("relative_humidity") ) {
                humidity = display_location.getString("relative_humidity");
            }
            if (display_location.has("feelslike_string")){
                feelsLike = display_location.getString("feelslike_string");
            }
            if (display_location.has("icon_url") ){
                iconURL = display_location.getString("icon_url");
            }
            if ( display_location.has("observation_time") ){
                observationTime = display_location.getString("ObservationTime");
            }
            if ( display_location.has("wind_mph") ) {
                wind = display_location.getInt("wind_mph");
                windMph = Integer.toString(wind);
            }
            if (display_location.has("pressure_in")){
                pressure = display_location.getString("pressure_in");
            }
            if (display_location.has("visibility_mi") ){
                visability = display_location.getString("visibility_mi");
            }
            if ( display_location.has("dewpoint_string") ){
                dewPoint = display_location.getString("dewpoint_string");
            }
            if ( display_location.has("precip_today_string") ){
                precipToday = display_location.getString("precip_today_string");
            }



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
    protected void onPreExecute() {
        super.onPreExecute();

        Log.d("DUTTON", "today forecast");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
