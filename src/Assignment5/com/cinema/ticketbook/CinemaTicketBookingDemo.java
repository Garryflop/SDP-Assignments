package Assignment5.com.cinema.ticketbook;

import Assignment5.com.cinema.ticketbook.facade.CinemaTicketBookingFacade;
import Assignment5.com.cinema.ticketbook.model.*;

import java.util.List;

/**
 * Main demonstration class showing the Facade Pattern in action.
 * 
 * This class demonstrates:
 * 1. How the Facade simplifies complex operations
 * 2. Clean and readable client code
 * 3. Error handling
 * 4. Complete booking workflow
 */
public class CinemaTicketBookingDemo {

    public static void main(String[] args) {
        // Create the Facade - single entry point to the complex subsystem
        CinemaTicketBookingFacade bookingFacade = new CinemaTicketBookingFacade();

        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   CINEMA TICKET BOOKING SYSTEM - FACADE PATTERN DEMO  ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");


        // Demonstrate the power of Facade Pattern
        demonstrateWithFacade(bookingFacade);
    }

    /**
     * Demonstrates booking WITH Facade Pattern.
     * Notice how simple and clean the client code is!
     */
    private static void demonstrateWithFacade(CinemaTicketBookingFacade facade) {
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│  DEMONSTRATION: WITH FACADE PATTERN         │");
        System.out.println("└─────────────────────────────────────────────┘\n");

        try {
            // Step 1: Display available movies
            displayAvailableMovies(facade);

            // Step 2: Create a user
            User user = createSampleUser();

            // Step 3: Display available seats for a movie
            String selectedMovieId = "M001";
            displayAvailableSeats(facade, selectedMovieId);

            // Step 4: Book a ticket - ALL COMPLEXITY HIDDEN BEHIND ONE METHOD!
            System.out.println("\n--- Booking Ticket (Single Facade Call) ---");
            Ticket ticket = facade.bookTicket(
                    user,
                    selectedMovieId,
                    "M001-R6S5", // VIP seat
                    PaymentMethod.CREDIT_CARD
            );

            // Step 5: Display ticket details
            displayTicketDetails(ticket);

            // Step 6: Demonstrate seat availability check
            System.out.println("\n--- Checking Seat Availability ---");
            boolean isAvailable = facade.isSeatAvailable(selectedMovieId, "M001-R6S5");
            System.out.println("Is seat M001-R6S5 still available? " + isAvailable);

            // Step 7: Demonstrate another booking
            System.out.println("\n\n--- Making Another Booking ---");
            User anotherUser = new User("U002", "Jane Smith", "jane@email.com", "+1234567891");
            Ticket anotherTicket = facade.bookTicket(
                    anotherUser,
                    "M002",
                    "M002-R4S8", // Premium seat
                    PaymentMethod.PAYPAL
            );
            displayTicketDetails(anotherTicket);

        } catch (Exception e) {
            System.err.println("Booking Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Display available movies using Facade.
     */
    private static void displayAvailableMovies(CinemaTicketBookingFacade facade) {
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│  AVAILABLE MOVIES                           │");
        System.out.println("└─────────────────────────────────────────────┘");
        
        List<Movie> movies = facade.getAvailableMovies();
        for (Movie movie : movies) {
            System.out.println("\n🎬 " + movie.getTitle());
            System.out.println("   ID: " + movie.getMovieId());
            System.out.println("   Genre: " + movie.getGenre());
            System.out.println("   Duration: " + movie.getDurationMinutes() + " minutes");
            System.out.println("   Show Time: " + movie.getShowTime());
            System.out.println("   Hall: " + movie.getHallNumber());
        }
        System.out.println();
    }

    /**
     * Display available seats using Facade.
     */
    private static void displayAvailableSeats(CinemaTicketBookingFacade facade, String movieId) {
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│  AVAILABLE SEATS FOR MOVIE: " + movieId + "        │");
        System.out.println("└─────────────────────────────────────────────┘");
        
        List<Seat> seats = facade.getAvailableSeats(movieId);
        
        // Group by seat type for better display
        long standardCount = seats.stream().filter(s -> s.getSeatType() == SeatType.STANDARD).count();
        long premiumCount = seats.stream().filter(s -> s.getSeatType() == SeatType.PREMIUM).count();
        long vipCount = seats.stream().filter(s -> s.getSeatType() == SeatType.VIP).count();
        
        System.out.println("📍 Standard Seats: " + standardCount + " available ($10.00)");
        System.out.println("📍 Premium Seats: " + premiumCount + " available ($15.00)");
        System.out.println("📍 VIP Seats: " + vipCount + " available ($20.00)");
        System.out.println("Total Available: " + seats.size());
    }

    /**
     * Create a sample user for demonstration.
     */
    private static User createSampleUser() {
        return new User("U001", "John Doe", "john.doe@email.com", "+1234567890");
    }

    /**
     * Display ticket details in a formatted way.
     */
    private static void displayTicketDetails(Ticket ticket) {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║           TICKET DETAILS                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.println("🎫 Ticket ID: " + ticket.getTicketId());
        System.out.println("👤 Customer: " + ticket.getUser().getName());
        System.out.println("🎬 Movie: " + ticket.getMovie().getTitle());
        System.out.println("💺 Seat: " + ticket.getSeat().getSeatId() + " (" + ticket.getSeat().getSeatType() + ")");
        System.out.println("💰 Price: $" + ticket.getPrice());
        System.out.println("🕐 Booking Time: " + ticket.getBookingTime());
        System.out.println("🎭 Show Time: " + ticket.getMovie().getShowTime());
        System.out.println("═══════════════════════════════════════════════════");
    }


}
