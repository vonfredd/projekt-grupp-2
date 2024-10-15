package org.http.backend.util;


public class CinemaHall {

    private String name;
    private int capacity;

    public CinemaHall() {
    }

    public CinemaHall(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
