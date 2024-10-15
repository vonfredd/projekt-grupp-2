package org.http.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.http.backend.entity.Cinema;
import org.http.backend.repository.CinemaRepository;
import org.http.backend.util.CinemaHall;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
            throw new NoSuchElementException("No cinema found with ID " + id);
        }
    }

    public Cinema create(Cinema cinema){
        cinema.setMovies(new ArrayList<>());
        cinema.setCinemaHalls(new ArrayList<>());
        return cinemaRepository.save(cinema);
    }


    public Cinema addCinemaHall(String cinemaName, CinemaHall cinemaHall) {
        Optional<Cinema> optionalCinema = cinemaRepository.findByName(cinemaName);

        if (optionalCinema.isPresent()) {
            Cinema cinema = optionalCinema.get();
            cinema.getCinemaHalls().add(cinemaHall);
            return cinemaRepository.save(cinema);
        } else {
            throw new RuntimeException("Cinema not found");
        }
    }
}
