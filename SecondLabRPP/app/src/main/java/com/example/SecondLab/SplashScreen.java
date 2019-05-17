package com.example.SecondLab;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final GetJson json = new GetJson(this);
        json.execute(getResources().getString(R.string.info_url));

        json.setListener(new ResponseListener() {
            @Override
            public void onResponseListener(String data) {
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startListActivity(json.response);
                finish();
            }
        }, 2000);

    }

    public interface ResponseListener {
        void onResponseListener(String data);
    }

    private void startListActivity(String response) {

        Intent intent = new Intent(SplashScreen.this, ListScreen.class);
        intent.putExtra("J", response);
        //Log.d("MAIN ACTIVITY", response.length() + " ");
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);
    }


    private class GetJson extends AsyncTask<String, String, String> {
        Activity activity;
        ResponseListener listener;
        String url;
        String response;

        public GetJson(Activity activity) {
            this.activity = activity;
        }

        public void setListener(ResponseListener responseListener) {
            this.listener = responseListener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            url = getResources().getString(R.string.info_url);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            listener.onResponseListener(response);
        }

        @Override
        protected String doInBackground(String... strings) {
            response = JsonParser.getTechString(url);
            res = response;
            return null;
        }
    }
}
