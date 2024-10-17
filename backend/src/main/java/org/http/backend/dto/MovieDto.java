package org.http.backend.dto;

import java.util.List;

public record MovieDto(String id, String title, String overview, int runtime, String releaseDate, String posterPath, List<GenreDto> genres) {
    public MovieDto {
        if (genres == null) {
            genres = List.of();
        }
    }
}
