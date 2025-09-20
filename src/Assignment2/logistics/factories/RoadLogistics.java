package Assignment2.logistics.factories;


import Assignment2.logistics.transport.Transport;
import Assignment2.logistics.transport.Truck;

public class RoadLogistics extends LogisticsCompany {

    public RoadLogistics(String companyName, String headquarters) {
        super(companyName, headquarters);
    }

    @Override
    public Transport createTransport(String transportId, double capacity) {
        // Business logic for truck creation
        String licenseType = capacity > 10000 ? "CDL-A" : "CDL-B";
        boolean hasTrailer = capacity > 5000;

        return new Truck(transportId, capacity, licenseType, hasTrailer);
    }

    @Override
    protected String getSpecialization() {
        return "Road transportation and truck logistics";
    }
}
