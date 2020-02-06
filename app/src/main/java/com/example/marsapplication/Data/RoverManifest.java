package com.example.marsapplication.Data;

import java.util.Date;

public class RoverManifest {

    String roverName;
    String roverStatus;
    String roverLandingDate;
    String roverLaunchDate;
    int roverTotalPhotos;
    String roverLastPhoto;

    public RoverManifest(String rvName, String rvStatus, String rvLandingDate, String rvLaunchDate, int rvTotalPhotos, String rvLastPhoto){
        roverName = rvName;
        roverStatus = rvStatus;
        roverLandingDate = rvLandingDate;
        roverLaunchDate = rvLaunchDate;
        roverTotalPhotos = rvTotalPhotos;
        roverLastPhoto = rvLastPhoto;
    }

    public String getRoverName(){
        return roverName;
    }

    public String getRoverStatus(){
        return roverStatus;
    }

    public String getRoverLandingDate(){
        return roverLandingDate;
    }

    public String getRoverLaunchDate(){
        return roverLaunchDate;
    }

    public int getRoverTotalPhotos(){
        return roverTotalPhotos;
    }

    public String getRoverLastPhoto(){
        return roverLastPhoto;
    }
}
