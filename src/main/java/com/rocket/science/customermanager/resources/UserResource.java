package com.rocket.science.customermanager.resources;

import com.rocket.science.customermanager.services.UserService;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Optional;

/**
 * Created by aadithya.hatwar on 26/02/17.
 */
@Path("cms/")
public class UserResource {

    private UserService userService ;

    @Inject
    public UserResource(UserService userService){
        this.userService=userService;
    }


    @POST
    @Path("/requestRide/")
    public Optional<JSONObject> requestRide(JSONObject jsonObject){
        return this.userService.requestRide(jsonObject);
    }

    @GET
    @Path("/trackRide/{bookingId}")
    public Optional<JSONObject> trackRide(@PathParam("bookingId") String bookingId){
        return this.userService.trackRide(bookingId);
    }

}
