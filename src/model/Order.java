package model;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private Customer customer;
    private ArrayList<OrderLine> lines = new ArrayList<>();

    public Order(int orderId, Customer customer) {
        if (orderId <= 0) throw new IllegalArgumentException("Order ID must be > 0");
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addLine(OrderLine line) {
        if (line == null) throw new IllegalArgumentException("Line cannot be null");
        lines.add(line);
    }

    public double getTotal() {
        double total = 0;
        for (OrderLine line : lines) {
            total += line.getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderId)
                .append(" | Customer: ").append(customer.getName()).append("\n");

        for (OrderLine line : lines) {
            sb.append("  - ").append(line).append("\n");
        }

        sb.append("Total: ").append(getTotal());
        return sb.toString();
    }
}
