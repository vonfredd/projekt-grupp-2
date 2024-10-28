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
    private List<String> genres;
    private String duration;
    private String releaseDate;
    private String imageUrl;
    private List<Rating> ratings;
    private String backdropPath;

    public Movie(String id, String name, String description, List<String> genre, String duration, String releaseDate, String imageUrl, List<Rating> ratings, String backdropPath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genres = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
        this.ratings = ratings;
        this.backdropPath = backdropPath;
    }

    public Movie() {
    }

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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
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
        return ratings;
    }

    public void setRating(List<Rating> rating) {
        this.ratings = rating;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }
    public void addOrUpdateRating(Rating newRating) {
        // Check if this user has already rated
        for (Rating rating : ratings) {
            if (rating.getUserId().equals(newRating.getUserId())) {
                // Update the existing rating
                rating.setMovieRating(newRating.getMovieRating());
                return;
            }
        }
        // If the user hasn't rated yet, add the new rating
        ratings.add(newRating);
    }

    public int getAverageRatingInPercentage() {
        if (ratings == null || ratings.isEmpty()) {
            return 0;  // Return 0 if there are no ratings
        }

        double sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getMovieRating();
        }

        double averageRating = sum / ratings.size();  // Calculate the average
        return (int) Math.round((averageRating / 5.0) * 100); // Convert to percentage assuming max rating is 5
    }
}

