package Assignment5.com.cinema.ticketbook.service;

import Assignment5.com.cinema.ticketbook.exception.BookingException;
import Assignment5.com.cinema.ticketbook.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Service for managing ticket bookings.
 * Complex subsystem that will be hidden behind the Facade.
 */
public class BookingService {
    private final Map<String, Ticket> tickets;

    public BookingService() {
        this.tickets = new HashMap<>();
    }

    /**
     * Create a ticket booking.
     */
    public Ticket createBooking(User user, Movie movie, Seat seat) {
        try {
            validateBooking(user, movie, seat);
            
            String ticketId = generateTicketId();
            double price = calculatePrice(seat);
            
            Ticket ticket = new Ticket(ticketId, user, movie, seat, price);
            tickets.put(ticketId, ticket);
            
            System.out.println("[BookingService] Ticket created: " + ticketId);
            return ticket;
            
        } catch (Exception e) {
            throw new BookingException("Failed to create booking: " + e.getMessage(), e);
        }
    }

    /**
     * Validate booking details.
     * Note: Seat availability is validated by SeatService before this is called.
     */
    private void validateBooking(User user, Movie movie, Seat seat) {
        if (user == null) {
            throw new BookingException("User information is required");
        }
        if (movie == null) {
            throw new BookingException("Movie information is required");
        }
        if (seat == null) {
            throw new BookingException("Seat information is required");
        }
        // Note: We don't check seat.isAvailable() here because the seat
        // has already been reserved by SeatService before creating the booking
    }

    /**
     * Calculate ticket price based on seat type.
     */
    private double calculatePrice(Seat seat) {
        double basePrice = seat.getPrice();
        // Could add additional pricing logic here (e.g., weekend surcharge, holidays)
        System.out.println("[BookingService] Calculated price: $" + basePrice);
        return basePrice;
    }

    /**
     * Generate a unique ticket ID.
     */
    private String generateTicketId() {
        return "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Get booking details.
     */
    public Ticket getBooking(String ticketId) {
        Ticket ticket = tickets.get(ticketId);
        if (ticket == null) {
            throw new BookingException("Booking not found: " + ticketId);
        }
        return ticket;
    }

    /**
     * Cancel a booking.
     */
    public void cancelBooking(String ticketId) {
        if (tickets.remove(ticketId) != null) {
            System.out.println("[BookingService] Booking cancelled: " + ticketId);
        } else {
            throw new BookingException("Cannot cancel - booking not found: " + ticketId);
        }
    }

    /**
     * Confirm booking after payment.
     */
    public void confirmBooking(String ticketId) {
        Ticket ticket = getBooking(ticketId);
        System.out.println("[BookingService] Booking confirmed: " + ticketId);
    }
}
