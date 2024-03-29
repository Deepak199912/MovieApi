package com.example.ModelTest;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.example.model.Cinema;

public class ModelCinemaTest {

    @Test
    public void testCinemaConstructor() {
        Cinema cinema = new Cinema();
        Assert.assertNotNull(cinema);
    }

    @Test
    public void testParameterizedConstructor() {
        Long cinemaId = 1L;
        String cinemaName = "Example Cinema";
        String cinemaLocation = "Example Location";

        Cinema cinema = new Cinema(cinemaId, cinemaName, cinemaLocation);

        Assert.assertEquals(cinema.getCinema_id(), cinemaId);
        Assert.assertEquals(cinema.getCinema_name(), cinemaName);
        Assert.assertEquals(cinema.getCinema_location(), cinemaLocation);
    }

    @Test
    public void testSettersAndGetters() {
        Cinema cinema = new Cinema();
        Long cinemaId = 2L;
        String cinemaName = "Another Cinema";
        String cinemaLocation = "Another Location";

        cinema.setCinema_id(cinemaId);
        cinema.setCinema_name(cinemaName);
        cinema.setCinema_location(cinemaLocation);

        Assert.assertEquals(cinema.getCinema_id(), cinemaId);
        Assert.assertEquals(cinema.getCinema_name(), cinemaName);
        Assert.assertEquals(cinema.getCinema_location(), cinemaLocation);
    }
}
