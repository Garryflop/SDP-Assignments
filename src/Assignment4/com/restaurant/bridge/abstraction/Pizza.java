package Assignment4.com.restaurant.bridge.abstraction;

import Assignment4.com.restaurant.bridge.implementation.interfaces.CookingMethod;

/**
 * Abstraction class representing a Pizza.
 * Provides high-level operations while delegating actual cooking to implementation.
 * <p>
 * This is the BRIDGE - it connects the pizza abstraction with cooking method implementation.
 * <p>
 * Clean Code Principles:
 * - Dependency Injection: CookingMethod injected via constructor
 * - Open/Closed Principle: Open for extension (subclasses), closed for modification
 * - Meaningful names that express intent
 */

public abstract class Pizza {
    // Bridge to implementation
    protected CookingMethod cookingMethod;
    protected String name;
    protected int cookingTemperature;
    protected int cookingDuration;
    /**
     * Constructor with dependency injection.
     */
    public Pizza(CookingMethod cookingMethod) {
        if (cookingMethod == null){
            throw new NullPointerException("CookingMethod is null");
        }
        this.cookingMethod = cookingMethod;
    }

    public final String prepare() {

        return prepareToppings() + "\n\n" +
                cookingMethod.preheat() + "\n\n" +
                cookingMethod.cook(name, cookingTemperature, cookingDuration) + "\n\n" +
                cookingMethod.finish() + "\n\n" +
                servePresentation();
    }

    protected abstract String prepareToppings();

    protected String servePresentation(){
        return String.format("🍕 Your %s is ready! Prepared using %s.\nBuon appetito!", name, cookingMethod.getMethodName());
    }

    public void setCookingMethod(CookingMethod cookingMethod) {
        if (cookingMethod == null) {
            throw new IllegalArgumentException("Cooking method cannot be null");
        }
        this.cookingMethod = cookingMethod;
    }

    public String getName() {
        return name;
    }
}
