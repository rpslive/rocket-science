package com.rocket.science.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by shamimh on 26/02/17.
 */
public class BookingTest {
    private Position sourcePosition;
    private Position destinationPosition;
    Date time;
    int id ;

    public BookingTest(Position sourcePosition, Position destinationPosition, Date time, int id) {
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookingTest{" +
                "sourcePosition=" + sourcePosition +
                ", destinationPosition=" + destinationPosition +
                ", time=" + time +
                ", id=" + id +
                '}';
    }

    public BookingTest() {
    }
}
