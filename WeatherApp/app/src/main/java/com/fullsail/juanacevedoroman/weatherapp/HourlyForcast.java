package com.fullsail.juanacevedoroman.weatherapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

            JSONObject response = new JSONObject(hourlyPuller);



                JSONArray hourlyForcast = response.getJSONArray("hourly_forecast");

                for (int x = 0; x < hourlyForcast.length(); x++){

                    JSONObject hourOBJ = hourlyForcast.getJSONObject(x);

                    prettyHolder = hourOBJ.getJSONObject("FCTTIME").getString("pretty");
                    civilHolder = hourOBJ.getJSONObject("FCTTIME").getString("civil");
                    tempHolder = hourOBJ.getJSONObject("temp").getString("english");
                    feelsHolder = hourOBJ.getJSONObject("feelslike").getString("english");
                    conditionHolder = hourOBJ.getString("condition");
                    humidityHolder = hourOBJ.getString("humidity");
                    iconHolder = hourOBJ.getString("icon_url");

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

                if (x == 23){
                    return null;
                }

                    data.add(new HourlyPull(prettyHolder, civilHolder, tempHolder, feelsHolder, conditionHolder, humidityHolder, iconHolder));
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

    @Override
    protected void onPostExecute(String s) {

        MyActivity activity = (MyActivity)mContext;

        activity.hourlyData.addAll(0,data);

        Log.d("DATA", ""+data.size());

        activity.loadUI(0,1,true);

    }
}