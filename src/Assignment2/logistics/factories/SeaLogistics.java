package Assignment2.logistics.factories;

import Assignment2.logistics.transport.Transport;
import Assignment2.logistics.transport.Ship;
//Concrete Factory
public class SeaLogistics extends LogisticsCompany {

    public SeaLogistics(String companyName, String headquarters) {
        super(companyName, headquarters);
    }

    @Override
    public Transport createTransport(String transportId, double capacity) {
        String portRegistration = "INT-" + transportId.substring(0, 3).toUpperCase();
        int containerCapacity = (int) (capacity / 1000); // 1 container = 1000kg

        return new Ship(transportId, capacity, portRegistration, containerCapacity);
    }

    @Override
    protected String getSpecialization() {
        return "Maritime shipping and international cargo";
    }
}
