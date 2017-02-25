package com.rocket.science.beans;

import com.google.maps.GeoApiContext;
import com.rocket.science.constants.Constant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shamimh on 25/02/17.
 */

@Configuration
public class GoogleApiContext {


    @Bean
    @Qualifier(value = "geoApiContext")
    public GeoApiContext constructGeoApiContext(){
        GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setApiKey(Constant.GoogleAPIConstant.API_KEY);
        geoApiContext.setMaxRetries(Integer.valueOf(Constant.GoogleAPIConstant.MAX_RETRY));
        return geoApiContext;
    }

    @Bean
    @Qualifier(value = "numberOfTopDriver")
    public Integer getValueOfTopNDriver(){
        return Integer.valueOf(Constant.DriverConstants.NUMBER_OF_N_DRIVERS);
    }

}
