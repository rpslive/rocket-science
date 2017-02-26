package com.rocket.science.resources;



import com.rocket.science.constants.Constant;
import com.rocket.science.externalServices.DriverManagerService;
import com.rocket.science.helper.HttpClientHelper;
import com.rocket.science.hibernate.entity.*;
import com.rocket.science.services.BookingRequestService;
import com.rocket.science.utils.ManagerUtil;
import com.rocket.science.utils.ResourceUtil;
import org.apache.log4j.Logger;
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
@Component
public class BookingRequestResource extends ResourceUtil<BookingRequest>{

    private static final Logger LOGGER = Logger.getLogger(BookingRequestResource.class);

    //private BookingRequestService bookingRequestService;

    @Inject
    public BookingRequestResource(BookingRequestService bookingRequestService){
        this.service = bookingRequestService;
    }

    @POST
    @Path("/bookCab")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response BookCab(BookingRequest entity ){
        System.out.println(entity.toString());
        Map<String,String> responseMap = new HashMap<>();

        // save the data to Database for use age later.
        //TODO : validate agency data
        boolean result = true;//service.add(entity);
        if(result != true){
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        this.service.book(entity);
        //TODO send the bookedDriverdriver to customer Service.

        return Response.ok().build();

    }



    public static Logger getLOGGER() {
        return LOGGER;
    }


    public BookingRequestResource() {
    }
}
