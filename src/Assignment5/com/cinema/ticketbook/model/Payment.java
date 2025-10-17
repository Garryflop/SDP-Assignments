package Assignment5.com.cinema.ticketbook.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a payment transaction.
 * Immutable class following Clean Code principles.
 */
public class Payment {
    private final String paymentId;
    private final String ticketId;
    private final double amount;
    private final PaymentMethod paymentMethod;
    private final PaymentStatus status;
    private final LocalDateTime paymentTime;

    public Payment(String paymentId, String ticketId, double amount, PaymentMethod paymentMethod) {
        validatePaymentData(paymentId, ticketId, amount, paymentMethod);
        this.paymentId = paymentId;
        this.ticketId = ticketId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = PaymentStatus.COMPLETED;
        this.paymentTime = LocalDateTime.now();
    }

    private void validatePaymentData(String paymentId, String ticketId, double amount, 
                                     PaymentMethod paymentMethod) {
        if (paymentId == null || paymentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment ID cannot be null or empty");
        }
        if (ticketId == null || ticketId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ticket ID cannot be null or empty");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(paymentId, payment.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", ticketId='" + ticketId + '\'' +
                ", amount=" + amount +
                ", method=" + paymentMethod +
                ", status=" + status +
                ", paymentTime=" + paymentTime +
                '}';
    }
}
