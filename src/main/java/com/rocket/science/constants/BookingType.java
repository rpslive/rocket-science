package com.rocket.science.constants;

/**
 * Created by shamimh on 25/02/17.
 */
public enum BookingType {

    PRIME("PRIME"),
    MINI("MINI"),
    SHARE("POOL");


    @Override
    public String toString() {
        return super.toString();
    }


    private  String bookingType;

    BookingType(String bookingType){
        this.bookingType = bookingType;
    }



}
