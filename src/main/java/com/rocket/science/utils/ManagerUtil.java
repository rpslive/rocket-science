package com.rocket.science.utils;

import com.google.gson.Gson;
import com.rocket.science.hibernate.entity.BookingRequest;
import com.rocket.science.hibernate.entity.DriverTracker;
import com.rocket.science.manager.BookingRequestUtil;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;

/**
 * Created by shamimh on 25/02/17.
 */
public abstract class ManagerUtil<T,U,V> {

    private static final Logger LOGGER = Logger.getLogger(ManagerUtil.class);

    /**
     * contains implementation for selecting top n drivers based on business Logic
     * @param entity
     * @param driverTrackers
     * @return
     * @throws Exception
     */
    public abstract List<U> process(T entity  , List<V> driverTrackers) throws Exception;


    public  Optional<JSONObject> constructJSONObjectFromModel(T entity){
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

    public abstract List<V> parseJsonToDrivers(JSONObject jsonOfDrivers);


}
