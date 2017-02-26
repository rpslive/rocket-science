package com.right.triangle.model;

import java.io.Serializable;

/**
 * Created by sinraja on 2/26/17.
 */
public class StatusData implements Serializable {

    private static final long serialVersionUID = 2105061907470199345L;

    private String cabId;
    private String driverId;
    private String status;

    public StatusData() {
    }

    public String getCabId() {
        return cabId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
