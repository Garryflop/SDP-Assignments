package Assignment2.logistics.gui.factories;

import Assignment2.logistics.gui.components.*;
import Assignment2.logistics.gui.components.windows.*;

public class WindowsGUIFactory implements GUIFactory {

    @Override
    public Button createButton(String label) {
        return new WindowsButton(label);
    }

    @Override
    public TextField createTextField(String placeholder) {
        return new WindowsTextField(placeholder);
    }

    @Override
    public Panel createPanel(String title) {
        return new WindowsPanel(title);
    }

    @Override
    public String getThemeName() {
        return "Windows Theme";
    }
}