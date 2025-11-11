package Assignment8.com.report.visitors;

import Assignment8.com.report.visitor.ReportVisitor;
import Assignment8.com.report.elements.SalesData;
import Assignment8.com.report.elements.InventoryData;
import Assignment8.com.report.elements.EmployeeData;

//TextReportVisitor - Concrete Visitor that generates text-formatted reports.

public class TextReportVisitor implements ReportVisitor {

    private final StringBuilder report;

    public TextReportVisitor() {
        this.report = new StringBuilder();
        report.append("TEXT FORMAT REPORT:\n");
    }

    @Override
    public void visitSalesData(SalesData salesData) {
        report.append("📊 SALES REPORT\n");
        report.append("───────────────────────────────────────────────\n");
        report.append(String.format("  Product Name    : %s\n", salesData.getProductName()));
        report.append(String.format("  Quantity Sold   : %d units\n", salesData.getQuantitySold()));
        report.append(String.format("  Total Revenue   : $%.2f\n", salesData.getTotalRevenue()));
        report.append(String.format("  Sales Date      : %s\n", salesData.getSalesDate()));
        report.append("\n");
    }

    @Override
    public void visitInventoryData(InventoryData inventoryData) {
        report.append("📦 INVENTORY REPORT\n");
        report.append("───────────────────────────────────────────────\n");
        report.append(String.format("  Item Name       : %s\n", inventoryData.getItemName()));
        report.append(String.format("  Current Stock   : %d units\n", inventoryData.getCurrentStock()));
        report.append(String.format("  Reorder Level   : %d units\n", inventoryData.getReorderLevel()));
        report.append(String.format("  Location        : %s\n", inventoryData.getWarehouseLocation()));
        report.append(String.format("  Reorder Needed  : %s\n",
                     inventoryData.needsReorder() ? "⚠️ YES" : "✓ NO"));
        report.append("\n");
    }

    @Override
    public void visitEmployeeData(EmployeeData employeeData) {
        report.append("👤 EMPLOYEE REPORT\n");
        report.append("───────────────────────────────────────────────\n");
        report.append(String.format("  Employee ID     : %s\n", employeeData.getEmployeeId()));
        report.append(String.format("  Full Name       : %s\n", employeeData.getFullName()));
        report.append(String.format("  Department      : %s\n", employeeData.getDepartment()));
        report.append(String.format("  Salary          : $%.2f\n", employeeData.getSalary()));
        report.append(String.format("  Years of Service: %d years\n", employeeData.getYearsOfService()));
        report.append("\n");
    }

    public String getReport() {
        return report.toString();
    }

    public void clearReport() {
        report.setLength(0);
    }
}
