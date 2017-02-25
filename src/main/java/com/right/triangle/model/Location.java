package com.right.triangle.model;

import java.io.Serializable;

/**
 * Created by sinraja on 2/26/17.
 */
public class Location implements Serializable {

    private static final long serialVersionUID = 2105061907470199595L;

    private double lon;
    private double lat;

    public double getLon() {
        return lon;
    }

    public Location() {
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Location(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }
}
