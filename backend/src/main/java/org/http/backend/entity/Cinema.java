package org.http.backend.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Cinema {
    private String name;
    private List<CinemaHall> cinemaHalls;

    public Cinema(String name, List<CinemaHall> cinemaHalls) {
        this.name = name;
        this.cinemaHalls = cinemaHalls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CinemaHall> getCinemaHalls() {
        return cinemaHalls;
    }

    public void setShowRooms(List<CinemaHall> cinemaHalls) {
        this.cinemaHalls = cinemaHalls;
    }
}
