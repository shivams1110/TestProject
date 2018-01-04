package com.royalways.testproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Intel on 04-01-2018.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private int count;
    private ArrayList<String> catList = new ArrayList<>();

    public TabAdapter(FragmentManager fm, int tabCount, ArrayList<String> catList) {
        super(fm);
        this.count =tabCount;
        this.catList = catList;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("val",catList.get(position));
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return count;
    }
}
