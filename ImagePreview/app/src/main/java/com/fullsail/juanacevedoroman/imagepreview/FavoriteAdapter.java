package com.fullsail.juanacevedoroman.imagepreview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.loopj.android.image.SmartImageView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by juanacevedoroman on 10/23/14.
 */
public class FavoriteAdapter extends BaseAdapter {

    private Context mContext;
    private  String[] mObject;


    public FavoriteAdapter(Context _c, String[] _data){

        mContext= _c;
        mObject = _data;

    }


    @Override
    public int getCount() {
        return mObject.length;
    }

    @Override
    public String getItem(int position) {
        return mObject[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){

            convertView = LayoutInflater.from(mContext).inflate(R.layout.cell_grid_fav, parent, false);
        }

        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File myDir = new File(root + "/ImagePreview");

        String loo = myDir.getAbsolutePath();



         ((ImageView)convertView.findViewById(R.id.imageview_cell)).setImageBitmap(BitmapFactory.decodeFile(loo + "/" + getItem(position)));



        return convertView;
    }
}
