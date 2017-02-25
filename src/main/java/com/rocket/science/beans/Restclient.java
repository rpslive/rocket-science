package com.rocket.science.beans;

import com.rocket.science.helper.HttpClientHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shamimh on 26/02/17.
 */

@Configuration
public class Restclient {


    @Bean
    @Qualifier(value = "httpClientHelper")
    public HttpClientHelper gethttpClient(){
        System.out.println("My beans are created");
        return new HttpClientHelper();
    }
}


