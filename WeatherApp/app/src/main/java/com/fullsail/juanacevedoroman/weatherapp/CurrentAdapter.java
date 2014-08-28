package com.fullsail.juanacevedoroman.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

/**
 * Created by juanacevedoroman on 8/28/14.
 */
public class CurrentAdapter extends BaseAdapter {

    private final long ID_CONSTANT = 0x0000;
    private Context mContext;
    private ArrayList<TodayObject> mObjects;
    private int mlayout;

    public CurrentAdapter(Context c, ArrayList<TodayObject> objects, int _layout) {
        mContext = c;
        mObjects = objects;
        mlayout = _layout;

    }


    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public TodayObject getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mlayout, parent, false);
        }

        // object item based on the position
        if (mlayout == R.layout.current_list_cell) {

            TodayObject item = getItem(position);

/*
            TextView wind = (TextView) convertView.findViewById(R.id.current_theInfo);
            TextView pressure = (TextView) convertView.findViewById(R.id. c);
            TextView visibility = (TextView) convertView.findViewById(R.id.day_cellDay);
            TextView dew = (TextView) convertView.findViewById(R.id.day_cellHigh);
            */
            TextView precip = (TextView) convertView.findViewById(R.id.current_theInfo);



            precip.setText(item.currentInfo.get("precip"));


        }

        return convertView;
    }
}
