package org.netflix.api.repository;

import org.netflix.api.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MovieRepository {
    // Thread-safe storage using ConcurrentHashMap
    private final Map<Long, Movie> movieStore = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Sample initial data
    public MovieRepository() {
        // Add some sample movies
        addMovie(new Movie(idCounter.getAndIncrement(),
                "Inception",
                "A thief who steals corporate secrets through dream-sharing technology.",
                2010, "Sci-Fi/Thriller", 8.8, "Christopher Nolan"));

        addMovie(new Movie(idCounter.getAndIncrement(),
                "The Shawshank Redemption",
                "Two imprisoned men bond over a number of years.",
                1994, "Drama", 9.3, "Frank Darabont"));
    }

    // Add a new movie
    public Movie addMovie(Movie movie) {
        if (movie.getId() == null) {
            movie.setId(idCounter.getAndIncrement());
        }
        movieStore.put(movie.getId(), movie);
        return movie;
    }

    // Get movie by ID
    public Optional<Movie> getMovieById(Long id) {
        return Optional.ofNullable(movieStore.get(id));
    }

    // Get all movies
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movieStore.values());
    }

    // Update movie
    public Optional<Movie> updateMovie(Long id, Movie movie) {
        if (movieStore.containsKey(id)) {
            movie.setId(id);
            movieStore.put(id, movie);
            return Optional.of(movie);
        }
        return Optional.empty();
    }

    // Delete movie
    public boolean deleteMovie(Long id) {
        return movieStore.remove(id) != null;
    }

    // Check if movie exists
    public boolean existsById(Long id) {
        return movieStore.containsKey(id);
    }

    // Get movie count
    public int getMovieCount() {
        return movieStore.size();
    }
}