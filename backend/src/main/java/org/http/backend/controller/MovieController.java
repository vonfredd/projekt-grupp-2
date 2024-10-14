package org.http.backend.controller;
import org.http.backend.entity.Movie;
import org.http.backend.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:5173")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> all() {
        return ResponseEntity.ok().body(movieService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Movie> one(String id) {
        return ResponseEntity.ok().body(movieService.findById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Movie>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok().body(movieService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody String jsonString) {
        return ResponseEntity.status(201).body(movieService.save(jsonString));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.delete(id);
        return ResponseEntity.ok().build();
    }

}
