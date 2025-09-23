package Assignment2.logistics.gui.components.mac;

import Assignment2.logistics.gui.components.interfaces.Button;
//Concrete Products
public class MacButton implements Button {
    private final String label;
    private boolean isPressed = false;

    public MacButton(String label) {
        this.label = validateLabel(label);
    }

    @Override
    public String render() {
        return String.format("🍎 Mac Button: (%s) %s",
                label, isPressed ? "●" : "○");
    }

    @Override
    public String onClick() {
        isPressed = !isPressed;
        return String.format("Mac button '%s' clicked with elegant animation", label);
    }

    @Override
    public String getTheme() {
        return "Mac";
    }

    private String validateLabel(String label) {
        if (label == null || label.trim().isEmpty()) {
            throw new IllegalArgumentException("Button label cannot be null or empty");
        }
        return label.trim();
    }
}