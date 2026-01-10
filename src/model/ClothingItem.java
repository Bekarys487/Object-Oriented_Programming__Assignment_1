package model;

import exception.InvalidInputException;

public abstract class ClothingItem implements Discountable {

    private int id;
    private String name;
    private String size;
    private double price;
    private int stock;

    public ClothingItem(int id, String name, String size, double price, int stock) {
        setId(id);
        setName(name);
        setSize(size);
        setPrice(price);
        setStock(stock);
    }

    public abstract String getType();

    @Override
    public double applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Percent must be between 0 and 100");
        }
        return price * (1 - percent / 100.0);
    }

    public void reduceStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }
        if (quantity > stock) {
            throw new InvalidInputException("Not enough stock!");
        }
        stock -= quantity;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be > 0");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name.trim();
    }

    public void setSize(String size) {
        if (size == null || size.trim().isEmpty())
            throw new IllegalArgumentException("Size cannot be empty");
        this.size = size.trim().toUpperCase();
    }

    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Price must be > 0");
        this.price = price;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSize() { return size; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return "[" + id + "] " + getType() + " | " + name +
                " | Size: " + size +
                " | Price: " + price +
                " | Stock: " + stock;
    }
}
