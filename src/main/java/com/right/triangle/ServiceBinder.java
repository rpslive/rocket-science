package com.right.triangle;

import com.right.triangle.services.PingService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by rpslive on 05/09/15.
 */
public class ServiceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(this.pingService).to(PingService.class);
    }

    private PingService pingService;

    public void setPingService(PingService pingService) {
        this.pingService = pingService;
    }
}
