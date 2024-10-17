package org.http.backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Schedule {

    @Id
    private String id;
    private LocalDateTime dateTime;
    private Cinema cinema;

    public Schedule() {
    }

    public Schedule(LocalDateTime dateTime, Cinema cinema) {
        this.dateTime = dateTime;
        this.cinema = cinema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
