package org.http.backend.controller;

import org.http.backend.entity.Schedule;
import org.http.backend.service.BookingService;
import org.http.backend.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    private final BookingService bookingService;

    public ScheduleController(ScheduleService scheduleService, BookingService bookingService) {
        this.scheduleService = scheduleService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> all() {
        return ResponseEntity.ok().body(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> find(@PathVariable(name = "id") String scheduleId) {
        return ResponseEntity.ok().body(scheduleService.find(scheduleId));
    }

    @GetMapping("/{scheduleId}/booked-seats")
    public List<Integer> getBookedSeatsForSchedule(@PathVariable String scheduleId) {

        return bookingService.getBookedSeatsByScheduleId(scheduleId);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Schedule>> findByMovieId(@PathVariable("movieId") String movieId) {
        try {
            List<Schedule> schedules = scheduleService.findByMovieId(movieId);
            return ResponseEntity.ok(schedules);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Collections.emptyList());
        }
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Schedule> save(@RequestBody Schedule schedule) {
        try {
            Schedule savedSchedule = scheduleService.add(schedule);
            return ResponseEntity.ok().body(savedSchedule);
        } catch (Exception e) {
            Schedule emptySchedule = new Schedule();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(emptySchedule);
        }
    }

}
