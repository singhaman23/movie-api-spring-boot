package org.netflix.api.model;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class Movie {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotNull(message = "Release year is required")
    @Min(value = 1888, message = "Release year must be after 1888")
    @Max(value = 2025, message = "Release year cannot be in the future")
    private Integer releaseYear;

    @NotBlank(message = "Genre is required")
    private String genre;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating cannot exceed 10.0")
    private Double rating;

    @NotBlank(message = "Director is required")
    private String director;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    // Constructors
    public Movie() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Movie(Long id, String title, String description, Integer releaseYear,
                 String genre, Double rating, String director) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
        this.director = director;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
        this.updatedAt = LocalDate.now();
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDate.now();
    }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDate.now();
    }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        this.updatedAt = LocalDate.now();
    }

    public String getGenre() { return genre; }
    public void setGenre(String genre) {
        this.genre = genre;
        this.updatedAt = LocalDate.now();
    }

    public Double getRating() { return rating; }
    public void setRating(Double rating) {
        this.rating = rating;
        this.updatedAt = LocalDate.now();
    }

    public String getDirector() { return director; }
    public void setDirector(String director) {
        this.director = director;
        this.updatedAt = LocalDate.now();
    }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public LocalDate getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; }
}
