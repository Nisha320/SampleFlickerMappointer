package com.example.com.sampleflickermappointer;

import java.io.Serializable;

/**
 * Created by ADMIN on 10-04-2017.
 */
public class Model implements Serializable {

    String latitude;
    String longitude;
    String ownername;
    String  title;

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

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
