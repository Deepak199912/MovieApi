package com.example.ControllerTest;

import com.example.controller.MovieController;
import com.example.model.Movie;
import com.example.repository.MovieRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class MovieControllerTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieController movieController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMovies() {
        // Mocking data
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(101L, "salaar", "prashant neel"));
        movies.add(new Movie(102L, "KGF-2", "neel prashant"));
        movies.add(new Movie(103L, "Avatar", "james cameron"));
        movies.add(new Movie(104L, "katera", "Tharun Sudhir"));

        when(movieRepository.findAll()).thenReturn(movies);

        ResponseEntity<List<Movie>> response = movieController.getAllMovies();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), movies);
    }

    @Test
    public void testGetMovieById() {
        // Mocking data
        Movie movie = new Movie(101L, "salaar", "prashant neel");
        Optional<Movie> optionalMovie = Optional.of(movie);

        when(movieRepository.findById(101L)).thenReturn(optionalMovie);

        ResponseEntity<Movie> response = movieController.getMovieById(101L);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), movie);
    }

    @Test
    public void testGetMovieByIdNotFound() {
        when(movieRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<Movie> response = movieController.getMovieById(999L);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
