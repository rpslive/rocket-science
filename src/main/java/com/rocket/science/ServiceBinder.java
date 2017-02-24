package com.rocket.science;

import com.rocket.science.services.PingService;
import com.rocket.science.services.UserService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by rpslive on 05/09/15.
 */
public class ServiceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(this.pingService).to(PingService.class);
        bind(this.userService).to(UserService.class);
    }

    private PingService pingService;

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setPingService(PingService pingService) {
        this.pingService = pingService;
    }
}
