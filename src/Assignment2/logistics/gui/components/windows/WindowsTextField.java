package Assignment2.logistics.gui.components.windows;

import Assignment2.logistics.gui.components.TextField;

public class WindowsTextField implements TextField {
    private String value = "";
    private final String placeholder;

    public WindowsTextField(String placeholder) {
        this.placeholder = placeholder != null ? placeholder : "";
    }

    @Override
    public String render() {
        String displayValue = value.isEmpty() ? placeholder : value;
        return String.format("🖼️ Windows TextField: [%s]", displayValue);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value != null ? value : "";
    }

    @Override
    public String getTheme() {
        return "Windows";
    }
}
