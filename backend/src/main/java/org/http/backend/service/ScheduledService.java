package org.http.backend.service;

import lombok.AllArgsConstructor;
import org.http.backend.entity.Schedule;
import org.http.backend.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduledService {

    private final ScheduleRepository scheduleRepository;

    public ScheduledService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule find(String id) {
        Optional <Schedule> schedule = scheduleRepository.findById(id);
        return schedule.orElseThrow(()-> new RuntimeException("Schedule not found"));
    }

    public Schedule add(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
