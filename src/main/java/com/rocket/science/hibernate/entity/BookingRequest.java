package com.rocket.science.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by shamimh on 25/02/17.
 * Model class for handling Customer Booking request
 */
@Entity
@XmlRootElement
@Table(name="BookingRequest")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookingRequest implements Serializable {


    private Position sourcePosition;
    private Position destinationPosition;
    Date time;
    int id ;

    public BookingRequest(Position sourcePosition, Position destinationPosition, Date time, int id) {
        this.sourcePosition = sourcePosition;
        this.destinationPosition = destinationPosition;
        this.time = time;
        this.id = id;
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public void setSourcePosition(Position sourcePosition) {
        this.sourcePosition = sourcePosition;
    }

    public Position getDestinationPosition() {
        return destinationPosition;
    }

    public void setDestinationPosition(Position destinationPosition) {
        this.destinationPosition = destinationPosition;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
