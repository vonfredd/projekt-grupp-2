package org.http.backend.controller;

import org.http.backend.entity.Cinema;
import org.http.backend.service.CinemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
@CrossOrigin(origins = "http://localhost:5174")
public class CinemaController {



    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public ResponseEntity<List<Cinema>> all() {
        return ResponseEntity.ok().body(cinemaService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Cinema> one(@RequestParam String id) {
        return ResponseEntity.ok().body(cinemaService.one(id));
    }

    @PostMapping
    public ResponseEntity<Cinema> create(@RequestBody Cinema cinema) {
        return ResponseEntity.ok().body(cinemaService.create(cinema));
    }
}
