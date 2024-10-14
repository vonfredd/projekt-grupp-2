package org.http.backend.entity;


import org.http.backend.util.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document(collection = "movies")
public class Movie {

    @Id
    private String id;

    public Movie() {
    }

    public Movie(String id, String imdbId, String name, String description, String genre, int duration, String releaseDate, String imageUrl, List<Rating> rating) {
        this.id = id;
        this.imdbId = imdbId;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
        this.rating = rating;
    }



    @Indexed(unique = true)
    private String imdbId;
    private String name;
    private String description;
    private String genre;
    private int duration;
    private String releaseDate;
    private String imageUrl;
    private List<Rating> rating;

    // Getters
    public String getImdbId() { return imdbId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }
    public String getReleaseDate() { return releaseDate; }
    public String getImageUrl() { return imageUrl; }

    // Setters
    public void setImdbId(String imdbId) { this.imdbId = imdbId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}

