package Assignment8.com.report.elements;

import Assignment8.com.report.visitor.ReportVisitor;

//SalesData - Concrete Element representing sales information.
public class SalesData implements ReportElement {

    private final String productName;
    private final int quantitySold;
    private final double totalRevenue;
    private final String salesDate;

    public SalesData(String productName, int quantitySold, double totalRevenue, String salesDate) {
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.totalRevenue = totalRevenue;
        this.salesDate = salesDate;
    }

    @Override
    public void accept(ReportVisitor visitor) {
        // Double dispatch: call the visitor's method specific to SalesData
        visitor.visitSalesData(this);
    }

    // Getters for visitor access
    public String getProductName() {
        return productName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public String getSalesDate() {
        return salesDate;
    }

    @Override
    public String toString() {
        return String.format("SalesData{product='%s', quantity=%d, revenue=$%.2f, date='%s'}",
                           productName, quantitySold, totalRevenue, salesDate);
    }
}
