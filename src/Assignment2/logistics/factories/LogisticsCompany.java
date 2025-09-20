package Assignment2.logistics.factories;

import Assignment2.logistics.transport.Transport;

public abstract class LogisticsCompany {
    protected final String companyName;
    protected final String headquarters;

    public LogisticsCompany(String companyName, String headquarters) {
        this.companyName = validateCompanyName(companyName);
        this.headquarters = validateHeadquarters(headquarters);
    }

    // Factory Method - to be implemented by concrete creators
    public abstract Transport createTransport(String transportId, double capacity);

    // Business logic that uses the factory method
    public String processShipment(String transportId, double capacity, String destination, double weight) {
        Transport transport = createTransport(transportId, capacity);
        String deliveryResult = transport.executeDelivery(destination, weight);

        return String.format("🏢 %s processed shipment:\n%s\n%s",
                companyName, transport.toString(), deliveryResult);
    }

    // Template method for company operations
    public final String getCompanyInfo() {
        return String.format("Company: %s | HQ: %s | Specializes in: %s",
                companyName, headquarters, getSpecialization());
    }

    // Hook method
    protected abstract String getSpecialization();

    private String validateCompanyName(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be null or empty");
        }
        return companyName.trim();
    }

    private String validateHeadquarters(String headquarters) {
        if (headquarters == null || headquarters.trim().isEmpty()) {
            throw new IllegalArgumentException("Headquarters cannot be null or empty");
        }
        return headquarters.trim();
    }

    // Getters
    public String getCompanyName() { return companyName; }
    public String getHeadquarters() { return headquarters; }
}
