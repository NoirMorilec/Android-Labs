package com.example.verylonglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.verylonglist.adapter.LabRecyclerViewAdapter;
import com.example.verylonglist.parser.StringToIntParser;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {

    private List<Item> itemList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        itemList = new ArrayList<>();
        recyclerView= findViewById(R.id.itemsRecyclerView);

        String str = StringToIntParser.getParsedString(196615);

        for (int i = 1; i < 1000001; i++ ){
            if(i%2 == 0){
                itemList.add(new Item(i, "Grey"));
            }
            else{
               itemList.add(new Item(i, "White"));
            }
        }

        LabRecyclerViewAdapter adapter = new LabRecyclerViewAdapter(getApplicationContext(), itemList);
        RecyclerView.LayoutManager  manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
