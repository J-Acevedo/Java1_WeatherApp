package com.fullsail.juanacevedoroman.imagepreview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class GridFragment extends Fragment {


    private GridFragmentListener mListener;

    public static final String TAG = "GridFragment";
    public static final String DATA_KEY = "DataKey";

    private GridAdapter mAdapter;
    private ActionMode mActionMode;

    private int x;


    public static GridFragment newInstance(ArrayList<PictureObject> _obj) {
        GridFragment fragment = new GridFragment();

        Bundle args = new Bundle();
        args.putSerializable(DATA_KEY, _obj);
        fragment.setArguments(args);


        return fragment;
    }

    public GridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        if (getArguments() != null) {

            mAdapter = new GridAdapter(getActivity(), (ArrayList<PictureObject>) getArguments().getSerializable(DATA_KEY));
            ((GridView) view.findViewById(R.id.grid)).setAdapter(mAdapter);
            ((GridView) view.findViewById(R.id.grid)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    mListener.viewImage(mAdapter.getItem(position).pictureUrl);
                }
            });



        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        GridView gv = (GridView)getActivity().findViewById(R.id.grid);
               gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                   @Override
                   public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                       Log.d(TAG, "LONG PRESSED");

                       // if actionmode is null "not started"
                       if (mActionMode != null) {
                           mActionMode.finish();
                           return false;
                       }else {

                           // Start the CAB
                           mActionMode = getActivity().startActionMode(SaveActionMode);

                           x = position;


                           return true;
                       }

                   }
               });

    }

    //CONTEXTUAL ACTION CALLBACK
    private ActionMode.Callback SaveActionMode = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater menuInflater = actionMode.getMenuInflater();
            menuInflater.inflate(R.menu.action_callback, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false; /*Returning nothing if set to false*/
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {


            SaveImage saveImage = new SaveImage();
            saveImage.execute(mAdapter.getItem(x).pictureUrl);

            mActionMode.finish();
            return true;


        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
        }
    };

    //
    private class SaveImage extends AsyncTask<String, Integer, Bitmap> {

        Bitmap pic;

        @Override
        protected Bitmap doInBackground(String... params) {

            URL myFileUrl = null;
            try {
                myFileUrl = new URL(params[0]);
            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
            try {
                HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                Log.d("im connected", "Download");
                pic = BitmapFactory.decodeStream(is);


            } catch (IOException e) {

                e.printStackTrace();
            }


            return pic;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            savePic(bitmap);
           // storeImage(bitmap,"lol");

        }
    }
    //

    private void savePic(Bitmap _bit){

        String imageName = new SimpleDateFormat("MMddyyyy_HHmmss").format(new Date(System.currentTimeMillis()));

        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File myDir = new File(root + "ImagePreview");
        myDir.mkdirs();

        File image = new File(myDir, imageName + ".jpg");
        try{
            image.createNewFile();
            FileOutputStream out = new FileOutputStream(image);

            _bit.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            scanPhoto(imageName);

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    private void scanPhoto(String imageFileName){

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imageFileName);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }




    //

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (GridFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement GridFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    public interface GridFragmentListener {

        public void viewImage(String _url);
    }

}
