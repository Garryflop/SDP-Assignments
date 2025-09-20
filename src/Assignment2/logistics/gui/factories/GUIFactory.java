package Assignment2.logistics.gui.factories;

import Assignment2.logistics.gui.components.*;

public interface GUIFactory {
    Button createButton(String label);
    TextField createTextField(String placeholder);
    Panel createPanel(String title);
    String getThemeName();
}
