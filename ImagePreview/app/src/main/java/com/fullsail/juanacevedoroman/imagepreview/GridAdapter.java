package com.fullsail.juanacevedoroman.imagepreview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

/**
 * Created by juanacevedoroman on 10/23/14.
 */
public class GridAdapter extends BaseAdapter {

    private ArrayList<PictureObject> mObjects;
    private Context mContext;

    public GridAdapter(Context _c, ArrayList<PictureObject> _data){

        mContext = _c;
        mObjects = _data;

    }


    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public PictureObject getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){

            convertView = LayoutInflater.from(mContext).inflate(R.layout.cell_grid, parent, false);

        }


        ((SmartImageView)convertView.findViewById(R.id.imageview_cell)).setImageUrl(getItem(position).pictureUrl);


        return convertView;
    }
}
