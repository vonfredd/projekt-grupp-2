package org.http.backend.controller;

import org.http.backend.dto.ScheduleDto;
import org.http.backend.entity.Cinema;
import org.http.backend.entity.Movie;
import org.http.backend.entity.Schedule;
import org.http.backend.service.BookingService;
import org.http.backend.service.CinemaService;
import org.http.backend.service.MovieService;
import org.http.backend.service.ScheduleService;
import org.http.backend.util.CinemaHall;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final CinemaService cinemaService;
    private final MovieService movieService;
    private final BookingService bookingService;

    public ScheduleController(ScheduleService scheduleService, CinemaService cinemaService, MovieService movieService, BookingService bookingService) {
        this.cinemaService = cinemaService;
        this.scheduleService = scheduleService;
        this.movieService = movieService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> all() {
        return ResponseEntity.ok().body(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> find(@PathVariable(name = "id") int scheduleId) {
        return ResponseEntity.ok().body(scheduleService.find(scheduleId));
    }

    @GetMapping("/{scheduleId}/booked-seats")
    public List<Integer> getBookedSeatsForSchedule(@PathVariable int scheduleId) {
        return bookingService.getBookedSeatsByScheduleId(scheduleId);
    }

    @PostMapping("/new")
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleDto scheduleDto) {
        Cinema cinema = cinemaService.findByName(scheduleDto.cinemaName());
        Movie movie = movieService.findById(scheduleDto.movieId());
        CinemaHall cinemaHall = scheduleDto.cinemaHall();
        Schedule schedule = new Schedule(scheduleDto.localDateTime(), cinema, cinemaHall, movie);
        Schedule createdSchedule = scheduleService.add(schedule);
        return ResponseEntity.ok().body(createdSchedule);
    }

}
