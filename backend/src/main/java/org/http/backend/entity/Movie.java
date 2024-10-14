package org.http.backend.entity;

import org.http.backend.util.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "movies")
public class Movie {

    @Id
    private String id;
    private String name;
    private String description;
    private List<String> genre;
    private String duration;
    private String releaseDate;
    private String imageUrl;
    private List<Rating> rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public Movie() {
    }

    public Movie(String id, String name, String description, List<String> genre, String duration, String releaseDate, String imageUrl, List<Rating> rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
        this.rating = rating;
    }
}

