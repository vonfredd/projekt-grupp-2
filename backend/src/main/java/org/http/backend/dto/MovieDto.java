package org.http.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.http.backend.entity.Movie;
import org.http.backend.util.Rating;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieDto(String id, String title, String overview, String runtime, @JsonProperty("release_date") String releaseDate, @JsonProperty("poster_path") String posterPath, List<GenreDto> genres, String backdropPath) {
    public MovieDto {
        if (genres == null) {
            genres = List.of();
        }
    }

    public Movie toEntity(){
        Movie movie = new Movie();
        movie.setId(id);
        movie.setName(title);
        movie.setDescription(overview);
        movie.setDuration(runtime);
        movie.setReleaseDate(releaseDate);
        movie.setImageUrl(posterPath);
        movie.setGenres(genres.stream().map(GenreDto::name).toList());
        List<Rating> ratings = new ArrayList<>();
        movie.setRating(ratings);
        movie.setBackdropPath(backdropPath);
        return movie;
    }
}
