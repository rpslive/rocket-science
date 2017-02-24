package com.rocket.science.resources;

import com.rocket.science.hibernate.entity.User;
import com.rocket.science.services.UserService;
import com.rocket.science.utils.ResourceUtil;

import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * Created by sinraja on 2/24/17.
 */
@Path("user")
public class UserResource extends ResourceUtil<User> {

    @Inject
    public UserResource(UserService userService){
        this.service = userService;
    }
}
