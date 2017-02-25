package com.right.triangle;

import com.right.triangle.services.PingService;
import com.right.triangle.services.TrackerService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by rpslive on 05/09/15.
 */
public class ServiceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(this.pingService).to(PingService.class);
        bind(this.trackerService).to(TrackerService.class);
    }

    private TrackerService trackerService;
    private PingService pingService;

    public void setTrackerService(TrackerService trackerService) {
        this.trackerService = trackerService;
    }

    public void setPingService(PingService pingService) {
        this.pingService = pingService;
    }
}
