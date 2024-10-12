package org.http.backend.controller;

import org.http.backend.entity.MovieHall;
import org.http.backend.service.MovieHallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/halls")
public class MovieHallController {
    private final MovieHallService movieHallService;
    public MovieHallController(MovieHallService movieHallService) {
        this.movieHallService = movieHallService;
    }

    @GetMapping
    public ResponseEntity<List<MovieHall>> all() {
        return ResponseEntity.ok().body(movieHallService.findAll());
    }

    @GetMapping("/name")
    public ResponseEntity<List<MovieHall>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok().body(movieHallService.findByName(name));
    }
}
