package Assignment4.com.restaurant.bridge.abstraction;

import Assignment4.com.restaurant.bridge.implementation.interfaces.CookingMethod;

/**
 * Refined Abstraction for Pepperoni Pizza.
 * American classic with spicy, savory pepperoni.
 *
 * Clean Code: Consistent structure with other refined abstractions
 */
public class PepperoniPizza extends Pizza {

    private static final int PEPPERONI_TEMP = 230;
    private static final int PEPPERONI_DURATION = 15;

    public PepperoniPizza(CookingMethod cookingMethod) {
        super(cookingMethod);
        this.name = "Pepperoni Pizza";
        this.cookingTemperature = PEPPERONI_TEMP;
        this.cookingDuration = PEPPERONI_DURATION;
    }

    @Override
    protected String prepareToppings() {
        return "🌶️ Preparing Pepperoni Pizza:\n" +
                "  - Rolling out hand-tossed dough\n" +
                "  - Applying rich tomato sauce base\n" +
                "  - Layering whole milk mozzarella\n" +
                "  - Arranging premium pepperoni slices (40+ pieces)\n" +
                "  - Adding Italian seasoning blend\n" +
                "  - Sprinkling grated Parmesan";
    }
}
