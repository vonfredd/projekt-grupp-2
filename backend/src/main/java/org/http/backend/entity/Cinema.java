package org.http.backend.entity;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Cinema {

    private String name;
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<MovieHall> getMovieHalls() {
        return movieHalls;
    }

    public void setMovieHalls(List<MovieHall> movieHalls) {
        this.movieHalls = movieHalls;
    }

    private List<MovieHall> movieHalls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
