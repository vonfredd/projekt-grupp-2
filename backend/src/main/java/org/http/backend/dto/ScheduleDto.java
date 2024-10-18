package org.http.backend.dto;

import org.http.backend.entity.Cinema;
import org.http.backend.entity.Movie;
import org.http.backend.util.CinemaHall;

import java.time.LocalDateTime;

public record ScheduleDto(LocalDateTime localDateTime, Cinema cinema, CinemaHall cinemaHall, Movie movie) {
}
