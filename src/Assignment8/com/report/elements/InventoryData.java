package Assignment8.com.report.elements;

import Assignment8.com.report.visitor.ReportVisitor;

//InventoryData - Concrete Element representing inventory information.

public class InventoryData implements ReportElement {

    private final String itemName;
    private final int currentStock;
    private final int reorderLevel;
    private final String warehouseLocation;

    public InventoryData(String itemName, int currentStock, int reorderLevel, String warehouseLocation) {
        this.itemName = itemName;
        this.currentStock = currentStock;
        this.reorderLevel = reorderLevel;
        this.warehouseLocation = warehouseLocation;
    }

    @Override
    public void accept(ReportVisitor visitor) {
        // Double dispatch: delegate to visitor's InventoryData-specific method
        visitor.visitInventoryData(this);
    }

    // Getters for visitor access
    public String getItemName() {
        return itemName;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public boolean needsReorder() {
        return currentStock <= reorderLevel;
    }

    @Override
    public String toString() {
        return String.format("InventoryData{item='%s', stock=%d, reorder=%d, location='%s'}",
                           itemName, currentStock, reorderLevel, warehouseLocation);
    }
}
