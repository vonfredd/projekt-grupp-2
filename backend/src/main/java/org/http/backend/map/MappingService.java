package org.http.backend.map;

import org.http.backend.dto.MovieDto;
import org.http.backend.dto.MovieGenreDto;
import org.http.backend.entity.Movie;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MappingService {
    public MovieDto mapToMovieDto(Movie movie) {
        List<MovieGenreDto> movieGenreDtoList = List.of(new MovieGenreDto(0, movie.getGenre()));
        MovieDto movieDto = new MovieDto(
                0,
                movie.getImdbId(),
                movie.getName(),
                movie.getDescription(),
                movieGenreDtoList,
                movie.getDuration(),
                movie.getReleaseDate(),
                movie.getImageUrl()
        );
        return movieDto;
    }

    public List<MovieDto> mapToMovieDtos(List<Movie> movies) {
        return movies.stream()
                .map(this::mapToMovieDto)
                .collect(Collectors.toList());
    }

    public Movie mapToMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setImdbId(movieDto.imdb_id());
        movie.setName(movieDto.original_title());
        movie.setDescription(movieDto.overview());
        String genre = Optional.ofNullable(movieDto)
                .map(dto -> dto.genres())
                .filter(genres -> !genres.isEmpty())
                .map(genres -> genres.getFirst())
                .map(firstGenre -> firstGenre.name())
                .orElse(null);  // or a default value if needed

        movie.setGenre(genre);
        movie.setDuration(movieDto.runtime());
        movie.setReleaseDate(movieDto.release_date());
        movie.setImageUrl(movieDto.poster_path());
        return movie;
    }
}
