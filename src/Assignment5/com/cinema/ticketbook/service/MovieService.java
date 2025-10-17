package Assignment5.com.cinema.ticketbook.service;

import Assignment5.com.cinema.ticketbook.exception.MovieNotFoundException;
import Assignment5.com.cinema.ticketbook.model.Movie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for managing movie information and availability.
 * Complex subsystem that will be hidden behind the Facade.
 */
public class MovieService {
    private final Map<String, Movie> movies;

    public MovieService() {
        this.movies = new HashMap<>();
        initializeMovies();
    }

    /**
     * Initialize some sample movies for demonstration.
     */
    private void initializeMovies() {
        movies.put("M001", new Movie("M001", "The Matrix", "Sci-Fi", 136, 
                LocalDateTime.now().plusDays(1).withHour(18).withMinute(0), 1));
        movies.put("M002", new Movie("M002", "Inception", "Thriller", 148, 
                LocalDateTime.now().plusDays(1).withHour(20).withMinute(30), 2));
        movies.put("M003", new Movie("M003", "Interstellar", "Sci-Fi", 169, 
                LocalDateTime.now().plusDays(2).withHour(19).withMinute(0), 1));
    }

    /**
     * Check if a movie exists and is available for booking.
     */
    public boolean isMovieAvailable(String movieId) {
        Movie movie = movies.get(movieId);
        if (movie == null) {
            return false;
        }
        return movie.getShowTime().isAfter(LocalDateTime.now());
    }

    /**
     * Get movie details by ID.
     */
    public Movie getMovieById(String movieId) {
        Movie movie = movies.get(movieId);
        if (movie == null) {
            throw new MovieNotFoundException(movieId);
        }
        return movie;
    }

    /**
     * Get all available movies.
     */
    public List<Movie> getAllAvailableMovies() {
        List<Movie> availableMovies = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        
        for (Movie movie : movies.values()) {
            if (movie.getShowTime().isAfter(now)) {
                availableMovies.add(movie);
            }
        }
        return availableMovies;
    }

    /**
     * Validate movie details before booking.
     */
    public void validateMovie(String movieId) {
        if (!isMovieAvailable(movieId)) {
            throw new MovieNotFoundException("Movie is not available for booking: " + movieId);
        }
    }

    /**
     * Log movie selection for analytics.
     */
    public void logMovieSelection(String movieId) {
        System.out.println("[MovieService] Movie selected: " + movieId);
    }
}
