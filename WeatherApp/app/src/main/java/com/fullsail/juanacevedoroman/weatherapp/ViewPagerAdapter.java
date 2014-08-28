package com.fullsail.juanacevedoroman.weatherapp;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by juanacevedoroman on 8/28/14.
 */
public class ViewPagerAdapter extends PagerAdapter {


    Activity activity;
    int imageArray[];

    public ViewPagerAdapter(Activity act, int[] imgArra) {
        imageArray = imgArra;
        activity = act;
    }

    public int getCount() {
        return imageArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == ((View) o);
    }


}
