package org.http.backend.dto;

import java.io.Serializable;
import java.util.List;

public record MovieDto(int id, String imdb_id, String original_title, String overview, List<MovieGenreDto> genres, int runtime, String release_date, String poster_path) implements Serializable {

}









