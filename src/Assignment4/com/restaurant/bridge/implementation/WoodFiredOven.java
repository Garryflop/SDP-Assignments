package Assignment4.com.restaurant.bridge.implementation;

import Assignment4.com.restaurant.bridge.implementation.interfaces.CookingMethod;

/**
 * Concrete implementation for wood-fired oven cooking.
 * Traditional Italian method with high heat and authentic flavor.
 */
public class WoodFiredOven implements CookingMethod {

    private static final String METHOD_NAME = "Wood-Fired Oven";
    private static final int OPTIMAL_TEMPERATURE = 450;

    @Override
    public String preheat() {
        return String.format("[%s] Igniting wood logs and preheating to %d°C. " +
                        "Creating authentic smoky atmosphere...",
                METHOD_NAME, OPTIMAL_TEMPERATURE);
    }

    @Override
    public String cook(String pizzaName, int temperature, int duration) {

        return String.format("[%s] Placing %s on the stone floor.\n",
                METHOD_NAME, pizzaName) +
                String.format("Cooking at %d°C for %d minutes with natural wood flames.\n",
                        temperature, duration) +
                "The pizza develops a crispy, slightly charred crust with smoky notes.";
    }

    @Override
    public String finish() {
        return String.format("[%s] Removing pizza with long-handled peel. " +
                "Letting embers cool naturally.", METHOD_NAME);
    }

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }
}
