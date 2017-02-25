package com.rocket.science.services;

import com.rocket.science.helper.Constants;
import com.rocket.science.helper.HttpClientHelper;
import com.rocket.science.hibernate.entity.Driver;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.internal.inject.Custom;

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
        return Boolean.parseBoolean(new HttpClientHelper().getRequest("tracking service").toString());
    }

    public String getStatus(Driver driver){
        return new HttpClientHelper().getRequest("tracking service").toString();
    }

    public String trackDriver(Driver driver){
        return new HttpClientHelper().getRequest("tracking service").toString();
    }


}
