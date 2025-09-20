package Assignment2.logistics.gui.components.windows;

import Assignment2.logistics.gui.components.Panel;
import java.util.ArrayList;
import java.util.List;

public class WindowsPanel implements Panel {
    private final List<String> components = new ArrayList<>();
    private final String title;

    public WindowsPanel(String title) {
        this.title = title != null ? title : "Panel";
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("🖼️ Windows Panel: '%s'\n", title));
        sb.append("┌─ Windows Style Border ─┐\n");
        for (String component : components) {
            sb.append(String.format("│ %s\n", component));
        }
        sb.append("└─────────────────────────┘");
        return sb.toString();
    }

    @Override
    public void addComponent(String component) {
        if (component != null && !component.trim().isEmpty()) {
            components.add(component.trim());
        }
    }

    @Override
    public String getTheme() {
        return "Windows";
    }
}