package Assignment2.logistics.gui.components.mac;


import Assignment2.logistics.gui.components.Panel;
import java.util.ArrayList;
import java.util.List;

public class MacPanel implements Panel {
    private final List<String> components = new ArrayList<>();
    private final String title;

    public MacPanel(String title) {
        this.title = title != null ? title : "Panel";
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("🍎 Mac Panel: '%s'\n", title));
        sb.append("╭─ Mac Style Rounded ─╮\n");
        for (String component : components) {
            sb.append(String.format("│ %s\n", component));
        }
        sb.append("╰─────────────────────╯");
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
        return "Mac";
    }
}
