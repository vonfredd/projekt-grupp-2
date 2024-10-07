package org.http.backend.controller;

import org.http.backend.entity.Movie;
import org.http.backend.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok().body(movieService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Movie> findById(String id) {
        return ResponseEntity.ok().body(movieService.findById(id).get());
    }

    @GetMapping("/name")
    public ResponseEntity<List<Movie>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok().body(movieService.findByName(name));
    }
    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(movieService.save(movie));
    }

}
