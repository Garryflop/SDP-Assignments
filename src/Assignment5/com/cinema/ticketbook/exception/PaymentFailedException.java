package Assignment5.com.cinema.ticketbook.exception;

/**
 * Custom exception for payment-related errors.
 */
public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) {
        super(message);
    }

    public PaymentFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
