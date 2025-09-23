package Assignment2.logistics.gui.factories.interfaces;

import Assignment2.logistics.gui.components.interfaces.Button;
import Assignment2.logistics.gui.components.interfaces.Panel;
import Assignment2.logistics.gui.components.interfaces.TextField;
//Abstract Factory
public interface GUIFactory {
    Button createButton(String label);
    TextField createTextField(String placeholder);
    Panel createPanel(String title);
    String getThemeName();
}
