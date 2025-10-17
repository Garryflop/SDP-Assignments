package Assignment5.com.cinema.ticketbook.exception;

/**
 * Custom exception for seat-related errors.
 * Demonstrates Clean Code principle of using domain-specific exceptions.
 */
public class SeatNotAvailableException extends RuntimeException {
    public SeatNotAvailableException(String message) {
        super(message);
    }

    public SeatNotAvailableException(String seatId, String movieTitle) {
        super(String.format("Seat %s is not available for movie '%s'", seatId, movieTitle));
    }
}
