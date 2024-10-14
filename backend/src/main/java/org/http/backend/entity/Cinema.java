package org.http.backend.entity;

import org.http.backend.util.ShowRoom;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Cinema {

    private String name;
    private List<Movie> movies;
    private List<ShowRoom> showRooms;

    public Cinema(String name, List<Movie> movies, List<ShowRoom> showRooms) {
        this.name = name;
        this.movies = movies;
        this.showRooms = showRooms;
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

    public List<ShowRoom> getShowRooms() {
        return showRooms;
    }

    public void setShowRooms(List<ShowRoom> showRooms) {
        this.showRooms = showRooms;
    }
}
