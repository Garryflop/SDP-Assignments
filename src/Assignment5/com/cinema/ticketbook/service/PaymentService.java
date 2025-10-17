package Assignment5.com.cinema.ticketbook.service;

import Assignment5.com.cinema.ticketbook.exception.PaymentFailedException;
import Assignment5.com.cinema.ticketbook.model.Payment;
import Assignment5.com.cinema.ticketbook.model.PaymentMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Service for processing payments.
 * Complex subsystem that will be hidden behind the Facade.
 */
public class PaymentService {
    private final Map<String, Payment> payments;

    public PaymentService() {
        this.payments = new HashMap<>();
    }

    /**
     * Process a payment for a ticket.
     */
    public Payment processPayment(String ticketId, double amount, PaymentMethod paymentMethod) {
        validatePaymentDetails(amount, paymentMethod);
        
        try {
            // Simulate payment processing
            System.out.println("[PaymentService] Processing payment...");
            simulatePaymentGateway(paymentMethod, amount);
            
            String paymentId = generatePaymentId();
            Payment payment = new Payment(paymentId, ticketId, amount, paymentMethod);
            payments.put(paymentId, payment);
            
            System.out.println("[PaymentService] Payment successful: " + paymentId);
            return payment;
            
        } catch (Exception e) {
            throw new PaymentFailedException("Payment processing failed: " + e.getMessage(), e);
        }
    }

    /**
     * Validate payment details before processing.
     */
    private void validatePaymentDetails(double amount, PaymentMethod paymentMethod) {
        if (amount <= 0) {
            throw new PaymentFailedException("Invalid payment amount: " + amount);
        }
        if (paymentMethod == null) {
            throw new PaymentFailedException("Payment method cannot be null");
        }
    }

    /**
     * Simulate payment gateway interaction.
     */
    private void simulatePaymentGateway(PaymentMethod method, double amount) {
        System.out.println("[PaymentService] Connecting to payment gateway for " + method);
        System.out.println("[PaymentService] Processing amount: $" + amount);
        
        // Simulate network delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("[PaymentService] Payment authorized");
    }

    /**
     * Generate a unique payment ID.
     */
    private String generatePaymentId() {
        return "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Get payment details.
     */
    public Payment getPayment(String paymentId) {
        return payments.get(paymentId);
    }

    /**
     * Verify payment completion.
     */
    public boolean verifyPayment(String paymentId) {
        Payment payment = payments.get(paymentId);
        return payment != null && payment.getStatus().name().equals("COMPLETED");
    }
}
