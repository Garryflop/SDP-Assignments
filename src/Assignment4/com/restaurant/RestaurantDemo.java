package Assignment4.com.restaurant;

import Assignment4.com.restaurant.bridge.abstraction.*;
import Assignment4.com.restaurant.bridge.implementation.*;
import Assignment4.com.restaurant.bridge.implementation.interfaces.CookingMethod;

/**
 * Demonstration of the Bridge Design Pattern in a restaurant pizza system.
 * <p>
 * Bridge Pattern Benefits Demonstrated:
 * 1. Decoupling: Pizza types independent of cooking methods
 * 2. Flexibility: Can combine any pizza with any cooking method
 * 3. Extensibility: Easy to add new pizzas or cooking methods
 * 4. Runtime flexibility: Can change cooking method dynamically
 */
public class RestaurantDemo {

    private static final String SEPARATOR = "=".repeat(80);

    public static void main(String[] args) {
        System.out.println(SEPARATOR);
        System.out.println("🍕 BRIDGE PATTERN DEMO: RESTAURANT PIZZA SYSTEM 🍕");
        System.out.println(SEPARATOR);

        demonstrateBasicBridge();
        demonstrateDifferentCombinations();
        demonstrateDynamicChange();
        demonstrateExtensibility();
    }

    /**
     * Demonstrates basic Bridge pattern usage.
     * Shows how abstraction and implementation are connected.
     */
    private static void demonstrateBasicBridge() {
        System.out.println("\n📌 SCENARIO 1: Basic Bridge Pattern\n");

        CookingMethod woodFired = new WoodFiredOven();
        Pizza margherita = new MargheritaPizza(woodFired);

        System.out.println(margherita.prepare());
        System.out.println("\n" + SEPARATOR);
    }

    /**
     * Demonstrates flexibility of combining different abstractions and implementations.
     * Any pizza can work with any cooking method.
     */
    private static void demonstrateDifferentCombinations() {
        System.out.println("\n📌 SCENARIO 2: Different Pizza-Method Combinations\n");

        // Pepperoni with Electric Oven
        Pizza pepperoni = new PepperoniPizza(new ElectricOven());
        System.out.println(pepperoni.prepare());
        System.out.println("\n" + "-".repeat(80) + "\n");

        // Vegetarian with Convection Oven
        Pizza veggie = new VegetarianPizza(new ConvectionOven());
        System.out.println(veggie.prepare());
        System.out.println("\n" + SEPARATOR);
    }

    /**
     * Demonstrates runtime flexibility.
     * Shows how cooking method can be changed dynamically (Bridge advantage).
     */
    private static void demonstrateDynamicChange() {
        System.out.println("\n📌 SCENARIO 3: Dynamic Method Change (Runtime Flexibility)\n");

        Pizza margherita = new MargheritaPizza(new ElectricOven());
        System.out.println("Initially using Electric Oven:");
        System.out.println(margherita.prepare());

        System.out.println("\n" + "-".repeat(80));
        System.out.println("⚡ Switching to Wood-Fired Oven for authentic taste...\n");
        System.out.println("-".repeat(80) + "\n");

        margherita.setCookingMethod(new WoodFiredOven());
        System.out.println(margherita.prepare());
        System.out.println("\n" + SEPARATOR);
    }

    /**
     * Demonstrates extensibility advantage.
     * Shows how easy it is to add new combinations.
     */
    private static void demonstrateExtensibility() {
        System.out.println("\n📌 SCENARIO 4: Extensibility - All Combinations\n");
        System.out.println("The Bridge pattern makes it easy to combine any pizza with any method:\n");

        Pizza[] pizzas = {
                new MargheritaPizza(new WoodFiredOven()),
                new MargheritaPizza(new ElectricOven()),
                new MargheritaPizza(new ConvectionOven()),
                new PepperoniPizza(new WoodFiredOven()),
                new PepperoniPizza(new ElectricOven()),
                new PepperoniPizza(new ConvectionOven()),
                new VegetarianPizza(new WoodFiredOven()),
                new VegetarianPizza(new ElectricOven()),
                new VegetarianPizza(new ConvectionOven())
        };

        System.out.println("Available combinations:");
        int count = 1;
        for (Pizza pizza : pizzas) {
            System.out.printf("%d. %s + %s%n",
                    count++,
                    pizza.getName(),
                    getCookingMethodName(pizza));
        }

        System.out.println("\n💡 That's " + pizzas.length + " combinations with just 3 pizzas and 3 methods!");
        System.out.println("Without Bridge: Would need " + pizzas.length + " separate classes!");
        System.out.println("With Bridge: Only 3 + 3 = 6 classes needed! 🎉");
        System.out.println("\n" + SEPARATOR);
    }

    /**
     * Helper method to extract cooking method name.
     * Clean Code: Small, focused helper method.
     */
    private static String getCookingMethodName(Pizza pizza) {
        // Using reflection to access the cooking method (for demo purposes)
        try {
            java.lang.reflect.Field field = Pizza.class.getDeclaredField("cookingMethod");
            field.setAccessible(true);
            CookingMethod method = (CookingMethod) field.get(pizza);
            return method.getMethodName();
        } catch (Exception e) {
            return "Unknown Method";
        }
    }
}