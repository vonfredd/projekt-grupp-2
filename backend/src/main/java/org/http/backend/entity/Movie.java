package org.http.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.http.backend.util.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;
import java.util.List;

@Document(collection = "movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    private String id;
    @Indexed(unique = true)
    private String imdbId;
    private String name;
    private String description;
    private String genre;
    private int duration;
    private String releaseDate;
    private String imageUrl;
    private List<Rating> rating;
}

