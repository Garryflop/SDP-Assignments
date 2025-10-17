package Assignment5.com.cinema.ticketbook.facade;

import Assignment5.com.cinema.ticketbook.exception.BookingException;
import Assignment5.com.cinema.ticketbook.model.*;
import Assignment5.com.cinema.ticketbook.service.*;

import java.util.List;

/**
 * FACADE PATTERN IMPLEMENTATION
 * 
 * CinemaTicketBookingFacade provides a simplified interface to the complex subsystem
 * of services (MovieService, SeatService, PaymentService, NotificationService, BookingService).
 * 
 * Benefits of this Facade:
 * 1. Simplifies the booking process for clients
 * 2. Hides the complexity of coordinating multiple services
 * 3. Provides a single entry point for ticket booking operations
 * 4. Reduces coupling between client code and subsystem classes
 * 5. Makes the system easier to use and understand
 * 
 * Clean Code Principles Applied:
 * - Single Responsibility: Each method has one clear purpose
 * - Meaningful Names: Method names clearly describe their actions
 * - Small Methods: Each method is focused and concise
 * - Error Handling: Proper exception handling throughout
 */
public class CinemaTicketBookingFacade {
    // Complex subsystems hidden behind the facade
    private final MovieService movieService;
    private final SeatService seatService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final BookingService bookingService;

    /**
     * Constructor initializes all required services.
     * In a real application, these could be injected via Dependency Injection.
     */
    public CinemaTicketBookingFacade() {
        this.movieService = new MovieService();
        this.seatService = new SeatService();
        this.paymentService = new PaymentService();
        this.notificationService = new NotificationService();
        this.bookingService = new BookingService();
    }

    /**
     * MAIN FACADE METHOD
     * 
     * Simplified interface for booking a movie ticket.
     * This single method coordinates all the complex operations:
     * 1. Validate movie availability
     * 2. Validate seat availability
     * 3. Reserve the seat
     * 4. Create booking
     * 5. Process payment
     * 6. Send notifications
     * 
     * Without facade, client would need to call all services individually
     * and handle coordination logic themselves.
     */
    public Ticket bookTicket(User user, String movieId, String seatId, PaymentMethod paymentMethod) {
        System.out.println("\n========== BOOKING PROCESS STARTED ==========");
        System.out.println("User: " + user.getName());
        System.out.println("Movie ID: " + movieId);
        System.out.println("Seat ID: " + seatId);
        System.out.println("=============================================\n");

        try {
            // Step 1: Validate and get movie details
            movieService.validateMovie(movieId);
            Movie movie = movieService.getMovieById(movieId);
            movieService.logMovieSelection(movieId);

            // Step 2: Validate and reserve seat
            seatService.validateSeatSelection(movieId, seatId);
            Seat seat = seatService.getSeat(movieId, seatId);
            seatService.reserveSeat(movieId, seatId);

            // Step 3: Create booking
            Ticket ticket = bookingService.createBooking(user, movie, seat);

            // Step 4: Process payment
            Payment payment = paymentService.processPayment(
                    ticket.getTicketId(),
                    ticket.getPrice(),
                    paymentMethod
            );

            // Step 5: Confirm booking after successful payment
            if (paymentService.verifyPayment(payment.getPaymentId())) {
                bookingService.confirmBooking(ticket.getTicketId());
            }

            // Step 6: Send notifications
            notificationService.sendAllNotifications(user, ticket);

            System.out.println("\n========== BOOKING COMPLETED SUCCESSFULLY ==========");
            return ticket;

        } catch (Exception e) {
            System.err.println("\n========== BOOKING FAILED ==========");
            System.err.println("Error: " + e.getMessage());
            
            // Rollback: Release seat if booking fails
            try {
                seatService.releaseSeat(movieId, seatId);
            } catch (Exception rollbackError) {
                // Log rollback error but don't throw
                System.err.println("Rollback error: " + rollbackError.getMessage());
            }
            
            throw new BookingException("Booking failed: " + e.getMessage(), e);
        }
    }

    /**
     * Get all available movies.
     * Simplified interface to MovieService.
     */
    public List<Movie> getAvailableMovies() {
        return movieService.getAllAvailableMovies();
    }

    /**
     * Get available seats for a specific movie.
     * Simplified interface to SeatService.
     */
    public List<Seat> getAvailableSeats(String movieId) {
        movieService.validateMovie(movieId);
        return seatService.getAvailableSeats(movieId);
    }

    /**
     * Get movie details.
     * Simplified interface to MovieService.
     */
    public Movie getMovieDetails(String movieId) {
        return movieService.getMovieById(movieId);
    }

    /**
     * Cancel a booking.
     * Coordinates cancellation across multiple services.
     */
    public void cancelBooking(String ticketId) {
        try {
            Ticket ticket = bookingService.getBooking(ticketId);
            
            // Release the seat
            seatService.releaseSeat(
                    ticket.getMovie().getMovieId(),
                    ticket.getSeat().getSeatId()
            );
            
            // Cancel the booking
            bookingService.cancelBooking(ticketId);
            
            System.out.println("Booking cancelled successfully: " + ticketId);
            
        } catch (Exception e) {
            throw new BookingException("Failed to cancel booking: " + e.getMessage(), e);
        }
    }

    /**
     * Get booking details.
     * Simplified interface to BookingService.
     */
    public Ticket getBookingDetails(String ticketId) {
        return bookingService.getBooking(ticketId);
    }

    /**
     * Check if a specific seat is available for a movie.
     * Coordinates movie and seat validation.
     */
    public boolean isSeatAvailable(String movieId, String seatId) {
        if (!movieService.isMovieAvailable(movieId)) {
            return false;
        }
        return seatService.isSeatAvailable(movieId, seatId);
    }
}
