package org.http.backend.repository;

import org.http.backend.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository  extends MongoRepository<Movie, String> {
    @Override
    List<Movie> findAll();

    List<Movie> findByName(String name);
}

//{
//        "name": "Inception",
//        "description": "A mind-bending thriller where dream invasion is possible.",
//        "genre": [1, 2, 3],
//        "duration": "148 minutes",
//        "releaseDate": "2010-07-16",
//        "imageUrl": "https://example.com/images/inception.jpg",
//        "rating": {
//        "userId": "1",
//        "movieRating": 5
//        }
//        }
