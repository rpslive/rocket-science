package com.rocket.science.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocket.science.hibernate.entity.BookingRequest;
import com.rocket.science.hibernate.entity.Position;

import java.io.IOException;
import java.util.Date;

/**
 * Created by shamimh on 26/02/17.
 */
public class test {
    public static void main(String[] args) throws IOException {
        Position p1 = new Position("333","443");
        Position p2 = new Position("023","839");
        BookingRequest bk = new BookingRequest(p1,p2,new Date(),3);
        ObjectMapper mp = new ObjectMapper();
        mp.writeValue(System.out,bk);
    }
}
