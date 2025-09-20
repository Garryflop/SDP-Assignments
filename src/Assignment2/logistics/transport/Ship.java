package Assignment2.logistics.transport;

public class Ship extends Transport {
    private final String portRegistration;
    private final int containerCapacity;

    public Ship(String transportId, double capacity, String portRegistration, int containerCapacity) {
        super(transportId, capacity);
        this.portRegistration = validatePortRegistration(portRegistration);
        this.containerCapacity = validateContainerCapacity(containerCapacity);
    }

    @Override
    public String deliver(String destination, double weight) {
        int containersNeeded = (int) Math.ceil(weight / 1000.0); // 1 container = 1000kg
        return String.format("🚢 Ship %s delivering %.1f kg to %s using %d containers",
                transportId, weight, destination, containersNeeded);
    }

    @Override
    protected String planRoute(String destination) {
        String baseRoute = super.planRoute(destination);
        return baseRoute + " (Maritime route via shipping lanes)";
    }

    private String validatePortRegistration(String portRegistration) {
        if (portRegistration == null || portRegistration.trim().isEmpty()) {
            throw new IllegalArgumentException("Port registration cannot be null or empty");
        }
        return portRegistration.trim();
    }

    private int validateContainerCapacity(int containerCapacity) {
        if (containerCapacity <= 0) {
            throw new IllegalArgumentException("Container capacity must be positive");
        }
        return containerCapacity;
    }

    // Getters
    public String getPortRegistration() { return portRegistration; }
    public int getContainerCapacity() { return containerCapacity; }
}