package org.http.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GenreDto(@JsonProperty("name") String name, @JsonProperty("id") String id) {
}
