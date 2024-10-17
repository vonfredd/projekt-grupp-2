package org.http.backend.repository;

import org.http.backend.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository  extends MongoRepository<Movie, String> {


    List<Movie> findByName(String name);
}
