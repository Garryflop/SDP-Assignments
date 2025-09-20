package Assignment2.logistics;

import Assignment2.logistics.factories.*;
import Assignment2.logistics.gui.factories.*;
import Assignment2.logistics.gui.components.*;
import Assignment2.logistics.transport.Transport;

public class MyLogisticsApplication {

    public static void main(String[] args) {
        System.out.println("🚚 LOGISTICS MANAGEMENT SYSTEM 🚚");
        System.out.println("=====================================\n");

        // Demonstrate Factory Method Pattern
        demonstrateFactoryMethod();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Demonstrate Abstract Factory Pattern
        demonstrateAbstractFactory();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Demonstrate integrated usage
        demonstrateIntegratedSystem();
    }

    private static void demonstrateFactoryMethod() {
        System.out.println("🏭 FACTORY METHOD PATTERN DEMONSTRATION");
        System.out.println("---------------------------------------\n");

        // Create different logistics companies
        LogisticsCompany roadCompany = new RoadLogistics("FastTrack Trucking", "Chicago");
        LogisticsCompany seaCompany = new SeaLogistics("Global Shipping Co", "Rotterdam");
        LogisticsCompany airCompany = new AirLogistics("SkyExpress Cargo", "Dubai");

        // Display company information
        System.out.println("📊 Logistics Companies:");
        System.out.println("• " + roadCompany.getCompanyInfo());
        System.out.println("• " + seaCompany.getCompanyInfo());
        System.out.println("• " + airCompany.getCompanyInfo());
        System.out.println();

        // Process shipments using different companies
        System.out.println("📦 Processing Shipments:");
        System.out.println();

        // Road shipment
        System.out.println(roadCompany.processShipment("TRK001", 8000, "New York", 7500));
        System.out.println();

        // Sea shipment
        System.out.println(seaCompany.processShipment("SHP001", 50000, "Hamburg", 45000));
        System.out.println();

        // Air shipment
        System.out.println(airCompany.processShipment("AIR001", 3000, "Tokyo", 2500));
    }

    private static void demonstrateAbstractFactory() {
        System.out.println("🖥️ ABSTRACT FACTORY PATTERN DEMONSTRATION");
        System.out.println("------------------------------------------\n");

        // Demonstrate Windows GUI
        System.out.println("🖼️ Creating Windows GUI:");
        createAndShowGUI(new WindowsGUIFactory());

        System.out.println("\n" + "-".repeat(40) + "\n");

        // Demonstrate Mac GUI
        System.out.println("🍎 Creating Mac GUI:");
        createAndShowGUI(new MacGUIFactory());
    }

    private static void createAndShowGUI(GUIFactory factory) {
        System.out.println("Theme: " + factory.getThemeName());
        System.out.println();

        // Create GUI components
        Button submitButton = factory.createButton("Submit Shipment");
        Button cancelButton = factory.createButton("Cancel");
        TextField destinationField = factory.createTextField("Enter destination...");
        TextField weightField = factory.createTextField("Enter weight (kg)...");
        Panel mainPanel = factory.createPanel("Shipment Details");

        // Set some values
        destinationField.setValue("London");
        weightField.setValue("1500");

        // Add components to panel
        mainPanel.addComponent(destinationField.render());
        mainPanel.addComponent(weightField.render());
        mainPanel.addComponent(submitButton.render());
        mainPanel.addComponent(cancelButton.render());

        // Display the GUI
        System.out.println(mainPanel.render());
        System.out.println();

        // Simulate interactions
        System.out.println("💡 User Interactions:");
        System.out.println("• " + submitButton.onClick());
        System.out.println("• Destination: " + destinationField.getValue());
        System.out.println("• Weight: " + weightField.getValue());
    }

    private static void demonstrateIntegratedSystem() {
        System.out.println("🔗 INTEGRATED SYSTEM DEMONSTRATION");
        System.out.println("-----------------------------------\n");

        // Create logistics system with GUI
        LogisticsCompany company = new RoadLogistics("Integrated Logistics", "Berlin");
        GUIFactory guiFactory = determineGUIFactory();

        System.out.println("🏢 Company: " + company.getCompanyInfo());
        System.out.println("🖥️ GUI Theme: " + guiFactory.getThemeName());
        System.out.println();

        // Create shipment form
        Panel shipmentForm = guiFactory.createPanel("New Shipment Form");
        TextField idField = guiFactory.createTextField("Transport ID...");
        TextField capacityField = guiFactory.createTextField("Capacity (kg)...");
        TextField destinationField = guiFactory.createTextField("Destination...");
        TextField weightField = guiFactory.createTextField("Weight (kg)...");
        Button processButton = guiFactory.createButton("Process Shipment");

        // Fill form
        idField.setValue("INT001");
        capacityField.setValue("10000");
        destinationField.setValue("Paris");
        weightField.setValue("8500");

        // Build form
        shipmentForm.addComponent(idField.render());
        shipmentForm.addComponent(capacityField.render());
        shipmentForm.addComponent(destinationField.render());
        shipmentForm.addComponent(weightField.render());
        shipmentForm.addComponent(processButton.render());

        System.out.println("📋 Shipment Form:");
        System.out.println(shipmentForm.render());
        System.out.println();

        // Process the shipment
        System.out.println("⚡ Processing shipment...");
        String result = company.processShipment(
                idField.getValue(),
                Double.parseDouble(capacityField.getValue()),
                destinationField.getValue(),
                Double.parseDouble(weightField.getValue())
        );

        System.out.println(result);
        System.out.println();
        System.out.println("✅ Integration successful!");
    }

    // Simulates OS detection for GUI factory selection
    private static GUIFactory determineGUIFactory() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            return new MacGUIFactory();
        } else {
            return new WindowsGUIFactory(); // Default to Windows
        }
    }
}