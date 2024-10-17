package org.http.backend.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Booking {

    @Id
    private String id;
    private User user;
    private Schedule schedule;
    private List<Integer> bookedSeats;


    public Booking(String id, User user, Schedule schedule, List<Integer> bookedSeats) {
        this.id = id;
        this.user = user;
        this.schedule = schedule;
        this.bookedSeats = bookedSeats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Integer> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}
