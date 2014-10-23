package com.fullsail.juanacevedoroman.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by juanacevedoroman on 8/28/14.
 */

public class DailyAdapter extends BaseAdapter {


    private final long ID_CONSTANT = 0x0000;
    private Context mContext;
    private ArrayList<TenDayObject> mObjects;
    private int mlayout;

    public DailyAdapter(Context c, ArrayList<TenDayObject> objects, int _layout) {
        mContext = c;
        mObjects = objects;
        mlayout = _layout;

    }


    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public TenDayObject getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.day_layout, parent, false);
        }

                TenDayObject item = getItem(position);

            SmartImageView myImage = (SmartImageView) convertView.findViewById(R.id.day_icon);
            TextView day = (TextView) convertView.findViewById(R.id.day_cellDay);
            TextView hi = (TextView) convertView.findViewById(R.id.day_cellHigh);
            TextView low = (TextView) convertView.findViewById(R.id.day_cellLow);

            myImage.setImageUrl(item.iconUrl);
            day.setText(item.weekday);
            hi.setText("High: " + item.high);
            low.setText("Low: " + item.low);


        return convertView;
    }

}
