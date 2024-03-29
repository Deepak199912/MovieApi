package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "booking_id")
    private long booking_id;
    
    @Column(name = "booking_name")
    private String booking_name;
    
    @Column(name = "payment_mode")
    private String payment_mode;
    
    @Column(name = "cinema_id")
    private String cinema_id;
    
    @Column(name = "movie_name")
    private String movie_name;

    public long getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(long booking_id) {
        this.booking_id = booking_id;
    }

    public String getBooking_name() {
        return booking_name;
    }

    public void setBooking_name(String booking_name) {
        this.booking_name = booking_name;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(String cinema_id) {
        this.cinema_id = cinema_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public Booking() {
        
    }

    public Booking(long booking_id, String booking_name, String payment_mode, String cinema_id, String movie_name) {
        this.booking_id = booking_id;
        this.booking_name = booking_name;
        this.payment_mode = payment_mode;
        this.cinema_id = cinema_id;
        this.movie_name = movie_name;
    }
}
