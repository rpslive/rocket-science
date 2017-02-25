package com.rocket.science.resources;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rocket.science.constants.Constant;
import com.rocket.science.externalServices.DriverManagerService;
import com.rocket.science.helper.HttpClientHelper;
import com.rocket.science.hibernate.entity.BookingRequest;
import com.rocket.science.hibernate.entity.DriverTracker;
import com.rocket.science.hibernate.entity.DriverTrackerETA;
import com.rocket.science.services.BookingRequestService;
import com.rocket.science.utils.ManagerUtil;
import com.rocket.science.utils.ResourceUtil;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;


/**
 * Created by shamimh on 25/02/17.
 */
@Path("book")
public class BookingRequestResource extends ResourceUtil<BookingRequest>{

    private static final Logger LOGGER = Logger.getLogger(BookingRequestResource.class);


    @Autowired
    private ManagerUtil<BookingRequest,DriverTrackerETA,DriverTracker> managerUtil;

    @Inject
    public BookingRequestResource(BookingRequestService bookingRequestService){
        this.service = bookingRequestService;
    }

    @Autowired
    @Qualifier(value = "httpClientHelper")
    HttpClientHelper httpClientHelper;

    @Autowired
    DriverManagerService driverManagerService;


    @POST
    @Path("/bookCab")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response BookCab(BookingRequest entity ){
        Map<String,String> responseMap = new HashMap<>();



        // save the data to Database for use age later.
        //TODO : validate agency data
        boolean result = service.add(entity);
        if(result != true){
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        Optional<JSONObject> jsonOfCustomerLocation = managerUtil.constructJSONObjectFromModel(entity);
        if(!jsonOfCustomerLocation.isPresent())
        {
             return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();

        }
        //call the Tracker service using post with driver location to get list of nearby drivers
        Optional<JSONObject> jsonOfDrivers = httpClientHelper.postRequest(Constant.TrackingService.TrackingServiceURI,jsonOfCustomerLocation.get());
        if(!jsonOfDrivers.isPresent()){
            Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        List<DriverTracker> driverTracker = managerUtil.parseJsonToDrivers(jsonOfDrivers.get());

        List<DriverTrackerETA> topOptimisedDriversToBook = new ArrayList<>();
        try {
            // get the top Optimised driver to call Booking Service
            topOptimisedDriversToBook.addAll(managerUtil.process(entity,driverTracker));
        } catch (Exception e) {
           LOGGER.error("exception from google map Service"+e.getMessage());
        }

        DriverTrackerETA bookedDriverdriver = driverManagerService.getResponseFromDriverMangementService(topOptimisedDriversToBook);//call booking service

        //TODO send the bookedDriverdriver to customer Service.

        return Response.ok().build();

    }




}
