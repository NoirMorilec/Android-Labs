package com.example.SecondLab;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class ListScreen extends AppCompatActivity {

    private static String response = "";
    final static String FILENAME = "file";
    private File myFile;
    FileOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle extras = getIntent().getExtras();
        response = extras.getString("J");


        saveData(response);

        if(fragmentManager.findFragmentByTag("lister") == null){
            ListFragment listFragment = new ListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.forFragments, listFragment,
                    "lister").addToBackStack(null).commit();
        }

        try {
            Log.d("List GOT RESPONSE ", response.length() + " ");
        } catch (NullPointerException exc){
            exc.printStackTrace();
        }    }

    public static String returnResponse() {
        return response;
    }
    public void saveData(String mJsonResponse) {
        try {
            outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(mJsonResponse.getBytes());
            outputStream.close();
            Log.d("OK!", "data is saved ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
