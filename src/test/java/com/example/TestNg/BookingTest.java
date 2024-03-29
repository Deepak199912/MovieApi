package com.example.TestNg;

import com.example.model.Booking;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingTest {               

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test(priority = 1)
    public void testGetAllBookings() {
        given()
            .when()
                .get("/booking")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0));
    }

    @Test(priority = 2)
    public void testCreateNewBooking() {
        Booking booking = new Booking(1003, "Raju", "Credit Card", "4", "Katera");

        given()
            .contentType(ContentType.JSON)
            .body(booking)
        .when()
            .post("/booking")
        .then()
            .statusCode(201)
            .body(equalTo("Booking created successfully!"));
    }

    @Test(priority = 3)
    public void testGetBookingById() {
        given()
            .pathParam("id", 1001)
        .when()
            .get("/booking/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("booking_id", equalTo(1001));
    }

    @Test(priority = 4)
    public void testUpdateBooking() {
        Booking booking = new Booking(1004, "Kumara", "Credit Card", "5", "Avatar No Way");

        given()
            .contentType(ContentType.JSON)
            .body(booking)
            .pathParam("id", 1004)
        .when()
            .put("/booking/{id}")
        .then()
            .statusCode(200)
            .body(equalTo("Booking updated successfully!"));
    }

    @Test(priority = 5)
    public void testDeleteBooking() {
        given()
            .pathParam("id", 1009)
        .when()
            .delete("/booking/{id}")
        .then()
            .statusCode(200)
            .body(equalTo("Booking deleted successfully!"));
    }
}
