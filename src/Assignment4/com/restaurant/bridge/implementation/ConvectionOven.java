package Assignment4.com.restaurant.bridge.implementation;


import Assignment4.com.restaurant.bridge.implementation.interfaces.CookingMethod;

/**
 * Concrete implementation for convection oven cooking.
 * Fast cooking with circulating hot air for efficiency.
 */
public class ConvectionOven implements CookingMethod {

    private static final String METHOD_NAME = "Convection Oven";
    private static final int FAN_SPEED = 3000; // RPM

    @Override
    public String preheat() {
        return String.format("[%s] Starting fan system (%d RPM) and heating to 200°C. " +
                        "Circulating hot air for rapid, even heating...",
                METHOD_NAME, FAN_SPEED);
    }

    @Override
    public String cook(String pizzaName, int temperature, int duration) {

        // Convection cooking is faster - reduce time by 25%
        int adjustedDuration = (int) (duration * 0.75);

        return String.format("[%s] Positioning %s in center of airflow.\n",
                METHOD_NAME, pizzaName) +
                String.format("Cooking at %d°C for %d minutes (reduced from %d due to fan).\n",
                        temperature, adjustedDuration, duration) +
                "Circulating air creates perfectly crispy texture throughout.";
    }

    @Override
    public String finish() {
        return String.format("[%s] Fan slowing down. Pizza ready. " +
                "Residual heat dissipating quickly.", METHOD_NAME);
    }

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }
}