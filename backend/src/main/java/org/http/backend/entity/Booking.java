package org.http.backend.entity;

import org.springframework.data.annotation.Id;

public class Booking {

    @Id
    private String id;
    private String userId;
    private Schedule schedule;

    public Booking() {
    }

    public Booking(String id, String userId, Schedule schedule) {
        this.id = id;
        this.userId = userId;
        this.schedule = schedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
