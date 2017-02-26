package com.rocket.science.externalServices;

import com.rocket.science.constants.Constant;
import com.rocket.science.helper.HttpClientHelper;
import com.rocket.science.hibernate.entity.DriverTracker;
import com.rocket.science.hibernate.entity.DriverTrackerETA;
import com.rocket.science.utils.ExternalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Created by shamimh on 26/02/17.
 */
@Component
public class CustomerManagementService extends ExternalServiceUtil<DriverTrackerETA> {

    @Autowired
    @Qualifier(value = "httpClientHelper")
    HttpClientHelper httpClientHelper;

    public DriverTrackerETA sendConfirmedDriverDetailsToCustomerSystem(List<DriverTrackerETA> topOptimisedDriversToBook){

        Optional<JSONObject> jsonOfOptimsedDrivers= this.constructJSONObjectFromModel(topOptimisedDriversToBook);
        Optional<JSONObject> jsonOfDrivers = httpClientHelper.postRequest(Constant.DriverManagement.DriverManagementURI,jsonOfOptimsedDrivers.get());
        return parseJsonToDriverETA(jsonOfDrivers.get());

    }




}
