package Assignment5.com.cinema.ticketbook.service;

import Assignment5.com.cinema.ticketbook.exception.SeatNotAvailableException;
import Assignment5.com.cinema.ticketbook.model.Seat;
import Assignment5.com.cinema.ticketbook.model.SeatType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for managing seat availability and reservations.
 * Complex subsystem that will be hidden behind the Facade.
 */
public class SeatService {
    private final Map<String, List<Seat>> movieSeats;

    public SeatService() {
        this.movieSeats = new HashMap<>();
        initializeSeats();
    }

    /**
     * Initialize seats for each movie hall.
     */
    private void initializeSeats() {
        initializeSeatsForMovie("M001");
        initializeSeatsForMovie("M002");
        initializeSeatsForMovie("M003");
    }

    private void initializeSeatsForMovie(String movieId) {
        List<Seat> seats = new ArrayList<>();
        
        // Create Standard seats (rows 1-3)
        for (int row = 1; row <= 3; row++) {
            for (int seatNum = 1; seatNum <= 10; seatNum++) {
                String seatId = String.format("%s-R%dS%d", movieId, row, seatNum);
                seats.add(new Seat(seatId, row, seatNum, SeatType.STANDARD));
            }
        }
        
        // Create Premium seats (rows 4-5)
        for (int row = 4; row <= 5; row++) {
            for (int seatNum = 1; seatNum <= 10; seatNum++) {
                String seatId = String.format("%s-R%dS%d", movieId, row, seatNum);
                seats.add(new Seat(seatId, row, seatNum, SeatType.PREMIUM));
            }
        }
        
        // Create VIP seats (row 6)
        for (int seatNum = 1; seatNum <= 8; seatNum++) {
            String seatId = String.format("%s-R6S%d", movieId, seatNum);
            seats.add(new Seat(seatId, 6, seatNum, SeatType.VIP));
        }
        
        movieSeats.put(movieId, seats);
    }

    /**
     * Check if a specific seat is available.
     */
    public boolean isSeatAvailable(String movieId, String seatId) {
        List<Seat> seats = movieSeats.get(movieId);
        if (seats == null) {
            return false;
        }
        
        return seats.stream()
                .filter(seat -> seat.getSeatId().equals(seatId))
                .findFirst()
                .map(Seat::isAvailable)
                .orElse(false);
    }

    /**
     * Get available seats for a movie.
     */
    public List<Seat> getAvailableSeats(String movieId) {
        List<Seat> seats = movieSeats.get(movieId);
        if (seats == null) {
            return new ArrayList<>();
        }
        
        return seats.stream()
                .filter(Seat::isAvailable)
                .collect(Collectors.toList());
    }

    /**
     * Get a specific seat.
     */
    public Seat getSeat(String movieId, String seatId) {
        List<Seat> seats = movieSeats.get(movieId);
        if (seats == null) {
            throw new SeatNotAvailableException("No seats found for movie: " + movieId);
        }
        
        return seats.stream()
                .filter(seat -> seat.getSeatId().equals(seatId))
                .findFirst()
                .orElseThrow(() -> new SeatNotAvailableException("Seat not found: " + seatId));
    }

    /**
     * Reserve a seat (mark as unavailable).
     */
    public void reserveSeat(String movieId, String seatId) {
        if (!isSeatAvailable(movieId, seatId)) {
            throw new SeatNotAvailableException(seatId, movieId);
        }
        
        Seat seat = getSeat(movieId, seatId);
        seat.setAvailable(false);
        System.out.println("[SeatService] Seat reserved: " + seatId);
    }

    /**
     * Release a reserved seat (mark as available again).
     */
    public void releaseSeat(String movieId, String seatId) {
        Seat seat = getSeat(movieId, seatId);
        seat.setAvailable(true);
        System.out.println("[SeatService] Seat released: " + seatId);
    }

    /**
     * Validate seat selection.
     */
    public void validateSeatSelection(String movieId, String seatId) {
        if (!isSeatAvailable(movieId, seatId)) {
            throw new SeatNotAvailableException(seatId, movieId);
        }
    }
}
