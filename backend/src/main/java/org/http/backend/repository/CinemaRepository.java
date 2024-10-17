package org.http.backend.repository;

import org.http.backend.entity.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepository extends MongoRepository<Cinema, String> {

    Cinema findByName(String cinemaName);

}
