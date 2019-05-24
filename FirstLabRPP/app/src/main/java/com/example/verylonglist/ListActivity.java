package com.example.verylonglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.verylonglist.adapter.LabRecyclerViewAdapter;
import com.example.verylonglist.parser.StringToIntParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ListActivity extends AppCompatActivity {

    private List<Item> itemList;
    private RecyclerView recyclerView;
    ImageView imageView;
    int idImage;

    Integer[] images = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        itemList = new ArrayList<>();
        recyclerView= findViewById(R.id.itemsRecyclerView);
        Random r;
        imageView = findViewById(R.id.imageView);

        r = new Random();
        for (int i = 1000000; i > 1; i-- ){
            idImage = images[r.nextInt(images.length)];
            if(i%2 == 0){
                itemList.add(new Item(i, "Grey",idImage));
            }
            else{
               itemList.add(new Item(i, "White",idImage));
            }
        }
        LabRecyclerViewAdapter adapter = new LabRecyclerViewAdapter(getApplicationContext(), itemList);
        RecyclerView.LayoutManager  manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        //imageView.setImageResource(images[0]);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
