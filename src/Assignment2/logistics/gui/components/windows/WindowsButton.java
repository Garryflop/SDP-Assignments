package Assignment2.logistics.gui.components.windows;


import Assignment2.logistics.gui.components.Button;

public class WindowsButton implements Button {
    private final String label;
    private boolean isPressed = false;

    public WindowsButton(String label) {
        this.label = validateLabel(label);
    }

    @Override
    public String render() {
        return String.format("🖼️ Windows Button: [%s] %s",
                label, isPressed ? "(pressed)" : "");
    }

    @Override
    public String onClick() {
        isPressed = !isPressed;
        return String.format("Windows button '%s' clicked with system sound", label);
    }

    @Override
    public String getTheme() {
        return "Windows";
    }

    private String validateLabel(String label) {
        if (label == null || label.trim().isEmpty()) {
            throw new IllegalArgumentException("Button label cannot be null or empty");
        }
        return label.trim();
    }
}
