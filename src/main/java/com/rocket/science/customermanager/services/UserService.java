package com.rocket.science.customermanager.services;

import com.rocket.science.helper.HttpClientHelper;
import org.json.JSONObject;

import java.util.Optional;

/**
 * Created by aadithya.hatwar on 26/02/17.
 */
public class UserService {

    String BookingExperienceRequestRide;
    String BookingExperienceTrackRide;

    public Optional<JSONObject> requestRide(JSONObject inputjson){
        return new HttpClientHelper().postRequest(BookingExperienceRequestRide,inputjson);
    }

    public Optional<JSONObject> trackRide(String bookingId){
        return new HttpClientHelper().getRequest(BookingExperienceTrackRide);
    }
}


