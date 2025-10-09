package Assignment4.com.restaurant.bridge.implementation;

import Assignment4.com.restaurant.bridge.implementation.interfaces.CookingMethod;

/**
 * Concrete implementation for electric oven cooking.
 * Modern, precise temperature control with consistent results.
 */
public class ElectricOven implements CookingMethod {

    private static final String METHOD_NAME = "Electric Oven";
    private static final int DEFAULT_TEMPERATURE = 220;

    @Override
    public String preheat() {
        return String.format("[%s] Setting digital controls to %d°C. " +
                        "Heating elements warming up with precision temperature control...",
                METHOD_NAME, DEFAULT_TEMPERATURE);
    }

    @Override
    public String cook(String pizzaName, int temperature, int duration) {

        return String.format("[%s] Loading %s onto middle rack.\n",
                METHOD_NAME, pizzaName) +
                String.format("Baking at precisely %d°C for %d minutes.\n",
                        temperature, duration) +
                "Even heat distribution ensures consistent golden-brown crust.";
    }

    @Override
    public String finish() {
        return String.format("[%s] Timer complete. Removing pizza safely. " +
                "Oven shutting down automatically.", METHOD_NAME);
    }

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }
}
