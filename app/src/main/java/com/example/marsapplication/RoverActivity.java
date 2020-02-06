package com.example.marsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.marsapplication.Data.RoverManifest;
import com.example.marsapplication.Utilities.JsonUtils;
import com.example.marsapplication.Utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoverActivity extends AppCompatActivity {

    final static String CLASS_NAME = RoverActivity.class.getSimpleName();

    @BindView(R.id.curiosity_button)
    ImageView topImageView;

    /* @BindView(R.id.toolbar_rover)
    Toolbar roverToolbar; */

    private Toolbar roverToolbar;
    private String whichRover;
    private RoverManifest roverManifest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover);

        Intent intent = getIntent();
        whichRover = intent.getStringExtra("rover_id");
        Log.i(CLASS_NAME, whichRover);

        roverToolbar = findViewById(R.id.toolbar_rover);
        roverToolbar.setTitle(whichRover);

        URL searchPhotoURL = NetworkUtils.buidPhotoURL(whichRover, "2015-6-3", "FHAZ");
        Log.i(CLASS_NAME, searchPhotoURL.toString());

        getRoverDataTask task = new getRoverDataTask();
        task.execute();
    }

    private class getRoverDataTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls){
            URL manifestURL = NetworkUtils.buidManifestURL(whichRover);
            Log.i(CLASS_NAME, manifestURL.toString());
            String myRoverManifestString = "";
            try {
                myRoverManifestString = NetworkUtils.getResponseFromHttpUrl(manifestURL);
            } catch (IOException e){
                Log.e(CLASS_NAME, "Problem making the HTTP request.", e);
            }

            return myRoverManifestString;
        }

        @Override
        protected void onPostExecute(String myString){
            if (myString == ""){
                return;
            } else {
                roverManifest = JsonUtils.jsonManifestData(myString);
            }
        }

    }
}
