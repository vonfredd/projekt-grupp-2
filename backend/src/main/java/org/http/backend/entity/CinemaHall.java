package org.http.backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class CinemaHall {
    @Id
    private int id;
    private String name;
    private int nrOfSeats;

    public CinemaHall(String name, int nrOfSeats) {
        this.name = name;
        this.nrOfSeats = nrOfSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setNrOfSeats(int nrOfSeats) {
        this.nrOfSeats = nrOfSeats;
    }

}
