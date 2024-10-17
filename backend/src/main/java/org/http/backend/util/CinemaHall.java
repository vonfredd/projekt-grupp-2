package org.http.backend.util;

import java.util.ArrayList;
import java.util.List;

public class CinemaHall {

    private String name;
    private List<Boolean> seats;

    public CinemaHall() {
    }

    public CinemaHall(String name, List<Boolean> seats) {
        this.name = name;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
