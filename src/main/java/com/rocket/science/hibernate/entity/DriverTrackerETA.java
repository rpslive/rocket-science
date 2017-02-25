package com.rocket.science.hibernate.entity;

/**
 * Created by shamimh on 25/02/17.
 */
public class DriverTrackerETA extends DriverTracker{

    private Long secondsToArrival;

    public Long getSecondsToArrival() {
        return secondsToArrival;
    }

    public void setSecondsToArrival(Long secondsToArrival) {
        this.secondsToArrival = secondsToArrival;
    }

    public DriverTrackerETA(String cabId, String driverId, Double lat, Double lang, String status, Long secondsToArrival) {
        super(cabId, driverId, lat, lang, status);
        this.secondsToArrival = secondsToArrival;
    }
}
