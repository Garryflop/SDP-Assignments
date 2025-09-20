package Assignment2.logistics.factories;

import Assignment2.logistics.transport.Transport;
import Assignment2.logistics.transport.Airplane;

public class AirLogistics extends LogisticsCompany {

    public AirLogistics(String companyName, String headquarters) {
        super(companyName, headquarters);
    }

    @Override
    public Transport createTransport(String transportId, double capacity) {
        // Business logic for airplane creation
        String flightCode = "FL" + transportId.substring(transportId.length() - 3);
        double maxAltitude = capacity > 5000 ? 35000 : 25000; // Higher altitude for larger planes

        return new Airplane(transportId, capacity, flightCode, maxAltitude);
    }

    @Override
    protected String getSpecialization() {
        return "Air cargo and express delivery services";
    }
}