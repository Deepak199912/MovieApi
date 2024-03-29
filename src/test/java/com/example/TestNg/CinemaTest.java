package com.example.TestNg;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.model.Cinema;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CinemaTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost"; //  base URI
        RestAssured.port = 8080; //  port number
    }

    @Test
    public void testGetAllCinemas() {
        given()
            .when()
                .get("/cinemas")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0)); // Assuming it returns at least one cinema
    }

    @Test
    public void testGetCinemaById() {
        given()
            .pathParam("id", 1) // Assuming cinema with ID 1 exists
        .when()
            .get("/cinemas/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("cinema_id", equalTo(1)); // Assuming cinema with ID 1 is returned
    }

    @Test
    public void testCreateNewCinema() {
        Cinema cinema = new Cinema(5, "Triveni Theatre", "Mejestic");
        given()
            .contentType(ContentType.JSON)
            .body(cinema)
        .when()
            .post("/cinemas")
        .then()
            .statusCode(201)
            .body(equalTo("Cinema created in database"));
    }

//    @Test
//    public void testUpdateCinema() {
//        Cinema cinema = new Cinema(1L, "PVR", "Vijay Nagar");
//
//        given()
//            .contentType(ContentType.JSON)
//            .body(cinema)
//            .pathParam("id", 1) // Assuming cinema with ID 1 exists
//        .when()
//            .put("/cinemas/{id}")
//        .then()
//            .statusCode(200)
//            .body(equalTo("Cinema updated successfully"));
//    }

//    @Test
//    public void testDeleteCinema() {
//        given()
//            .pathParam("id", 5) // Assuming cinema with ID 1 exists
//        .when()
//            .delete("/cinemas/{id}")
//        .then()
//            .statusCode(200)
//            .body(equalTo("Cinema deleted successfully"));
//    }
}
