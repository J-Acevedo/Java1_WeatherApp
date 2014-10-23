package com.fullsail.juanacevedoroman.imagepreview;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MyActivity extends Activity implements ActionBar.OnNavigationListener, GridFragment.GridFragmentListener, FavoriteFragment.FavoriteFragmentListener {


    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new String[] {
                                getString(R.string.title_section1),
                                getString(R.string.title_section2)
                        }),
                this);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getActionBar().getSelectedNavigationIndex());
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        // When the given dropdown item is selected, show its contents in the
        // container view.

        switch (position){
            case 0:
               MyTask task = new MyTask();
                task.execute("http://www.panoramio.com/map/get_panoramas.php?set=public&from=0&to=20&minx=-180&miny=-90&maxx=180&maxy=90&size=medium&mapfilter=true");


                break;

            case 1:


                getFragmentManager().beginTransaction()
                        .replace(R.id.container, FavoriteFragment.newInstance(),FavoriteFragment.TAG)
                        .commit();


                break;
        }


        return true;
    }

    @Override
    public void viewImageFav(String _s) {

        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File myDir = new File(root + "/ImagePreview");

        String loo = myDir.getAbsolutePath();

        Intent view = new Intent(Intent.ACTION_VIEW);
        view.setDataAndType(Uri.parse("file://" + loo + "/"+ _s),"image/*");

        Log.d("Tag",loo + "/"+ _s);

        startActivity(view);

    }

    @Override
    public void viewImage(String _url) {



        Intent view = new Intent(Intent.ACTION_VIEW);
        view.setDataAndType(Uri.parse(_url),"image/*");
        startActivity(view);


    }





    private class MyTask extends AsyncTask<String, Integer, ArrayList<PictureObject>>{

        ArrayList<PictureObject> data = new ArrayList<PictureObject>();

        HttpURLConnection connection;
        String jsonString;


        @Override
        protected ArrayList<PictureObject> doInBackground(String... params) {

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

            try {
                JSONObject response = new JSONObject(jsonString);
                JSONArray photos = response.getJSONArray("photos");

                data.clear();
                for (int i = 0; i < photos.length(); i++) {

                    JSONObject picture = photos.getJSONObject(i);


                    String picUrl = picture.getString("photo_file_url");
                    String picname = picture.getString("photo_title");

                    data.add(new PictureObject(picname, picUrl));

                }

            } catch (JSONException e) {

                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }


            return data;
        }


        @Override
        protected void onPostExecute(ArrayList<PictureObject> pictureObjects) {
            super.onPostExecute(pictureObjects);


            getFragmentManager().beginTransaction()
                    .replace(R.id.container, GridFragment.newInstance(pictureObjects),GridFragment.TAG)
                    .commit();

        }
    }


}
