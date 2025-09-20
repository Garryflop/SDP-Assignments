package Assignment2.logistics.gui.factories;

import Assignment2.logistics.gui.components.*;
import Assignment2.logistics.gui.components.mac.*;

public class MacGUIFactory implements GUIFactory {

    @Override
    public Button createButton(String label) {
        return new MacButton(label);
    }

    @Override
    public TextField createTextField(String placeholder) {
        return new MacTextField(placeholder);
    }

    @Override
    public Panel createPanel(String title) {
        return new MacPanel(title);
    }

    @Override
    public String getThemeName() {
        return "Mac Theme";
    }
}