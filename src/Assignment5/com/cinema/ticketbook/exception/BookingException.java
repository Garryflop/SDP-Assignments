package Assignment5.com.cinema.ticketbook.exception;

/**
 * Custom exception for booking-related errors.
 */
public class BookingException extends RuntimeException {
    public BookingException(String message) {
        super(message);
    }

    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }
}
