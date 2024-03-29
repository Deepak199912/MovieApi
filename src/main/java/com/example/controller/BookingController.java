package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Booking;
import com.example.repository.BookingRepository;

@RestController
@RequestMapping("/booking")
public class BookingController {
    
    @Autowired
    BookingRepository bookingRepository;
    
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings(){
        List<Booking> bList = new ArrayList<>();
        bookingRepository.findAll().forEach(bList::add);
        return new ResponseEntity<List<Booking>>(bList, HttpStatus.OK);
    }  

    @PostMapping
    public ResponseEntity<String> createNewBooking(@RequestBody Booking booking) {
        try {
            if (isDuplicateBookingId(booking.getBooking_id())) {
                return new ResponseEntity<>("Duplicate booking ID. Booking not allowed.", HttpStatus.CONFLICT);
            }
            bookingRepository.save(booking);
            return new ResponseEntity<>("Booking created successfully!", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Failed to create booking. Please check your request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            return new ResponseEntity<>(booking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable("id") long id, @RequestBody Booking booking) {
        Optional<Booking> bookingData = bookingRepository.findById(id);

        if (bookingData.isPresent()) {
            Booking _booking = bookingData.get();
            _booking.setBooking_name(booking.getBooking_name());
            _booking.setPayment_mode(booking.getPayment_mode());
            _booking.setCinema_id(booking.getCinema_id());
            _booking.setMovie_name(booking.getMovie_name());
            bookingRepository.save(_booking);
            return new ResponseEntity<>("Booking updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") long id) {
        try {
            bookingRepository.deleteById(id);
            return new ResponseEntity<>("Booking deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete booking.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private boolean isDuplicateBookingId(long bookingId) {
        return bookingRepository.existsById(bookingId);
    }
}
