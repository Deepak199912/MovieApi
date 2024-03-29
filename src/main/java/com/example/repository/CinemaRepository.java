package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

}
