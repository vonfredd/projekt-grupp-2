package org.http.backend.util;

public class Rating {

    private String userId;
    private int movieRating;

    public Rating() {
    }

    public Rating(String userId, int movieRating) {
        this.userId = userId;
        this.movieRating = movieRating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }
}
