package com.example.el.objectsroute.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.el.objectsroute.ui.fragment.MapFragment;
import com.example.el.objectsroute.ui.fragment.ObjectListFragment;

/**
 * Created by el on 14.03.2018.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter{

    public static final int LIST_INDEX = 0;
    public static final int MAP_INDEX = 1;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       switch (position) {
           case MAP_INDEX:
               return MapFragment.getInstance();
           default:
               return ObjectListFragment.getInstance();
       }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
