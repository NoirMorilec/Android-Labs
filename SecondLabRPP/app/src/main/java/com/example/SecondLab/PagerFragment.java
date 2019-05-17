package com.example.SecondLab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PagerFragment extends Fragment {
    private ViewPager pager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private String rawList;
    private List<Item> items;
    private int page;

    public PagerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v  = inflater.inflate(R.layout.fragment_pager, container, false);

        rawList = getArguments().getString("List");
        page = getArguments().getInt("Item");

        Type listType = new TypeToken<List<Item>>(){}.getType();
        final GsonBuilder builder = new GsonBuilder();
        Gson gson =  builder.create();
        items = gson.fromJson(rawList, listType);
        items.remove(0);
        pager = v.findViewById(R.id.viewPager);
        fragmentPagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(),
                items);
        pager.setAdapter(fragmentPagerAdapter);
        pager.setCurrentItem(page);
        return v;
    }

}