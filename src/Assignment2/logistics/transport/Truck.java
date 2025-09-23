package Assignment2.logistics.transport;
//Concrete Product
public class Truck extends Transport {
    private final String licenseType;
    private final boolean hasTrailer;

    public Truck(String transportId, double capacity, String licenseType, boolean hasTrailer) {
        super(transportId, capacity);
        this.licenseType = validateLicenseType(licenseType);
        this.hasTrailer = hasTrailer;
    }

    @Override
    public String deliver(String destination, double weight) {
        return String.format("🚛 Truck %s delivering %.1f kg to %s via highway",
                transportId, weight, destination);
    }

    @Override
    protected String planRoute(String destination) {
        String baseRoute = super.planRoute(destination);
        return baseRoute + " (Highway route optimized for trucks)";
    }

    private String validateLicenseType(String licenseType) {
        if (licenseType == null || licenseType.trim().isEmpty()) {
            throw new IllegalArgumentException("License type cannot be null or empty");
        }
        return licenseType.trim();
    }

    // Getters
    public String getLicenseType() { return licenseType; }
    public boolean hasTrailer() { return hasTrailer; }
}
