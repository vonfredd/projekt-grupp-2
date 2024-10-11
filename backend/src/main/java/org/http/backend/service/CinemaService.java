package org.http.backend.service;

import org.http.backend.entity.Cinema;
import org.http.backend.repository.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public Cinema one(String id) {
        Optional<Cinema> cinema = cinemaRepository.findById(id);
        if (cinema.isPresent()) {
            return cinema.get();
        } else {
            throw new RuntimeException("No such ID " + id);
        }
    }

    public Cinema create( Cinema cinema) {
        return cinemaRepository.save(cinema);
    }
}
