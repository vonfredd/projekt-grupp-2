package org.http.backend.controller;

import org.http.backend.dto.CinemaHallDto;
import org.http.backend.entity.Cinema;
import org.http.backend.service.CinemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
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
    @PutMapping("/{cinemaName}/halls")
    public ResponseEntity<Cinema> addCinemaHalls(@PathVariable String cinemaName, @RequestBody CinemaHallDto cinemaHallDto) {
        Cinema updatedCinema = cinemaService.addCinemaHall(cinemaName, cinemaHallDto);
        return ResponseEntity.ok(updatedCinema);
    }
}
