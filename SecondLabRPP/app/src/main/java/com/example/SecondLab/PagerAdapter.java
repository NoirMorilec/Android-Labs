package com.example.SecondLab;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private List<Item> items;

    public PagerAdapter(FragmentManager fragmentManager, List<Item> list) {
        super(fragmentManager);
        this.items = list;
    }

    @Override
    public Fragment getItem(int i) {
        Log.d("TT", "getItem: " + items.get(i).getName());
        return PageFragment.newInstance(i, items.get(i).getName(), items.get(i).getHelpText(),
                items.get(i).getGraphic());

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return items.get(position).getName();
    }
}