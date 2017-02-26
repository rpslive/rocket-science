package com.rocket.science.services;

import com.rocket.science.constants.BookingType;

import com.rocket.science.constants.Constant;
import com.rocket.science.externalServices.DriverManagerService;
import com.rocket.science.helper.HttpClientHelper;
import com.rocket.science.hibernate.entity.BookingRequest;
import com.rocket.science.hibernate.entity.DriverTracker;
import com.rocket.science.hibernate.entity.DriverTrackerETA;
import com.rocket.science.utils.ManagerUtil;
import com.rocket.science.utils.ServiceUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by shamimh on 25/02/17.
 */
@Service
public class BookingRequestService extends ServiceUtil<BookingRequest> {

    @Autowired
    @Qualifier(value = "bookingRequestUtil")
    private ManagerUtil<BookingRequest,DriverTrackerETA,DriverTracker> bookingRequestUtil;

/*    @Inject
    public BookingRequestResource(BookingRequestService bookingRequestService){
        this.service = bookingRequestService;
    }*/

    @Autowired
    @Qualifier(value = "httpClientHelper")
    HttpClientHelper httpClientHelper;

    @Autowired
    private DriverManagerService driverManagerService;

    public Optional<JSONArray> postRequest(String url, JSONObject jsonobject) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(jsonobject.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = client.execute(request);
            //return Optional.of(new JSONObject(response.getEntity().getContent().toString()));
            String json_string = EntityUtils.toString(response.getEntity());
            JSONArray temp1 = new JSONArray(json_string);
            return Optional.of(temp1);

        } catch (Exception var10) {
            return Optional.empty();
        }
    }

    public void book(BookingRequest entity) {
        Optional<JSONObject> jsonOfCustomerLocation = bookingRequestUtil.constructJSONObjectFromModel(entity);

        //call the Tracker service using post with driver location to get list of nearby drivers
        Optional<JSONArray> jsonOfDrivers = postRequest(Constant.TrackingService.TrackingServiceURI,jsonOfCustomerLocation.get());
        if(!jsonOfDrivers.isPresent()){
            Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        List<DriverTracker> driverTracker = bookingRequestUtil.parseJsonToDrivers(jsonOfDrivers.get());

        List<DriverTrackerETA> topOptimisedDriversToBook = new ArrayList<>();
        try {
            // get the top Optimised driver to call Booking Service
            topOptimisedDriversToBook.addAll(bookingRequestUtil.process(entity,driverTracker));
        } catch (Exception e) {
            //LOGGER.error("exception from google map Service"+e.getMessage());
        }

        DriverTrackerETA bookedDriverdriver = driverManagerService.getResponseFromDriverMangementService(topOptimisedDriversToBook);//call booking service

    }

    public ManagerUtil<BookingRequest, DriverTrackerETA, DriverTracker> getBookingRequestUtil() {
        return bookingRequestUtil;
    }

    public void setBookingRequestUtil(ManagerUtil<BookingRequest, DriverTrackerETA, DriverTracker> bookingRequestUtil) {
        this.bookingRequestUtil = bookingRequestUtil;
    }

    public HttpClientHelper getHttpClientHelper() {
        return httpClientHelper;
    }

    public void setHttpClientHelper(HttpClientHelper httpClientHelper) {
        this.httpClientHelper = httpClientHelper;
    }

    public DriverManagerService getDriverManagerService() {
        return driverManagerService;
    }

    public void setDriverManagerService(DriverManagerService driverManagerService) {
        this.driverManagerService = driverManagerService;
    }
}
