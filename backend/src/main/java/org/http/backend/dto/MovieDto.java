package org.http.backend.dto;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;
import java.util.List;

public record MovieDto(int id, String original_title, String overview, List<MovieGenreDto> genres, int runtime, String release_date, String poster_path) implements Serializable {

}









