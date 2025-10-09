package Assignment4.com.restaurant.bridge.abstraction;


import Assignment4.com.restaurant.bridge.implementation.interfaces.CookingMethod;

/**
 * Refined Abstraction for Vegetarian Pizza.
 * Healthy option loaded with fresh vegetables.
 * Clean Code: Demonstrates extensibility without modifying parent class
 */
public class VegetarianPizza extends Pizza {

    private static final int VEGGIE_TEMP = 220;
    private static final int VEGGIE_DURATION = 14;

    public VegetarianPizza(CookingMethod cookingMethod) {
        super(cookingMethod);
        this.name = "Vegetarian Pizza";
        this.cookingTemperature = VEGGIE_TEMP;
        this.cookingDuration = VEGGIE_DURATION;
    }

    @Override
    protected String prepareToppings() {
        return "🥬 Preparing Vegetarian Pizza:\n" +
                "  - Preparing thin-crust dough base\n" +
                "  - Spreading herb-infused tomato sauce\n" +
                "  - Adding mozzarella and feta cheese blend\n" +
                "  - Topping with bell peppers (red, yellow, green)\n" +
                "  - Adding mushrooms, red onions, and black olives\n" +
                "  - Finishing with fresh spinach and cherry tomatoes\n" +
                "  - Garnishing with dried oregano";
    }
}