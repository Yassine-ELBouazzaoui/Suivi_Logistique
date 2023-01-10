package com.example.suivilogistique;

import java.io.Serializable;

public class LocationUser implements Serializable {
    String latitude;
    String longitude;
    String codeM;
    String position;

    public LocationUser(String latitude, String longitude, String codeM, String position) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.codeM = codeM;
        this.position = position;
    }

    public LocationUser(String latitude, String longitude, String codeM) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.codeM = codeM;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCodeM() {
        return codeM;
    }

    public void setCodeM(String codeM) {
        this.codeM = codeM;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
