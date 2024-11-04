package org.http.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.http.backend.dto.MovieDto;
import org.http.backend.entity.Movie;
import org.http.backend.repository.MovieRepository;
import org.http.backend.util.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
    private final ObjectMapper objectMapper;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(ObjectMapper objectMapper, MovieRepository movieRepository) {
        this.objectMapper = objectMapper;
        this.movieRepository = movieRepository;
    }

    public Movie save(String stringMovie) throws JsonProcessingException {
        try {
            MovieDto movieDto = objectMapper.readValue(stringMovie, MovieDto.class);
            return movieRepository.save(movieDto.toEntity());
        } catch (JsonProcessingException e) {
            logger.error("Something went wrong when processing json data" + e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("Something went wrong saving to database" + e.getMessage());
            throw e;
        }
    }

    public Map<String, Object> findById(String id) {
        var movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("No such ID " + id));
        Map<String, Object> movieData = new HashMap<>();
        movieData.put("id", movie.getId());
        movieData.put("name", movie.getName());
        movieData.put("description", movie.getDescription());
        movieData.put("genres", movie.getGenres());
        movieData.put("duration", movie.getDuration());
        movieData.put("releaseDate", movie.getReleaseDate());
        movieData.put("imageUrl", movie.getImageUrl());
        movieData.put("backdropPath", movie.getBackdropPath());
        movieData.put("averageRating", movie.getAverageRatingInPercentage());
        movieData.put("ratings", movie.getRatings());
        return movieData;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }


    public List<Movie> findByNameContainsIgnoreCase(String name) {
        return movieRepository.findByNameContainsIgnoreCase(name);
    }

    public void delete(String id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("No such ID " + id);
        }
        movieRepository.deleteById(id);
    }

    public Movie addRating(String movieId, Rating rating) {
        if (rating.getMovieRating() < 0 || rating.getMovieRating() > 5) {
            throw new RuntimeException("Rating must be between 0 and 5");
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("No such ID " + movieId));

        movie.addOrUpdateRating(rating);

        return movieRepository.save(movie);
    }

    public List<Map<String, Object>> findAllWithRatingsConverted() {
        var movies = movieRepository.findAll();
        List<Map<String, Object>> response = new ArrayList<>();

        // Loop through each movie and build a custom JSON response
        for (Movie movie : movies) {
            Map<String, Object> movieData = new HashMap<>();
            movieData.put("id", movie.getId());
            movieData.put("name", movie.getName());
            movieData.put("description", movie.getDescription());
            movieData.put("genres", movie.getGenres());
            movieData.put("duration", movie.getDuration());
            movieData.put("releaseDate", movie.getReleaseDate());
            movieData.put("imageUrl", movie.getImageUrl());
            movieData.put("backdropPath", movie.getBackdropPath());
            movieData.put("averageRating", movie.getAverageRatingInPercentage());

            response.add(movieData);
        }

        return response;
    }

    public List<Rating> getRatings(String movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("No such ID " + movieId));
        return movie.getRatings();
    }
}
