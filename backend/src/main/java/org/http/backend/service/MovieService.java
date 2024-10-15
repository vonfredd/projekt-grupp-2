package org.http.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.http.backend.entity.Movie;
import org.http.backend.repository.MovieRepository;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ObjectMapper objectMapper;

    public MovieService(MovieRepository movieRepository, ObjectMapper objectMapper) {
        this.movieRepository = movieRepository;
        this.objectMapper = objectMapper;
    }

    public Movie save(String stringMovie) {
    Movie movie = new Movie();
        try {
            JsonNode rootNode = objectMapper.readTree(stringMovie);

            String movieId = rootNode.path("id").asText();
            String name = rootNode.path("title").asText();
            String description = rootNode.path("overview").asText();
            List<String> genres = new ArrayList<>();
            for (JsonNode genreNode : rootNode.path("genres")) {
                genres.add(genreNode.path("name").asText());
            }
            String duration = rootNode.path("runtime").asText(); // Runtime in minutes
            String releaseDate = rootNode.path("release_date").asText();
            String imageUrl = rootNode.path("poster_path").asText();

            movie = new Movie(movieId, name.toLowerCase(), description, genres, duration, releaseDate, imageUrl,new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieRepository.save(movie);
    }

    public Movie findById(String id) {
        Optional <Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new RuntimeException("No such ID " + id);
        }
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findByNameContains(String name) {
        return movieRepository.findByNameContains(name);
    }

    public void delete(String id) {
        movieRepository.deleteById(id);
    }
}
