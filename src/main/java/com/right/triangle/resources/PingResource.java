package com.right.triangle.resources;

import com.right.triangle.services.PingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by rpslive on 05/09/15.
 */
@Path("rs/ping")
public class PingResource {

    private PingService pingService;

    @Inject
    public PingResource(PingService pingService){
        this.pingService = pingService;
    }

    @GET
    public String ping() {
        return pingService.ping();
    }
}
