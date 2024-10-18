package org.http.backend.dto;
import org.http.backend.util.CinemaHall;

import java.time.LocalDateTime;

public record ScheduleDto(LocalDateTime localDateTime, String cinemaName, CinemaHall cinemaHall, String movieId, String movieTitle) {
}
