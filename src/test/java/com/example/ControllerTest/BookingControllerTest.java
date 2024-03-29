package com.example.ControllerTest;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.controller.BookingController;
import com.example.model.Booking;
import com.example.repository.BookingRepository;

public class BookingControllerTest {

    @Mock
    private BookingRepository bookingRepositoryMock;

    @InjectMocks
    private BookingController bookingController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBookings() {
        // Creating sample bookings
        Booking booking1 = new Booking(1001, "Raj", "credit card", "2", "Salaar");
        Booking booking2 = new Booking(1002, "Deepak", "UPI", "1", "KGF-2");
        Booking booking3 = new Booking(1003, "Ram", "debit card", "3", "Avatar");
        Booking booking4 = new Booking(1004, "Kumar", "paytm wallet", "4", "Katera");
        Booking booking5 = new Booking(1005, "Ram", "UPI", "2", "Salaar");
        Booking booking6 = new Booking(1006, "Rafg", "UPI", "3", "Salaar");

        // Mock repository response
        when(bookingRepositoryMock.findAll()).thenReturn(Arrays.asList(booking1, booking2, booking3, booking4, booking5, booking6));

        // Call controller method
        ResponseEntity<List<Booking>> responseEntity = bookingController.getAllBookings();

        // Verify response
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        List<Booking> bookings = responseEntity.getBody();
        Assert.assertEquals(bookings.size(), 6); // Assuming there are 6 bookings in the list
        Assert.assertTrue(bookings.contains(booking1));
        Assert.assertTrue(bookings.contains(booking2));
        Assert.assertTrue(bookings.contains(booking3));
        Assert.assertTrue(bookings.contains(booking4));
        Assert.assertTrue(bookings.contains(booking5));
        Assert.assertTrue(bookings.contains(booking6));
    }
    @Test
    public void testCreateNewBooking_Successful() {
        Booking newBooking = new Booking(1008, "John", "credit card", "1", "KGF-2");

        when(bookingRepositoryMock.existsById(newBooking.getBooking_id())).thenReturn(false);

        ResponseEntity<String> responseEntity = bookingController.createNewBooking(newBooking);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        Assert.assertEquals(responseEntity.getBody(), "Booking created successfully!");
    }

    @Test
    public void testCreateNewBooking_DuplicateId() {
        Booking existingBooking = new Booking(1001, "Raj", "credit card", "2", "Salaar");

        when(bookingRepositoryMock.existsById(existingBooking.getBooking_id())).thenReturn(true);

        ResponseEntity<String> responseEntity = bookingController.createNewBooking(existingBooking);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
        Assert.assertEquals(responseEntity.getBody(), "Duplicate booking ID. Booking not allowed.");
    }
}


