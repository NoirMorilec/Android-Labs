package com.example.SecondLab;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class JsonParser {

    public static String getTechString(String url) {
        String response = "";
        try {
            URL techUrl = new URL(url);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) techUrl.openConnection();
            httpsURLConnection.connect();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder buffer = new StringBuilder();
            while ((response = bufferedReader.readLine()) != null) {
                buffer.append(response).append("\n");
            }
            bufferedReader.close();
            httpsURLConnection.disconnect();
            response = buffer.toString();
            return response;

        } catch (MalformedURLException exc) {
            exc.printStackTrace();
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        return "Connection error.";
    }


}

