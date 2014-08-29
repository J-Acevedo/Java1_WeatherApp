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
 * Created by juanacevedoroman on 8/28/14.
 */
public class TenDayForecast extends AsyncTask<String, Integer, String> {


    HttpURLConnection connection;
    String jsonString;

    Context context;
    ArrayList<TenDayObject> data;

    public TenDayForecast(Context _context, ArrayList<TenDayObject> _data){

        context = _context;
        data = _data;
    }


    //On PreExecute
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //Background
    @Override
    protected String doInBackground(String... params) {

        try {

            URL url = new URL(params[0]);

            connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            InputStream is = connection.getInputStream();

            jsonString = IOUtils.toString(is);

            is.close();

            connection.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();


        } finally {

            connection.disconnect();
        }

        //JSON
        try {
            JSONObject response = new JSONObject(jsonString);

            JSONArray daysArray = response.getJSONObject("forecast").getJSONObject("simpleforecast").getJSONArray("forecastday");

            for (int i = 0; i < daysArray.length(); i++){

                JSONObject day = daysArray.getJSONObject(i);

                String weekday = day.getJSONObject("date").getString("weekday");
                String pretty = day.getJSONObject("date").getString("pretty");
                String high = day.getJSONObject("high").getString("fahrenheit");
                String low = day.getJSONObject("low").getString("fahrenheit");
                String conditions = day.getString("conditions");
                String iconUrl = day.getString("icon_url");

                data.add(new TenDayObject(weekday,pretty,high,low,conditions,iconUrl));
            }


            Thread.sleep(300);


        } catch (JSONException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }


        return null;
    }

    //OnProgress
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    //OnPostExecute
    @Override
    protected void onPostExecute(String s) {

        MyActivity activity = (MyActivity)context;

        activity.loadUI(0,2);


    }


}
