package model;

public class OrderLine {
    private String itemName;
    private String itemType;
    private double unitPrice;
    private int quantity;

    public OrderLine(String itemType, String itemName, double unitPrice, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        this.itemType = itemType;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public double getTotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return itemType + " " + itemName + " x" + quantity + " = " + getTotal();
    }
}