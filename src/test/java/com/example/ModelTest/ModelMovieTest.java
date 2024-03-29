package com.example.ModelTest;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.example.model.Movie;

public class ModelMovieTest {

    @Test
    public void testMovieConstructor() {
        Movie movie = new Movie();
        Assert.assertNotNull(movie);
    }

    @Test
    public void testSettersAndGetters() {
        Movie movie = new Movie();
        movie.setMovie_id(101);
        movie.setMovie_name("salaar");
        movie.setMovie_director("prashant neel");

        Assert.assertEquals(movie.getMovie_id(), 101);
        Assert.assertEquals(movie.getMovie_name(), "salaar");
        Assert.assertEquals(movie.getMovie_director(), "prashant neel");
    }
}
