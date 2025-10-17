package Assignment5.com.cinema.ticketbook.exception;

/**
 * Custom exception for movie-related errors.
 */
public class MovieNotFoundException extends RuntimeException {
//    public MovieNotFoundException(String message) {
//        super(message);
//    }

    public MovieNotFoundException(String movieId) {
        super(String.format("Movie with ID '%s' not found", movieId));
    }
}
