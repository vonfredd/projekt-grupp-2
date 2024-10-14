package org.http.backend.entity;



import java.util.List;


public class MovieHall {

    private String name;

    public MovieHall(String name, List<Boolean> seats) {
        this.name = name;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Boolean> getSeats() {
        return seats;
    }

    public void setSeats(List<Boolean> seats) {
        this.seats = seats;
    }

    private List<Boolean> seats;
}
