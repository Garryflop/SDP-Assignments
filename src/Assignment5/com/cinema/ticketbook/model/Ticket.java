package Assignment5.com.cinema.ticketbook.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a booked ticket.
 * Immutable class that ties together user, movie, and seat information.
 */
public class Ticket {
    private final String ticketId;
    private final User user;
    private final Movie movie;
    private final Seat seat;
    private final double price;
    private final LocalDateTime bookingTime;

    public Ticket(String ticketId, User user, Movie movie, Seat seat, double price) {
        validateTicketData(ticketId, user, movie, seat, price);
        this.ticketId = ticketId;
        this.user = user;
        this.movie = movie;
        this.seat = seat;
        this.price = price;
        this.bookingTime = LocalDateTime.now();
    }

    private void validateTicketData(String ticketId, User user, Movie movie, Seat seat, double price) {
        if (ticketId == null || ticketId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ticket ID cannot be null or empty");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        if (seat == null) {
            throw new IllegalArgumentException("Seat cannot be null");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    public String getTicketId() {
        return ticketId;
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", user=" + user.getName() +
                ", movie=" + movie.getTitle() +
                ", seat=" + seat.getSeatId() +
                ", price=" + price +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
