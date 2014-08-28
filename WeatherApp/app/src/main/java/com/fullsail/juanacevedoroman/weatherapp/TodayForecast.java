package com.fullsail.juanacevedoroman.weatherapp;

import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Matt on 8/28/14.
 */

public class TodayForecast extends AsyncTask<String, Integer, String> {

    HttpURLConnection today_Connection;

    String today_JSON;


    @Override
    protected String doInBackground(String... params) {

        try{

            URL today_url = new URL( params[0] );

            today_Connection = (HttpURLConnection) today_url.openConnection();

            today_Connection.connect();

            InputStream today_Input = today_Connection.getInputStream();

            today_JSON = IOUtils.toString(today_Input);

            today_Input.close();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e ){
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
