package org.http.backend.entity;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


public class User {

    @Indexed(unique = true)
    private String googleId;
    private String name;
    @Indexed(unique = true)
    private String email;

}
