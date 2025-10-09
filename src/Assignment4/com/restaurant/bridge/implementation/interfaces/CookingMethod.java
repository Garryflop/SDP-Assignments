package Assignment4.com.restaurant.bridge.implementation.interfaces;

public interface CookingMethod {
    String preheat();
    String cook(String foodName, int temperature, int duration);
    String finish();
    String getMethodName();
}
