package org.http.backend.controller;

import org.http.backend.dto.ScheduleDto;
import org.http.backend.entity.Schedule;
import org.http.backend.service.ScheduledService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@CrossOrigin(origins = "http://localhost:5174")
public class ScheduleController {
    private final ScheduledService scheduledService;

    public ScheduleController(ScheduledService scheduledService) {
        this.scheduledService = scheduledService;
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> all() {
        return ResponseEntity.ok().body(scheduledService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> find(@PathVariable(name = "id") String scheduleId) {
        return ResponseEntity.ok().body(scheduledService.find(scheduleId));
    }

    @PostMapping("/new")
    public ResponseEntity<Schedule> save(@RequestBody ScheduleDto scheduleDto) {
        return ResponseEntity.ok().body(scheduledService.add(scheduleDto));
    }
}
