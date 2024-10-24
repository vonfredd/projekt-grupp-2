package org.http.backend.repository;

import org.http.backend.entity.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, Integer> {

    Schedule findById(String id);

    List<Schedule> findByMovie_Id(String movieId);
}
