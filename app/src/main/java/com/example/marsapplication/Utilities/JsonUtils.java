package com.example.marsapplication.Utilities;

import android.util.Log;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.marsapplication.Data.RoverManifest;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    private static String CLASS_NAME = JsonUtils.class.getSimpleName();

    public static RoverManifest jsonManifestData(String json){

        RoverManifest myRoverManifest = null;

        try {

            JSONObject baseJsonResponse = new JSONObject(json);
            JSONObject photo_manifest = baseJsonResponse.getJSONObject("photo_manifest");
            String rvName = photo_manifest.getString("name");
            String rvStatus = photo_manifest.getString("status");
            String rvLandingDate = photo_manifest.getString("landing_date");
            String rvLaunchDate = photo_manifest.getString("launch_date");
            int rvTotalPhotos = photo_manifest.getInt("total_photos");
            String rvLastPhoto =  photo_manifest.getString("max_date");

            myRoverManifest = new RoverManifest(rvName, rvStatus, rvLandingDate, rvLaunchDate, rvTotalPhotos, rvLastPhoto);
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "Programl parsing JSON result", e);
        }

        return myRoverManifest;
    }


}
