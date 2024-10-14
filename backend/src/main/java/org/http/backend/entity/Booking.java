package org.http.backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Booking {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Booking(String id, String userId, Schedule schedule) {
        this.id = id;
        this.userId = userId;
        this.schedule = schedule;
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

    @Id
    private String id;
    private String userId;
    private Schedule schedule;
}
