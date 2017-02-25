package com.rocket.science;

import com.rocket.science.services.BookingRequestService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by rpslive on 05/09/15.
 */

public class ServiceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(this.bookingRequestService).to(BookingRequestService.class);
    }


    private BookingRequestService bookingRequestService;


    public BookingRequestService getBookingRequestService() {
        return bookingRequestService;
    }

    public void setBookingRequestService(BookingRequestService bookingRequestService) {
        this.bookingRequestService = bookingRequestService;
    }
}
