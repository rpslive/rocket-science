package com.rocket.science.resources;

import com.rocket.science.hibernate.entity.Booking;
import com.rocket.science.hibernate.entity.User;
import com.rocket.science.services.BookingService;
import com.rocket.science.services.UserService;
import com.rocket.science.utils.ResourceUtil;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by pawan.gupta on 26/02/17.
 */
@Path("booking")
public class BookingResource extends ResourceUtil<Booking> {

    @Inject
    public BookingResource(BookingService bookingService){
        this.service = bookingService;
    }

    @POST
    @Path("confirmation")
    @Consumes("application/json")
    public boolean addBkg(Booking booking){
        return service.add(booking);
    }

    @POST
    @Path("cancellation")
    @Consumes("application/json")
    public void updateBkg(Booking booking){
        service.update(booking);
    }


}
