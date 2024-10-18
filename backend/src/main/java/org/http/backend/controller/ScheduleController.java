package org.http.backend.controller;

import org.http.backend.entity.Schedule;
import org.http.backend.service.BookingService;
import org.http.backend.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")
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
    public ResponseEntity<Schedule> find(@PathVariable(name = "id") int scheduleId) {
        return ResponseEntity.ok().body(scheduleService.find(scheduleId));
    }

    @GetMapping("/{scheduleId}/booked-seats")
    public List<Integer> getBookedSeatsForSchedule(@PathVariable int scheduleId) {

        return bookingService.getBookedSeatsByScheduleId(scheduleId);
    }

    @PostMapping("/new")
    public ResponseEntity<Schedule> save(Schedule schedule) {
        return ResponseEntity.ok().body(scheduleService.add(schedule));
    }


}
