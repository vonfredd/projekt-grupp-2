package org.http.backend.repository;

import org.http.backend.entity.MovieHall;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieHallRepository extends MongoRepository<MovieHall, String> {

    List<MovieHall> findByName(String name);
}
