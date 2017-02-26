package com.rocket.science;


import com.rocket.science.customermanager.services.UserService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by rpslive on 05/09/15.
 */
public class ServiceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(this.userService).to(UserService.class);
    }

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
