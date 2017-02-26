package com.right.triangle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by sinraja on 2/26/17.
 */
@JsonIgnoreProperties()
public class BookingRequest implements Serializable {
    private static final long serialVersionUID = 210506190747019785L;

    private Location sourcePosition;
    private Location destinationPosition;

    public BookingRequest() {
    }

    public Location getSourcePosition() {
        return sourcePosition;
    }

    public void setSourcePosition(Location sourcePosition) {
        this.sourcePosition = sourcePosition;
    }

    public Location getDestinationPosition() {
        return destinationPosition;
    }

    public void setDestinationPosition(Location destinationPosition) {
        this.destinationPosition = destinationPosition;
    }
}
