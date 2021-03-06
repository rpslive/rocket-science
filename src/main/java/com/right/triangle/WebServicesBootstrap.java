package com.right.triangle;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

/**
 * Created by rpslive on 05/09/15.
 */
public class WebServicesBootstrap {

    public static final String BASE_URI = "http://localhost:8080";

    public static HttpServer startServer(Settings settings){
        final ResourceConfig rc = new ResourceConfig().packages("com.right.triangle");
        rc.register(settings.getServiceBinder());
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws InterruptedException {
        new WebServicesBootstrap().start(args);
    }

    private void start(String[] args) throws InterruptedException {
        Settings settings = new SettingsFactory().create(args);
        if(settings == null){
            return;
        }
        CountDownLatch stopSignal = addShutdownHook();
        final HttpServer server = startServer(settings);
        //server.getServerConfiguration().addHttpHandler(new StaticHttpHandler("src/main/webapp"), "/");
        System.out.println(String.format("Jersey app started with WADL available at " +
                " %s/application.wadl", BASE_URI));
        stopSignal.await();
        server.stop();
    }

    private CountDownLatch addShutdownHook() {
        final CountDownLatch stopSignal = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("ShutdownHook"){
            @Override
            public void run(){
                stopSignal.countDown();
            }
        });
        return stopSignal;
    }
}
