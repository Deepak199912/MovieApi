package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Cinema;
import com.example.repository.CinemaRepository;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    CinemaRepository cinemaRepository;

    // GET all cinemas
    @GetMapping
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        List<Cinema> cList = new ArrayList<>();
        cinemaRepository.findAll().forEach(cList::add);
        return new ResponseEntity<>(cList, HttpStatus.OK);
    }

    // GET cinema by ID
    @GetMapping("/{id}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable("id") Long id) {
        Optional<Cinema> cinema = cinemaRepository.findById(id);
        return cinema.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST create new cinema
    @PostMapping
    public ResponseEntity<String> createNewCinema(@RequestBody Cinema cinema) {
        cinemaRepository.save(cinema);
        return new ResponseEntity<>("Cinema created in database", HttpStatus.CREATED);
    }

    // PUT update cinema
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCinema(@PathVariable("id") Long id, @RequestBody Cinema cinema) {
        Optional<Cinema> cinemaData = cinemaRepository.findById(id);
        if (cinemaData.isPresent()) {
            Cinema _cinema = cinemaData.get();
            _cinema.setCinema_name(cinema.getCinema_name());
            _cinema.setCinema_location(cinema.getCinema_location());
            cinemaRepository.save(_cinema);
            return new ResponseEntity<>("Cinema updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cinema not found", HttpStatus.NOT_FOUND);
        }
    }

    // DELETE cinema
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCinema(@PathVariable("id") Long id) {
        try {
            cinemaRepository.deleteById(id);
            return new ResponseEntity<>("Cinema deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete cinema", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
