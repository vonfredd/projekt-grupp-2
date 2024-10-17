package org.http.backend.entity;

import org.http.backend.util.CinemaHall;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
public class Cinema {

    @Id
    private String name;
    private List<Movie> movies;
    private List<CinemaHall> cinemaHalls;

    public Cinema(String name, List<Movie> movies, List<CinemaHall> cinemaHalls) {
        this.name = name;
        this.movies = movies;
        this.cinemaHalls = cinemaHalls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }


    public List<CinemaHall> getCinemaHalls() {
        return cinemaHalls;
    }

    public void setCinemaHalls(List<CinemaHall> cinemaHalls) {
        this.cinemaHalls = cinemaHalls;
    }
}
