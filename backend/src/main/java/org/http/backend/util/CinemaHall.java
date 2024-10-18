package org.http.backend.util;

import java.util.List;

public class CinemaHall {

    private String name;
    private int nrOfSeats;

    public CinemaHall() {
    }

    public CinemaHall(String name, int nrOfSeats) {
        this.name = name;
        this.nrOfSeats = nrOfSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNrOfSeats() {
        return nrOfSeats;
    }

    public void setNrOfSeats() {
        this.nrOfSeats = nrOfSeats;
    }


}
