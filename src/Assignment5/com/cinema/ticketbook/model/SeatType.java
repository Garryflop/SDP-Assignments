package Assignment5.com.cinema.ticketbook.model;

/**
 * Enum representing different types of seats with their base prices.
 * Demonstrates Clean Code principle of using enums for fixed sets of values.
 */
public enum SeatType {
    STANDARD(10.0),
    VIP(20.0),
    PREMIUM(15.0);

    private final double basePrice;

    SeatType(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
