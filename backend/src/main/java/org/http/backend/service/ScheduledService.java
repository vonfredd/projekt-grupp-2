package org.http.backend.service;

import org.http.backend.dto.ScheduleDto;
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

    public Schedule add(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleDto.toEntity();
        System.out.println("\nSCHEDULE ENTITY\n" +"DATE AND TIME:" + schedule.getDateTime()+"\nNAME OF CINEMA:" + schedule.getCinema().getName() + "\nName of Hall: " + schedule.getCinema().getShowRooms().getFirst() + "\nName of movie: " +  schedule.getCinema().getMovies().getFirst().getName());
        return scheduleRepository.save(scheduleDto.toEntity());
    }
}
