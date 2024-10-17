package org.http.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.http.backend.entity.Cinema;
import org.http.backend.entity.Movie;
import org.http.backend.entity.Schedule;
import org.http.backend.util.ShowRoom;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record ScheduleDto(@JsonProperty("datetime") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm") LocalDateTime dateTime, Cinema cinema , ShowRoom showRoom, Movie movie) {

    public Schedule toEntity(){
        List<ShowRoom> showRooms = new ArrayList<>();
        showRooms.add(showRoom);
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        Cinema newCinema = new Cinema(cinema.getName(), movies, showRooms);
        return new Schedule(dateTime, newCinema);
    }
}
