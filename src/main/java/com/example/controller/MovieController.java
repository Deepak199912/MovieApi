package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Movie;
import com.example.repository.MovieRepository;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    // Get all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> mList = new ArrayList<>();
        movieRepository.findAll().forEach(mList::add);
        return new ResponseEntity<>(mList, HttpStatus.OK);
    }

    // Get a movie by ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new movie
    @PostMapping
    public ResponseEntity<String> createNewMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return new ResponseEntity<>("Movie created successfully", HttpStatus.CREATED);
    }

    // Update a movie
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable long id, @RequestBody Movie movieDetails) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setMovie_name(movieDetails.getMovie_name());
            movie.setMovie_director(movieDetails.getMovie_director());
            movieRepository.save(movie);
            return new ResponseEntity<>("Movie updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }

    // Delete a movie
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return new ResponseEntity<>("Movie deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }
}
