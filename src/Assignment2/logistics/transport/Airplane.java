package Assignment2.logistics.transport;

public class Airplane extends Transport {
    private final String flightCode;
    private final double maxAltitude;

    public Airplane(String transportId, double capacity, String flightCode, double maxAltitude) {
        super(transportId, capacity);
        this.flightCode = validateFlightCode(flightCode);
        this.maxAltitude = validateMaxAltitude(maxAltitude);
    }

    @Override
    public String deliver(String destination, double weight) {
        return String.format("✈️ Flight %s delivering %.1f kg to %s via air cargo",
                flightCode, weight, destination);
    }

    @Override
    protected String planRoute(String destination) {
        String baseRoute = super.planRoute(destination);
        return baseRoute + String.format(" (Flight path at %.0f ft altitude)", maxAltitude);
    }

    private String validateFlightCode(String flightCode) {
        if (flightCode == null || flightCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Flight code cannot be null or empty");
        }
        return flightCode.trim().toUpperCase();
    }

    private double validateMaxAltitude(double maxAltitude) {
        if (maxAltitude <= 0) {
            throw new IllegalArgumentException("Max altitude must be positive");
        }
        return maxAltitude;
    }

    // Getters
    public String getFlightCode() { return flightCode; }
    public double getMaxAltitude() { return maxAltitude; }
}