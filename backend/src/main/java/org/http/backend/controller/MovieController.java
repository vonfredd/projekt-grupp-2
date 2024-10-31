package org.http.backend.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.http.backend.dto.MovieDto;
import org.http.backend.entity.Movie;
import org.http.backend.service.MovieService;
import org.http.backend.util.Rating;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @Value("${tmdb.access.token}")
    private String tmdbAccessToken;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> all() {
        return ResponseEntity.ok().body(movieService.findAll());
    }
    @GetMapping("/allWithRatingsConverted")
    public ResponseEntity<List<Map<String, Object>>> allWithRatingsConverted() {
        return ResponseEntity.ok().body(movieService.findAllWithRatingsConverted());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> one(@PathVariable String id) {
        return ResponseEntity.ok().body(movieService.findById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Movie>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok().body(movieService.findByNameContainsIgnoreCase(name));
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody String jsonString) throws JsonProcessingException {
        try{
            return ResponseEntity.status(201).body(movieService.save(jsonString));
        }catch (JsonMappingException | IllegalArgumentException e){
            return ResponseEntity.status(400).body(movieService.save(jsonString));
        }
    }

    @PutMapping("{movieId}/rating")
    public ResponseEntity<?> addRating(@PathVariable String movieId, @RequestBody Rating rating) {
      try {
          return ResponseEntity.ok().body(movieService.addRating(movieId, rating));
      } catch (Exception e) {
          return ResponseEntity.status(400).body("Error: " + e.getMessage());
      }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<MovieDto> fetchMovieFromTmdb(@PathVariable String id) {
        ObjectMapper objectMapper = new ObjectMapper();

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.themoviedb.org/3/movie/" + id + "?language=en-US";

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", "Bearer " + tmdbAccessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            MovieDto movieDto = objectMapper.readValue(response.getBody(), MovieDto.class);
            return ResponseEntity.ok().body(movieDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

}
