package com.rocket.science.utils;

import com.google.gson.Gson;
import com.rocket.science.constants.Constant;
import com.rocket.science.hibernate.entity.DriverTrackerETA;
import com.rocket.science.resources.BookingRequestResource;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;

/**
 * Created by shamimh on 26/02/17.
 */

public abstract class ExternalServiceUtil<T> {

    private static final Logger LOGGER = Logger.getLogger(BookingRequestResource.class);
    public Optional<JSONObject> constructJSONObjectFromModel(List<T> entity){
        Gson gson = new Gson();
        String jsonString = gson.toJson(entity);
        try {
            JSONObject request = new JSONObject(jsonString);
            return Optional.of(request);
        } catch (JSONException e) {
            LOGGER.error("Unable to convert object into GSON "+entity.toString());
            return null;
        }

    };

    public DriverTrackerETA parseJsonToDriverETA(JSONObject jsonOfDrivers){



        String cabId = (String)jsonOfDrivers.get(Constant.DriverConstants.cabId);;
        String driverId = (String)jsonOfDrivers.get(Constant.DriverConstants.driverId);
        Double lat = (Double)jsonOfDrivers.get(Constant.DriverConstants.lat);;
        Double lon = (Double)jsonOfDrivers.get(Constant.DriverConstants.lon);;
        Long seconds = (Long)jsonOfDrivers.get("secondsToArrival");
        String status = (String)jsonOfDrivers.get(Constant.DriverConstants.Status);

        DriverTrackerETA driverTracker = new DriverTrackerETA(cabId,driverId,lat,lon,status,seconds);//driverJson.get();



        return driverTracker;


    };


}
