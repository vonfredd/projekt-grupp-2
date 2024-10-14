package org.http.backend.entity;



import java.util.Date;


public class Schedule {
    private String id;
    private Date date;
    private String time;

    public String getId() {
        return id;
    }

    public Schedule(String id, Date date, String time, Cinema cinema) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.cinema = cinema;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    private Cinema cinema;
}
