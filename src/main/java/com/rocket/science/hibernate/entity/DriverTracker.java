package com.rocket.science.hibernate.entity;

import java.io.Serializable;

/**
 * Created by shamimh on 25/02/17.
 *
 */

public class DriverTracker implements Serializable {

    private String cabId;
    private  String driverId;
    private Double lat;

    private Double lang;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getCabId() {
        return cabId;
    }

    public DriverTracker(String cabId, String driverId, Double lat, Double lang,String status) {
        this.cabId = cabId;
        this.driverId = driverId;
        this.lat = lat;
        this.lang = lang;
        this.status = status;
    }

    public void setCabId(String cabId) {

        this.cabId = cabId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLang() {
        return lang;
    }

    public void setLang(Double lang) {
        this.lang = lang;
    }



}
