package com.right.triangle.resources;

import com.right.triangle.model.Location;
import com.right.triangle.model.PositionData;
import com.right.triangle.model.StatusData;
import com.right.triangle.services.TrackerService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.inject.Inject;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;


/**
 * Created by sinraja on 2/25/17.
 */

@Path("/rs/tracker")
public class TrackerResource {


    private TrackerService trackerService;

    @Inject
    public TrackerResource(TrackerService trackerService){
        this.trackerService = trackerService;
    }

    @GET
    @Path("/status/{cabId}/{driverId}")
    public String getStatus(@PathParam("cabId") String cabId, @PathParam("driverId") String driverId){
        return trackerService.getStatus(cabId,driverId);
    }

    @POST
    @Path("/status")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateStatus(@Context UriInfo uriInfo,
                                @Context HttpServletRequest request,
                                StatusData statusData){
        return trackerService.updateStatus(statusData.getCabId(),statusData.getDriverId(),statusData.getStatus());
    }

    @POST
    @Path("/nearby")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PositionData> nearby(@Context UriInfo uriInfo,
                                     @Context HttpServletRequest request,
                                     Location location){
        return trackerService.getNearby(location);
    }

    @POST
    @Path("/position")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean setLocation(@Context UriInfo uriInfo,
                               @Context HttpServletRequest request,
                               PositionData positionData){
        return trackerService.setPosition(positionData);
    }

    @POST
    @Path("/track/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Location trackDriver(@Context UriInfo uriInfo,
                                @Context HttpServletRequest request,
                                StatusData statusData){
        return trackerService.trackDriver(statusData.getCabId(),statusData.getDriverId());
    }


}
