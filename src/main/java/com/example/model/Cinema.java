package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cinema")
public class Cinema {
    @Id
    @Column(name = "cinema_id")
    private Long cinema_id;

    @Column(name = "cinema_name")
    private String cinema_name;

    @Column(name = "cinema_location")
    private String cinema_location;

    // Default constructor
    public Cinema() {
        
    }

    // Parameterized constructor
    public Cinema(long cinema_id, String cinema_name, String cinema_location) {
        this.cinema_id = cinema_id;
        this.cinema_name = cinema_name;
        this.cinema_location = cinema_location;
    }

    // Getters and setters
    public Long getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(Long cinema_id) {
        this.cinema_id = cinema_id;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public String getCinema_location() {
        return cinema_location;
    }

    public void setCinema_location(String cinema_location) {
        this.cinema_location = cinema_location;
    }
}

