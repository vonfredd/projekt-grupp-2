package org.http.backend.service;

import org.http.backend.entity.MovieHall;
import org.http.backend.repository.MovieHallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieHallService {

    private final MovieHallRepository movieHallRepository;
    public MovieHallService(MovieHallRepository movieHallRepository) {
        this.movieHallRepository = movieHallRepository;
    }

    public List<MovieHall> findAll() {
        return movieHallRepository.findAll();
    }

    public MovieHall create(MovieHall movieHall) {
        return movieHallRepository.save(movieHall);
    }

    public List<MovieHall> findByName(String name) {
        return movieHallRepository.findByName(name);
    }
}
