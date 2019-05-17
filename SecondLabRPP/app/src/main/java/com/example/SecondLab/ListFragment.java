package com.example.SecondLab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private String list;
    private ListAdapter listAdapter;
    private List<Item> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recyclerView);
        listAdapter = new ListAdapter();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(manager);


        try {
            list = ListScreen.returnResponse();
            listAdapter = new ListAdapter(getContext(), list);
            listAdapter = new ListAdapter(getContext(), list);

            Type listType = new TypeToken<List<Item>>() {}.getType();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            items = gson.fromJson(list, listType);
            items.remove(0);

        } catch (NullPointerException exc) {
            Log.d("error","empty list");
        }

        listAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewListener(getContext(), recyclerView,
                new RecyclerViewListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PagerFragment pagerFragment = new PagerFragment();
                Bundle bundle = new Bundle();
                bundle.putString("List", list);
                bundle.putInt("Item", position);
                pagerFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.forFragments,
                        pagerFragment, "pager").addToBackStack(null).commit();

            }
        }));
        listAdapter.notifyDataSetChanged();
        return fragmentView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
