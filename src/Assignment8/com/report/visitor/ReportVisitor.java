package Assignment8.com.report.visitor;

import Assignment8.com.report.elements.SalesData;
import Assignment8.com.report.elements.InventoryData;
import Assignment8.com.report.elements.EmployeeData;

//Visitor interface - defines visit operations for each element type.

public interface ReportVisitor {
    void visitSalesData(SalesData salesData);
    void visitInventoryData(InventoryData inventoryData);
    void visitEmployeeData(EmployeeData employeeData);
}
