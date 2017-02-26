package com.rocket.science.services;

import com.oracle.javafx.jmx.json.JSONException;
import com.rocket.science.helper.Constants;
import com.rocket.science.helper.HttpClientHelper;
import com.rocket.science.hibernate.entity.Driver;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.glassfish.jersey.internal.inject.Custom;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by pawan.gupta on 25/02/17.
 */
public class DriverManagementService {

    public Driver bookDriver(List<Driver> drivers){

        for (Driver driver : drivers) {
            String status = getStatus(driver);
            if (status.equals(Constants.DriverStatus.IDLE.toString())) {
                setStatus(driver,Constants.DriverStatus.IN_TRIP.toString());
                notifyDriver(driver);
                return driver;
            }
        }
        return null;
    }

    public boolean notifyDriver(Driver driver){return true;}

    public boolean cancelTrip(Driver driver){
        return Boolean.parseBoolean(new HttpClientHelper().getRequest("booking service").toString()) &&
                setStatus(driver,Constants.DriverStatus.IDLE.toString());
    }

    public boolean setStatus(Driver driver,String status){
            try {
                return Boolean.parseBoolean(new HttpClientHelper().postRequest("tracking service",
                        new JSONObject("{\"cabId\":"+driver.cabId+",\"driverId\":"+driver.driverId+",\"status\":"+status+"}")).toString());
            } catch (org.json.JSONException e) {
                e.printStackTrace();
            }

            return false;
    }

    public String getStatus(Driver driver){
        return new HttpClientHelper().getRequest("tracking service").toString();
    }

    public String trackDriver(Driver driver){
        try {
            return new HttpClientHelper().postRequest("tracking service",
                    new JSONObject("{\"cabId\":"+driver.cabId+",\"driverId\":"+driver.driverId+
                            ",\"status\":"+Constants.DriverStatus.IN_TRIP.toString()+"}")).toString();
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


}
