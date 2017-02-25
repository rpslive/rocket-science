package com.right.triangle.model;

import com.mongodb.BasicDBObject;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by sinraja on 2/25/17.
 */
public class PositionData implements Serializable {

    private static final long serialVersionUID = 2105061907470199595L;

    public PositionData() {
    }

    private Location loc;

    private String status;

    private String driverId;

    private String cabId;

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCabId() {
        return cabId;
    }

    public void setCabId(String cabId) {
        this.cabId = cabId;
    }
}
