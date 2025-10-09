package Assignment4.com.restaurant.bridge.abstraction;

import Assignment4.com.restaurant.bridge.implementation.interfaces.CookingMethod;

/**
 * Refined Abstraction for Margherita Pizza.
 * Classic Italian pizza with simple, fresh ingredients.
 * <p>
 * Clean Code Principles:
 * - Single Responsibility: Only knows about Margherita-specific details
 * - Inheritance used appropriately for "is-a" relationship
 */
public class MargheritaPizza extends Pizza {

    private static final int MARGHERITA_TEMP = 250;
    private static final int MARGHERITA_DURATION = 12;

    public MargheritaPizza(CookingMethod cookingMethod) {
        super(cookingMethod);
        this.name = "Margherita Pizza";
        this.cookingTemperature = MARGHERITA_TEMP;
        this.cookingDuration = MARGHERITA_DURATION;
    }

    @Override
    protected String prepareToppings() {
        return "🍅 Preparing Margherita Pizza:\n" +
                "  - Stretching fresh dough into 12-inch circle\n" +
                "  - Spreading San Marzano tomato sauce\n" +
                "  - Adding fresh mozzarella di bufala slices\n" +
                "  - Placing fresh basil leaves\n" +
                "  - Drizzling extra virgin olive oil\n" +
                "  - Light sprinkle of sea salt";
    }
}