package com.example.com.sampleflickermappointer;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by ADMIN on 10-04-2017.
 */
public class HttpHandler {

    public HttpHandler() {
        // TODO Auto-generated constructor stub
    }

    public String urltojson(String uri) throws IOException {

        URL url1 = null;
        String line = null;
        StringBuilder stringBuilder = null ;
        try {
            url1 = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(
                    connection.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(in));
            stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");

            }

            in.close();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

}