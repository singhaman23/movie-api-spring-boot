package org.netflix.api.controller;


import org.netflix.api.dto.ApiResponse;
import org.netflix.api.dto.MovieRequest;
import org.netflix.api.model.Movie;
import org.netflix.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*") // Allow all origins for testing
public class MovieController {

    @Autowired
    private MovieService movieService;

    // POST: Add a new movie
    @PostMapping
    public ResponseEntity<ApiResponse> addMovie(@Valid @RequestBody MovieRequest movieRequest) {
        try {
            Movie savedMovie = movieService.addMovie(movieRequest);
            ApiResponse response = new ApiResponse(true, "Movie added successfully", savedMovie);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ApiResponse response = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Internal server error", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // GET: Get movie by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            ApiResponse response = new ApiResponse(true, "Movie found", movie.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(false, "Movie not found with id: " + id, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // GET: Get all movies
    @GetMapping
    public ResponseEntity<ApiResponse> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        ApiResponse response = new ApiResponse(true, "Movies retrieved successfully", movies);
        return ResponseEntity.ok(response);
    }

    // GET: Get movie count
    @GetMapping("/count")
    public ResponseEntity<ApiResponse> getMovieCount() {
        int count = movieService.getMovieCount();
        ApiResponse response = new ApiResponse(true, "Movie count retrieved", count);
        return ResponseEntity.ok(response);
    }

    // PUT: Update movie
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMovie(@PathVariable Long id,
                                                   @Valid @RequestBody MovieRequest movieRequest) {
        Optional<Movie> updatedMovie = movieService.updateMovie(id, movieRequest);
        if (updatedMovie.isPresent()) {
            ApiResponse response = new ApiResponse(true, "Movie updated successfully", updatedMovie.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(false, "Movie not found with id: " + id, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // DELETE: Delete movie
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable Long id) {
        boolean deleted = movieService.deleteMovie(id);
        if (deleted) {
            ApiResponse response = new ApiResponse(true, "Movie deleted successfully", null);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(false, "Movie not found with id: " + id, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
