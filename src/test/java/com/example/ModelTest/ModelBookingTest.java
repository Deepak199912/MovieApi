package com.example.ModelTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.model.Booking;

public class ModelBookingTest {

    @Test
    public void testBookingGetterAndSetter() {
        long bookingId = 1001L;
        String bookingName = "Raj";
        String paymentMode = "credit card";
        String cinemaId = "2";
        String movieName = "Salaar";

        Booking booking = new Booking();
        booking.setBooking_id(bookingId);
        booking.setBooking_name(bookingName);
        booking.setPayment_mode(paymentMode);
        booking.setCinema_id(cinemaId);
        booking.setMovie_name(movieName);

        Assert.assertEquals(booking.getBooking_id(), bookingId);
        Assert.assertEquals(booking.getBooking_name(), bookingName);
        Assert.assertEquals(booking.getPayment_mode(), paymentMode);
        Assert.assertEquals(booking.getCinema_id(), cinemaId);
        Assert.assertEquals(booking.getMovie_name(), movieName);
    }
}


