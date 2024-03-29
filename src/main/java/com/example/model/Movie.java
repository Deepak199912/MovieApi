package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

    @Id	
    @Column(name = "movie_id")
    private long movie_id;
    
    @Column(name = "movie_name")
    private String movie_name;
    
    @Column(name = "movie_director")
    private String movie_director;

    public long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(long movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public Movie() {
        
    }

    public Movie(long movie_id, String movie_name, String movie_director) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_director = movie_director;
    }   
}
