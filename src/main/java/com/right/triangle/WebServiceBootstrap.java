package com.right.triangle;


import com.right.triangle.resources.PingResource;
import com.right.triangle.services.PingService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Created by rpslive on 05/09/15.
 */
public class WebServiceBootstrap {

    public static final String BASE_URI = "http://localhost:8080";

    public static HttpServer startServer(){
        final ResourceConfig rc = new ResourceConfig().packages("com.right.triangle");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI),rc);
    }

    public static void main(String[] args) throws IOException {
        wireServices();
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at " +
                " %sapplication.wadl\n Hit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }

    public static void wireServices(){
        PingService pingService = new PingService();
        PingResource.setPingService(pingService);
    }


}
