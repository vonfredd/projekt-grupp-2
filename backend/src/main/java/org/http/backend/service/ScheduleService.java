package org.http.backend.service;

import org.http.backend.entity.Schedule;
import org.http.backend.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule find(String id) {
        return scheduleRepository.findById(id);
    }

    public Schedule add(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findByMovieId(String movieId) {
        return scheduleRepository.findByMovie_Id(movieId);
    }
}
