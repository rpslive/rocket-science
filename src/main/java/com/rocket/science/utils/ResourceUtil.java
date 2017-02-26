package com.rocket.science.utils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sinraja on 2/24/17.
 */
public class ResourceUtil<T> {

    public ServiceUtil<T> service;

    public ServiceUtil<T> getService() {
        return service;
    }

    public void setService(ServiceUtil<T> service) {
        this.service = service;
    }

    @POST
    @Path("/addition")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add( T entity ){
        Map<String,String> responseMap = new HashMap<>();
        //TODO : validate agency data
        boolean result = service.add(entity);
        if(result == true){
            responseMap.put("Success",entity.getClass().getCanonicalName()+" added Successfully");
        }else{
            responseMap.put("Failure",entity.getClass().getCanonicalName()+" addition failed");
        }
        return Response.ok(responseMap).build();
    }

    @POST
    @Path("/updation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update( T entity ){
        Map<String,T> responseMap = new HashMap<>();
        //TODO : validate agency data
        T result = service.update(entity);
        if(result != null){
            responseMap.put("Success",result);
        }else{
            responseMap.put("Failure",null);
        }
        return Response.ok(responseMap).build();
    }

    @POST
    @Path("/deletion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete( T entity ){
        Map<String,String> responseMap = new HashMap<>();
        //TODO : validate agency data
        boolean result = service.delete(entity);
        if(result == true){
            responseMap.put("Success",entity.getClass().getCanonicalName()+" updated Successfully");
        }else{
            responseMap.put("Failure",entity.getClass().getCanonicalName()+" updation failed");
        }
        return Response.ok(responseMap).build();
    }

    @POST
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find( T entity ){
        Map<String,T> responseMap = new HashMap<>();
        //TODO : validate agency data
        T result = service.get(entity);
        if(result != null){
            responseMap.put("Success",result);
        }else{
            responseMap.put("Failure",null);
        }
        return Response.ok(responseMap).build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@Context UriInfo uriInfo,
                         @Context HttpServletRequest request){
        Map<String,List<T>> responseMap = new HashMap<>();
        List<T> result = service.getAll();
        if(result != null){
            responseMap.put("Success",result);
        }else{
            responseMap.put("Failure",result);
        }
        return Response.ok(responseMap).build();
    }


}
