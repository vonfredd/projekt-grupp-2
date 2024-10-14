package org.http.backend.util;



import java.util.List;


public class ShowRoom {

    private String name;
    private List<Boolean> seats;

    public ShowRoom() {
    }

    public ShowRoom(String name, List<Boolean> seats) {
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
}
