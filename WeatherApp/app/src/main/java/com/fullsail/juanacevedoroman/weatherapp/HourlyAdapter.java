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
 * Created by davidamalfitano on 8/28/14.
 */
public class HourlyAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<HourlyPull> mObjects;
    int mLayout;

    private static final long ID_CONSTANT = 0x010000000;

    public HourlyAdapter(Context c, ArrayList objects, int hour_list_cell){
        mContext = c;
        mObjects = objects;
        mLayout = hour_list_cell;
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public HourlyPull getItem(int pos) {
        return mObjects.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return ID_CONSTANT + pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    mLayout, parent, false);
        }

        HourlyPull item = getItem(pos);

        TextView timeT = (TextView)convertView.findViewById(R.id.hour_cellTime);
        TextView tempT = (TextView)convertView.findViewById(R.id.hour_cellTemp);
        SmartImageView imageSI = (SmartImageView)convertView.findViewById(R.id.hour_cellImage);

        timeT.setText(item.civilPull);
        tempT.setText(item.tempPull + " F");
        imageSI.setImageUrl(item.icon_urlPull);


        return convertView;
    }
}
