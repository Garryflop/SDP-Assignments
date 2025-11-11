package Assignment8.com.report.visitors;

import Assignment8.com.report.visitor.ReportVisitor;
import Assignment8.com.report.elements.SalesData;
import Assignment8.com.report.elements.InventoryData;
import Assignment8.com.report.elements.EmployeeData;

//JsonReportVisitor - Concrete Visitor that generates JSON-formatted reports.

public class JsonReportVisitor implements ReportVisitor {

    private final StringBuilder jsonReport;
    private boolean isFirstElement;

    public JsonReportVisitor() {
        this.jsonReport = new StringBuilder();
        this.isFirstElement = true;
        jsonReport.append("{\n");
        jsonReport.append("  \"reportFormat\": \"JSON\",\n");
        jsonReport.append("  \"elements\": [\n");
    }

    @Override
    public void visitSalesData(SalesData salesData) {
        addCommaIfNeeded();
        jsonReport.append("    {\n");
        jsonReport.append("      \"type\": \"SalesData\",\n");
        jsonReport.append(String.format("      \"productName\": \"%s\",\n",
                         escapeJson(salesData.getProductName())));
        jsonReport.append(String.format("      \"quantitySold\": %d,\n",
                         salesData.getQuantitySold()));
        jsonReport.append(String.format("      \"totalRevenue\": %.2f,\n",
                         salesData.getTotalRevenue()));
        jsonReport.append(String.format("      \"salesDate\": \"%s\"\n",
                         escapeJson(salesData.getSalesDate())));
        jsonReport.append("    }");
        isFirstElement = false;
    }

    @Override
    public void visitInventoryData(InventoryData inventoryData) {
        addCommaIfNeeded();
        jsonReport.append("    {\n");
        jsonReport.append("      \"type\": \"InventoryData\",\n");
        jsonReport.append(String.format("      \"itemName\": \"%s\",\n",
                         escapeJson(inventoryData.getItemName())));
        jsonReport.append(String.format("      \"currentStock\": %d,\n",
                         inventoryData.getCurrentStock()));
        jsonReport.append(String.format("      \"reorderLevel\": %d,\n",
                         inventoryData.getReorderLevel()));
        jsonReport.append(String.format("      \"warehouseLocation\": \"%s\",\n",
                         escapeJson(inventoryData.getWarehouseLocation())));
        jsonReport.append(String.format("      \"needsReorder\": %s\n",
                         inventoryData.needsReorder()));
        jsonReport.append("    }");
        isFirstElement = false;
    }

    @Override
    public void visitEmployeeData(EmployeeData employeeData) {
        addCommaIfNeeded();
        jsonReport.append("    {\n");
        jsonReport.append("      \"type\": \"EmployeeData\",\n");
        jsonReport.append(String.format("      \"employeeId\": \"%s\",\n",
                         escapeJson(employeeData.getEmployeeId())));
        jsonReport.append(String.format("      \"fullName\": \"%s\",\n",
                         escapeJson(employeeData.getFullName())));
        jsonReport.append(String.format("      \"department\": \"%s\",\n",
                         escapeJson(employeeData.getDepartment())));
        jsonReport.append(String.format("      \"salary\": %.2f,\n",
                         employeeData.getSalary()));
        jsonReport.append(String.format("      \"yearsOfService\": %d\n",
                         employeeData.getYearsOfService()));
        jsonReport.append("    }");
        isFirstElement = false;
    }



    public String getReport() {
        StringBuilder finalReport = new StringBuilder(jsonReport);
        finalReport.append("\n  ]\n");
        finalReport.append("}\n");
        return finalReport.toString();
    }

    private void addCommaIfNeeded() {
        if (!isFirstElement) {
            jsonReport.append(",\n");
        }
    }

    private String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}
