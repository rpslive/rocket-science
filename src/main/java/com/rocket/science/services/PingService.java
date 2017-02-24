package com.rocket.science.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by rpslive on 05/09/15.
 */
public class PingService {

    public String ping() {
        try {
            return String.format("Ping reply from %s at %tc.",
                    InetAddress.getLocalHost().getCanonicalHostName(),
                    new Date());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
