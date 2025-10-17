package Assignment5.com.cinema.ticketbook.model;

import java.util.Objects;

/**
 * Represents a seat in the cinema hall.
 * Encapsulates seat information and availability status.
 */
public class Seat {
    private final String seatId;
    private final int rowNumber;
    private final int seatNumber;
    private final SeatType seatType;
    private boolean isAvailable;

    public Seat(String seatId, int rowNumber, int seatNumber, SeatType seatType) {
        validateSeatData(seatId, rowNumber, seatNumber, seatType);
        this.seatId = seatId;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.isAvailable = true;
    }

    private void validateSeatData(String seatId, int rowNumber, int seatNumber, SeatType seatType) {
        if (seatId == null || seatId.trim().isEmpty()) {
            throw new IllegalArgumentException("Seat ID cannot be null or empty");
        }
        if (rowNumber <= 0) {
            throw new IllegalArgumentException("Row number must be positive");
        }
        if (seatNumber <= 0) {
            throw new IllegalArgumentException("Seat number must be positive");
        }
        if (seatType == null) {
            throw new IllegalArgumentException("Seat type cannot be null");
        }
    }

    public String getSeatId() {
        return seatId;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPrice() {
        return seatType.getBasePrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(seatId, seat.seatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatId);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId='" + seatId + '\'' +
                ", row=" + rowNumber +
                ", seat=" + seatNumber +
                ", type=" + seatType +
                ", available=" + isAvailable +
                '}';
    }
}
