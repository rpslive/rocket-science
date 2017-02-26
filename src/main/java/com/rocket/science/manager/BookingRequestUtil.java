package com.rocket.science.manager;

import com.google.gson.Gson;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.rocket.science.constants.Constant;
import com.rocket.science.hibernate.entity.BookingRequest;
import com.rocket.science.hibernate.entity.DriverTracker;
import com.rocket.science.hibernate.entity.DriverTrackerETA;
import com.rocket.science.resources.BookingRequestResource;
import com.rocket.science.utils.ManagerUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by shamimh on 25/02/17.
 */
@Component
@Configurable
public class BookingRequestUtil extends ManagerUtil<BookingRequest,DriverTrackerETA,DriverTracker> {

    private static final Logger LOGGER = Logger.getLogger(BookingRequestUtil.class);


    @Autowired
    @Qualifier(value="numberOfTopDriver")
    private Integer numberOfTopDriver;

    @Autowired
    @Qualifier(value = "geoApiContext")
    private GeoApiContext geoApiContext ;

    @Override
    public List<DriverTrackerETA> process(BookingRequest entity, List<DriverTracker> driverTrackers) throws Exception {

        DistanceMatrixApiRequest distanceMatrixApiRequest = new DistanceMatrixApiRequest(geoApiContext);

        List<LatLng> listOfLatLng = new ArrayList<>();

        //construct lat and log object
        driverTrackers.stream().filter(e -> (StringUtils.isNotEmpty(e.getCabId()) &&
                StringUtils.isNotEmpty(e.getDriverId()))).filter(e -> e.getStatus().equals("IDLE")).forEach(e ->{
                   listOfLatLng.add(new LatLng(e.getLat(),e.getLang()));

        });

        distanceMatrixApiRequest = distanceMatrixApiRequest.destinations(listOfLatLng.toArray(new LatLng[listOfLatLng.size()]));
        distanceMatrixApiRequest.mode(TravelMode.DRIVING);

        // this is a matrix, so need to traverse and extract only the information we need
        DistanceMatrix  distanceMatrix=  distanceMatrixApiRequest.await();

       // List<DriverTrackerETA> topNDrivers =
        return getTopDriverWithLowestETA(distanceMatrix,driverTrackers);




    }


    /**
     * get the drivers with lowest ETA to destination, and send it to Booking Managment Service for
     * Booking the driver
     * @param distanceMatrix
     * @param driverTrackers
     * @return
     */
    private List<DriverTrackerETA> getTopDriverWithLowestETA(DistanceMatrix distanceMatrix, List<DriverTracker> driverTrackers){

        List<DriverTrackerETA> listOfTopDriver = new ArrayList<>();
        int sizeOfDistanceMatrix = distanceMatrix.rows.length;
        for(int i = 0 ; i < sizeOfDistanceMatrix ;i++){

            DriverTracker driverTracker = driverTrackers.get(i);
            Long secondsToArrival =  distanceMatrix.rows[i].elements[i].durationInTraffic.inSeconds;
            listOfTopDriver.add(new DriverTrackerETA(driverTracker.getCabId(), driverTracker.getDriverId(), driverTracker.getLat() , driverTracker.getLang(),driverTracker.getStatus() , secondsToArrival));

        }
        return listOfTopDriver;
    }



    @Override//TODO need to implment this class, although we can do without it now
    public List<DriverTracker> parseJsonToDrivers(JSONObject jsonOfDrivers){
        List<DriverTracker> driverTrackers = new ArrayList<>();
        JSONArray msg = (JSONArray) jsonOfDrivers.get("PositionData");
        Iterator<Object> iterator = msg.iterator();
        while (iterator.hasNext()) {
            JSONObject driverJson = (JSONObject) iterator.next();
            //TODO implement builder/step builder pattern if time permits.
            String cabId = (String)driverJson.get(Constant.DriverConstants.cabId);;
            String driverId = (String)driverJson.get(Constant.DriverConstants.driverId);
            Double lat = (Double)driverJson.get(Constant.DriverConstants.lat);;
            Double lon = (Double)driverJson.get(Constant.DriverConstants.lon);;
            String status = (String)driverJson.get(Constant.DriverConstants.Status);
            DriverTracker driverTracker = new DriverTracker(cabId,driverId,lat,lon,status);//driverJson.get();
            driverTrackers.add(driverTracker);

        }
        return driverTrackers;


    };




}
