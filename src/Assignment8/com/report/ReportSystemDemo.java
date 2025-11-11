package Assignment8.com.report;

import Assignment8.com.report.elements.SalesData;
import Assignment8.com.report.elements.InventoryData;
import Assignment8.com.report.elements.EmployeeData;
import Assignment8.com.report.elements.ReportElement;
import Assignment8.com.report.visitors.TextReportVisitor;
import Assignment8.com.report.visitors.JsonReportVisitor;

import java.util.ArrayList;
import java.util.List;

/// ReportSystemDemo - Main class demonstrating the Visitor pattern.
/// This class shows how the Visitor pattern allows adding new operations
/// (different report formats) to existing element structures without
/// modifying the element classes themselves.
/// Demonstration Flow:
/// 1. Create various data elements (Sales, Inventory, Employee)
/// 2. Store them in a simple collection (Object Structure)
/// 3. Create different visitors (Text, JSON formatters)
/// 4. Apply visitors to each element to generate different report formats

public class ReportSystemDemo {

    public static void main(String[] args) {
        System.out.println("VISITOR PATTERN - REPORT GENERATION SYSTEM");
        System.out.println();

        // Step 1: Create various report elements
        System.out.println("📝 Creating report elements...");
        System.out.println("─────────────────────────────────────────────────────────");

        SalesData sales1 = new SalesData("Gaming Laptop", 45, 67500.00, "2025-10-15");
        SalesData sales2 = new SalesData("Wireless Mouse", 120, 3600.00, "2025-10-20");

        InventoryData inventory1 = new InventoryData("Office Chair", 15, 20, "Warehouse A");
        InventoryData inventory2 = new InventoryData("Standing Desk", 8, 10, "Warehouse B");

        EmployeeData employee1 = new EmployeeData("EMP001", "Jurttyn Balasy",
                                                  "Engineering", 85000.00, 5);
        EmployeeData employee2 = new EmployeeData("EMP002", "Jurttyn Qyzy",
                                                  "Sales", 65000.00, 3);

        // Step 2: Store elements in Object Structure (simple list)
        List<ReportElement> elements = new ArrayList<>();
        elements.add(sales1);
        elements.add(sales2);
        elements.add(inventory1);
        elements.add(inventory2);
        elements.add(employee1);
        elements.add(employee2);

        System.out.println("Created " + elements.size() + " report elements");
        System.out.println();

        // Step 3: Generate TEXT format report using TextReportVisitor
        System.out.println("GENERATING TEXT FORMAT REPORT:");
        System.out.println();

        TextReportVisitor textVisitor = new TextReportVisitor();
        // Apply visitor to each element
        for (ReportElement element : elements) {
            element.accept(textVisitor);  // Visitor pattern in action!
        }
        System.out.println(textVisitor.getReport());

        System.out.println();

        // Step 4: Generate JSON format report using JsonReportVisitor
        System.out.println("GENERATING JSON FORMAT REPORT:");
        System.out.println();

        JsonReportVisitor jsonVisitor = new JsonReportVisitor();
        // Apply same visitor to same elements - different output!
        for (ReportElement element : elements) {
            element.accept(jsonVisitor);  // Same elements, different visitor!
        }
        System.out.println(jsonVisitor.getReport());

        System.out.println();

        // Step 5: Demonstrate extensibility - adding individual elements
        System.out.println("DEMONSTRATING VISITOR EXTENSIBILITY:");
        System.out.println();

        // Create a single new element
        SalesData newSale = new SalesData("Mechanical Keyboard", 75, 11250.00, "2025-11-01");

        // Apply TEXT visitor to just this element
        TextReportVisitor singleTextVisitor = new TextReportVisitor();
        newSale.accept(singleTextVisitor);
        System.out.println("Text format for single element:");
        System.out.println(singleTextVisitor.getReport());

        // Apply JSON visitor to the same element
        JsonReportVisitor singleJsonVisitor = new JsonReportVisitor();
        newSale.accept(singleJsonVisitor);
        System.out.println("JSON format for single element:");
        System.out.println(singleJsonVisitor.getReport());
    }
}
