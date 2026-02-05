package org.netflix.api.service;


import org.netflix.api.dto.MovieRequest;
import org.netflix.api.model.Movie;
import org.netflix.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.*;

@Service
@Validated
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Add a new movie
    public Movie addMovie(@Valid MovieRequest movieRequest) {
        // Convert DTO to Model
        Movie movie = convertToEntity(movieRequest);

        // Additional business logic validation
        validateMovieBusinessRules(movie);

        // Save movie
        return movieRepository.addMovie(movie);
    }

    // Get movie by ID
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.getMovieById(id);
    }

    // Get all movies
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    // Update movie
    public Optional<Movie> updateMovie(Long id, @Valid MovieRequest movieRequest) {
        if (!movieRepository.existsById(id)) {
            return Optional.empty();
        }

        Movie movie = convertToEntity(movieRequest);
        movie.setId(id);
        validateMovieBusinessRules(movie);

        return movieRepository.updateMovie(id, movie);
    }

    // Delete movie
    public boolean deleteMovie(Long id) {
        return movieRepository.deleteMovie(id);
    }

    // Get movie count
    public int getMovieCount() {
        return movieRepository.getMovieCount();
    }

    // Convert DTO to Entity
    private Movie convertToEntity(MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setReleaseYear(movieRequest.getReleaseYear());
        movie.setGenre(movieRequest.getGenre());
        movie.setRating(movieRequest.getRating());
        movie.setDirector(movieRequest.getDirector());
        return movie;
    }

    // Additional business rule validation
    private void validateMovieBusinessRules(Movie movie) {
        // Example: Ensure rating is logical for the release year
        if (movie.getRating() > 9.5 && movie.getReleaseYear() < 1950) {
            throw new IllegalArgumentException("Very high ratings for very old movies need manual review");
        }

        // Example: Validate genre
        List<String> validGenres = Arrays.asList(
                "Action", "Adventure", "Comedy", "Drama", "Sci-Fi",
                "Thriller", "Horror", "Romance", "Documentary", "Animation"
        );

        if (!validGenres.contains(movie.getGenre())) {
            throw new IllegalArgumentException("Invalid genre. Must be one of: " + validGenres);
        }
    }
}
