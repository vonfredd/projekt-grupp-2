package org.http.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.http.backend.dto.MovieDto;
import org.http.backend.dto.MovieGenreDto;
import org.http.backend.entity.Movie;
import org.http.backend.map.MappingService;
import org.http.backend.repository.MovieRepository;
import org.http.backend.util.Rating;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository, ObjectMapper objectMapper) {
        this.movieRepository = movieRepository;
    }

    public Movie save(MovieDto movieDto) throws IOException {
        Movie movie = new MappingService().mapToMovie(movieDto);
        return movieRepository.save(movie);
    }

    public Movie findById(String id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such ID " + id));
    }

    public List<Movie> findAll() {
        try {
            List<Movie> result = movieRepository.findAll();
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

}
