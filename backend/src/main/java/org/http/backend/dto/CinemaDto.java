package org.http.backend.dto;

import org.http.backend.util.CinemaHall;

import java.util.List;

public record CinemaDto(String name, List<CinemaHall> cinemaHalls) {
}
