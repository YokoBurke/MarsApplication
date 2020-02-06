package com.example.marsapplication.Utilities;

import android.net.Uri;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import butterknife.ButterKnife;

public class NetworkUtils {

    final static String CLASS_NAME = NetworkUtils.class.getSimpleName();
    final static String myURL = "api.nasa.gov";
    final static String API_URL = "DEMO_KEY";

    //https://api.nasa.gov/mars-photos/api/v1/manifests/curiosity?&api_key=DEMO_KEY
    //"api.nasa.gov/mars-photos/api/v1/rovers/";

    public static URL buidPhotoURL(String myRover, String myDate, String myCamera){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
            .authority(myURL)
            .appendPath("mars-photos")
            .appendPath("api")
            .appendPath("v1")
            .appendPath(myRover)
            .appendPath("photos")
            .appendQueryParameter("earth_date", myDate)
            .appendQueryParameter("camera", myCamera)
            .appendQueryParameter("api_key", API_URL)
            .build();

        URL url = null;
        try {
            url = new URL(builder.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;

    }

    public static URL buidManifestURL(String myRover){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(myURL)
                .appendPath("mars-photos")
                .appendPath("api")
                .appendPath("v1")
                .appendPath("manifests")
                .appendPath(myRover)
                .appendQueryParameter("api_key", API_URL)
                .build();

        URL url = null;
        try {
            url = new URL(builder.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;

    }

    public static String getResponseFromHttpUrl (URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(CLASS_NAME, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(CLASS_NAME, "Problem retrieving the JSON result.", e);
        }finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output  = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String myLine = reader.readLine();
            while (myLine != null){
                output.append(myLine);
                myLine = reader.readLine();
            }
        }

        return output.toString();
    }

}
