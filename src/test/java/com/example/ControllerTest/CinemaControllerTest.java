package com.example.ControllerTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.controller.CinemaController;
import com.example.model.Cinema;
import com.example.repository.CinemaRepository;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class CinemaControllerTest {

    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private CinemaController cinemaController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCinemas() {
        // Mocking data
        List<Cinema> mockCinemas = new ArrayList<>();
        mockCinemas.add(new Cinema(1L, "PVR", "Vijay Nagar"));
        mockCinemas.add(new Cinema(2L, "IMAX", "Hebbal"));
        mockCinemas.add(new Cinema(3L, "4DX", "Yelahanka"));
        mockCinemas.add(new Cinema(4L, "INOX", "Kormangala"));
        mockCinemas.add(new Cinema(5L, "Navrang theatre", "Mejestic"));

        // Stubbing the behavior of cinemaRepository.findAll() to return mockCinemas
        when(cinemaRepository.findAll()).thenReturn(mockCinemas);

        // Calling the controller method
        ResponseEntity<List<Cinema>> responseEntity = cinemaController.getAllCinemas();

        // Verifying the response
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        List<Cinema> retrievedCinemas = responseEntity.getBody();
        Assert.assertEquals(retrievedCinemas.size(), 5);
        Assert.assertEquals(retrievedCinemas.get(0).getCinema_name(), "PVR");
        Assert.assertEquals(retrievedCinemas.get(1).getCinema_name(), "IMAX");
        Assert.assertEquals(retrievedCinemas.get(2).getCinema_name(), "4DX");
        Assert.assertEquals(retrievedCinemas.get(3).getCinema_name(), "INOX");
        Assert.assertEquals(retrievedCinemas.get(4).getCinema_name(), "Navrang theatre");

        // Verifying the interaction with cinemaRepository
        verify(cinemaRepository, times(1)).findAll();
    }
}



