package com.rocket.science.hibernate.entity;

import java.io.Serializable;

/**
 * Created by shamimh on 26/02/17.
 */
public class Position implements Serializable {
    String lat;
    String lon;

    public Position(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Position() {
    }

    @Override
    public String toString() {
        return "Position{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }

    public String getLat() {

        return lat;

    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
