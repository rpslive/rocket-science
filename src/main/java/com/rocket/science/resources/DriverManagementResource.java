package com.rocket.science.resources;

import com.rocket.science.hibernate.entity.Driver;
import com.rocket.science.services.DriverManagementService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by pawan.gupta on 25/02/17.
 */

@Path("dms/Driver")
public class DriverManagementResource{
    private DriverManagementService driverManagementService;

    @Inject
    public DriverManagementResource(DriverManagementService driverManagementService){
        this.driverManagementService = driverManagementService;
    }


    @POST
    @Path("/Locking")
    @Consumes("application/json")
    public Driver bookDriver(List<Driver> drivers) {
        return driverManagementService.bookDriver(drivers);
    }

    @POST
    @Path("/Cancellation")
    @Consumes("application/json")
    public String cancelTrip(Driver driver) {
        return String.valueOf(driverManagementService.cancelTrip(driver));
    }

    @GET
    @Path("/Tracking")
    @Consumes("application/json")
    public String trackDriver(Driver driver) {
        return driverManagementService.trackDriver(driver);
    }

}
