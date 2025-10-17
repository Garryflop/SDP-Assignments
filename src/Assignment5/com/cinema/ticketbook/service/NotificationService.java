package Assignment5.com.cinema.ticketbook.service;

import Assignment5.com.cinema.ticketbook.model.Ticket;
import Assignment5.com.cinema.ticketbook.model.User;

/**
 * Service for sending notifications to users.
 * Complex subsystem that will be hidden behind the Facade.
 */
public class NotificationService {

    /**
     * Send booking confirmation email to user.
     */
    public void sendBookingConfirmation(User user, Ticket ticket) {
        System.out.println("\n[NotificationService] Sending booking confirmation email...");
        System.out.println("To: " + user.getEmail());
        System.out.println("Subject: Booking Confirmation - " + ticket.getMovie().getTitle());
        System.out.println("Dear " + user.getName() + ",");
        System.out.println("Your ticket has been successfully booked!");
        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Movie: " + ticket.getMovie().getTitle());
        System.out.println("Seat: " + ticket.getSeat().getSeatId());
        System.out.println("Show Time: " + ticket.getMovie().getShowTime());
        System.out.println("Amount Paid: $" + ticket.getPrice());
        System.out.println("[NotificationService] Email sent successfully\n");
    }

    /**
     * Send SMS notification to user.
     */
    public void sendSmsNotification(User user, Ticket ticket) {
        System.out.println("[NotificationService] Sending SMS notification...");
        System.out.println("To: " + user.getPhoneNumber());
        System.out.println("Message: Your booking for '" + ticket.getMovie().getTitle() + 
                          "' is confirmed. Ticket ID: " + ticket.getTicketId());
        System.out.println("[NotificationService] SMS sent successfully");
    }

    /**
     * Send reminder notification before show time.
     */
    public void sendShowReminder(User user, Ticket ticket) {
        System.out.println("[NotificationService] Scheduling show reminder...");
        System.out.println("Reminder will be sent to " + user.getEmail() + 
                          " 2 hours before show time");
    }

    /**
     * Send all notifications for a booking.
     */
    public void sendAllNotifications(User user, Ticket ticket) {
        sendBookingConfirmation(user, ticket);
        sendSmsNotification(user, ticket);
        sendShowReminder(user, ticket);
    }
}
