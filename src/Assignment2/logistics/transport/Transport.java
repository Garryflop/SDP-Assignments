package Assignment2.logistics.transport;
//Abstract Product
public abstract class Transport {
    protected String transportId;
    protected double capacity;
    protected String currentLocation;

    public Transport(String transportId, double capacity) {
        this.transportId = validateTransportId(transportId);
        this.capacity = validateCapacity(capacity);
        this.currentLocation = "Warehouse";
    }

    // Template method - defines delivery workflow
    public final String executeDelivery(String destination, double weight) {
        validateDeliveryRequest(destination, weight);
        String route = planRoute(destination);
        String result = deliver(destination, weight);
        updateLocation(destination);
        return String.format("Delivery completed: %s", result);
    }

    // Abstract method to be implemented by concrete products
    public abstract String deliver(String destination, double weight);

    // Hook method - can be overridden
    protected String planRoute(String destination) {
        return String.format("Route planned from %s to %s", currentLocation, destination);
    }

    // Common validation logic
    private String validateTransportId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Transport ID cannot be null or empty");
        }
        return id.trim();
    }

    private double validateCapacity(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        return capacity;
    }

    private void validateDeliveryRequest(String destination, double weight) {
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be null or empty");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        if (weight > capacity) {
            throw new IllegalArgumentException("Weight exceeds transport capacity");
        }
    }

    private void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }

    // Getters
    public String getTransportId() { return transportId; }
    public double getCapacity() { return capacity; }
    public String getCurrentLocation() { return currentLocation; }

    @Override
    public String toString() {
        return String.format("%s{id='%s', capacity=%.1f kg, location='%s'}",
                getClass().getSimpleName(), transportId, capacity, currentLocation);
    }
}