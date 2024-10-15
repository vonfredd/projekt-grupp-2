package org.http.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.http.backend.dto.GenreDto;
import org.http.backend.dto.MovieDto;
import org.http.backend.entity.Movie;
import org.http.backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MovieService {

    private final ObjectMapper objectMapper;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(ObjectMapper objectMapper, MovieRepository movieRepository) {
        this.objectMapper = objectMapper;
        this.movieRepository = movieRepository;
    }

    public Movie save(String stringMovie) {
        MovieDto movieDto;

        try {
            movieDto = objectMapper.readValue(stringMovie, MovieDto.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse movie data", e);
        }
        Movie movie = new Movie();
        movie.setId(movieDto.id());
        movie.setName(movieDto.title());
        movie.setDescription(movieDto.overview());

        List<String> genreNames = (movieDto.genres() != null ?
                movieDto.genres().stream().map(GenreDto::name).toList() :
                List.of());

        movie.setGenres(genreNames);
        movie.setDuration(String.valueOf(movieDto.runtime()));
        movie.setReleaseDate(movieDto.releaseDate());
        movie.setImageUrl(movieDto.posterPath());

        return movieRepository.save(movie);
    }


    public Movie findById(String id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such ID " + id));
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    public void delete(String id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("No such ID " + id);
        }
        movieRepository.deleteById(id);
    }
}
