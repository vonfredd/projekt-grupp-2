package org.http.backend.service;

import org.http.backend.dto.CinemaHallDto;
import org.http.backend.entity.Cinema;
import org.http.backend.util.CinemaHall;
import org.http.backend.repository.CinemaRepository;
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

    public Cinema create(Cinema cinema) {
        cinema.setCinemaHalls(new ArrayList<>());
        return cinemaRepository.save(cinema);
    }

    public Cinema addCinemaHall(String cinemaName, CinemaHallDto cinemaHallDto) {
        Cinema existingCinema = cinemaRepository.findByName(cinemaName);

        if (existingCinema == null) {
            throw new RuntimeException("Cinema not found: " + cinemaName);
        }

        boolean hallExists = existingCinema.getCinemaHalls()
                .stream()
                .anyMatch(hall -> hall.getName().equals(cinemaHallDto.name()));

        if (hallExists) {
            throw new RuntimeException("Cinema hall already exists: " + cinemaHallDto.name());
        }



        List<CinemaHall> halls = existingCinema.getCinemaHalls();
        halls.add(new CinemaHall(cinemaHallDto.name(), cinemaHallDto.nrOfSeats()));
        existingCinema.setCinemaHalls(halls);

        return cinemaRepository.save(existingCinema);
    }
}