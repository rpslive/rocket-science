package com.right.triangle;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by rpslive on 05/09/15.
 */
public class Settings {

    private ServiceBinder serviceBinder;

    public ServiceBinder getServiceBinder() {
        return serviceBinder;
    }

    public void setServiceBinder(ServiceBinder serviceBinder) {
        this.serviceBinder = serviceBinder;
    }

    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
