package org.http.backend.controller;

import org.http.backend.dto.MovieDto;
import org.http.backend.entity.Movie;
import org.http.backend.map.MappingService;
import org.http.backend.repository.MovieRepository;
import org.http.backend.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:5173")
public class MovieController {
    private final MovieService movieService;
    private final MappingService mapS = new MappingService();
    private final MovieRepository movieRepository;

    public MovieController(MovieService movieService,
                           MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> findAll() {
        return ResponseEntity.ok().body(mapS.mapToMovieDtos(movieRepository.findAll()));
    }

    @GetMapping("/id")
    public ResponseEntity<MovieDto> findById(String id) {
        return ResponseEntity.ok().body(mapS.mapToMovieDto(movieService.findById(id)));
    }

    @GetMapping("/name")
    public ResponseEntity<List<MovieDto>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok().body(mapS.mapToMovieDtos(movieService.findByName(name)));
    }

    @PostMapping
    public ResponseEntity<MovieDto> save(@RequestBody MovieDto movieDto) throws IOException {
        return ResponseEntity.ok().body(mapS.mapToMovieDto(movieService.save(movieDto)));
    }

}
