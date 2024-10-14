package org.http.backend.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Indexed(unique = true)
    private String googleId;
    private String name;
    @Indexed(unique = true)
    private String email;

    public User() {
    }

    public User(String googleId, String name, String email) {
        this.googleId = googleId;
        this.name = name;
        this.email = email;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
