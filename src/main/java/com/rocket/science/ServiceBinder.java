package com.rocket.science;

import com.rocket.science.services.DriverManagementService;
import com.rocket.science.services.PingService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by rpslive on 05/09/15.
 */
public class ServiceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(this.pingService).to(PingService.class);
        bind(this.driverManagementService).to(DriverManagementService.class);
    }

    private PingService pingService;

    private DriverManagementService driverManagementService;

    public void setPingService(PingService pingService) {
        this.pingService = pingService;
    }

    public void setDriverManagementService(DriverManagementService driverManagementService) {
        this.driverManagementService = driverManagementService;
    }

    public DriverManagementService getDriverManagementService() {
        return driverManagementService;
    }
}
