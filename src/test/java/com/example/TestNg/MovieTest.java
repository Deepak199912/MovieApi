package com.example.TestNg;


import com.example.model.Movie;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MovieTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost"; // base URI
        RestAssured.port = 8080; // port number
    }

    @Test(priority = 1)
    public void testGetAllMovies() {
        given()
            .when()
                .get("/movies")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0)); // Assuming it returns at least one movie
    }

    @Test(priority = 2)
    public void testGetMovieById() {
        given()
            .pathParam("id", 101) // Assuming movie with ID 101 exists
        .when()
            .get("/movies/{id}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("movie_id", equalTo(101)); // Assuming movie with ID 101 is returned
    }

    @Test(priority = 3)
    public void testCreateNewMovie() {
        Movie movie = new Movie(109, "Jakie", "edhuret");

        given()
            .contentType(ContentType.JSON)
            .body(movie)
        .when()
            .post("/movies")
        .then()
            .statusCode(201)
            .body(equalTo("Movie created successfully"));
    }

    @Test(priority = 4)
    public void testUpdateMovie() {
        Movie movie = new Movie(103, "Avatar-2", "James Cameron");

        given()
            .contentType(ContentType.JSON)
            .body(movie)
            .pathParam("id", 103) // Assuming movie with ID 103 exists
        .when()
            .put("/movies/{id}")
        .then()
            .statusCode(200)
            .body(equalTo("Movie updated successfully"));
    }

    @Test(priority = 5)
    public void testDeleteMovie() {
        given()
            .pathParam("id", 106) // Assuming movie with ID 106 exists
        .when()
            .delete("/movies/{id}")
        .then()
            .statusCode(204)
            .body(emptyOrNullString());
    }
}


