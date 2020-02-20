package com.example.marsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.marsapplication.Data.RoverManifest;
import com.example.marsapplication.Utilities.JsonUtils;
import com.example.marsapplication.Utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoverActivity extends AppCompatActivity {

    final static String CLASS_NAME = RoverActivity.class.getSimpleName();

    Spinner cameraSpinner;


    @BindView(R.id.toolbar_rover)
    Toolbar roverToolbar;

    @BindView(R.id.text_rs_status)
    TextView roverStatusTextView;

    @BindView(R.id.text_rs_launch_date)
    TextView roverLaunchDateTextView;

    @BindView(R.id.text_rs_landing_date)
    TextView roverLandingDateTextView;

    @BindView(R.id.text_rs_total_photo)
    TextView roverTotalPhotoTextView;

    @BindView(R.id.text_rs_max_date)
    TextView roverMaxTextView;


    private String whichRover;
    private RoverManifest roverManifest;
    private String[] spinnerStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        whichRover = intent.getStringExtra("rover_id");

        roverToolbar.setTitle(whichRover);

        URL searchPhotoURL = NetworkUtils.buidPhotoURL(whichRover, "2015-6-3", "FHAZ");
        Log.i(CLASS_NAME, searchPhotoURL.toString());


        if (whichRover.equals(getResources().getString(R.string.curiosity))) {
            spinnerStrings = getResources().getStringArray(R.array.curiosity_camera);
        } else if (whichRover.equals(getResources().getString(R.string.opportunity))){
            spinnerStrings = getResources().getStringArray(R.array.oppotunity_camera);
        } else if (whichRover.equals(getResources().getString(R.string.spirit))){
            spinnerStrings = getResources().getStringArray(R.array.spirit_camera);
        }

        cameraSpinner = findViewById(R.id.simpleSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cameraSpinner.setAdapter(adapter);

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
                roverStatusTextView.setText(roverManifest.getRoverStatus());
                roverLaunchDateTextView.setText(roverManifest.getRoverLaunchDate());
                roverLandingDateTextView.setText(roverManifest.getRoverLandingDate());
                roverTotalPhotoTextView.setText(roverManifest.getRoverTotalPhotos());
                roverMaxTextView.setText(roverManifest.getRoverLastPhoto());
            }
        }

    }
}
