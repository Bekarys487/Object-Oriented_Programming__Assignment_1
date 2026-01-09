public class ClothingItem {
    // Protected fields - accessible in child classes
    protected int itemId;
    protected String name;
    protected String size;
    protected double price;

    // Parameterized constructor
    public ClothingItem(int itemId, String name, String size, double price) {
        this.itemId = itemId;
        this.name = name;
        this.size = size;
        setPrice(price); // Use setter for validation
    }

    // Default constructor
    public ClothingItem() {
        this.itemId = 0;
        this.name = "Unknown item";
        this.size = "M";
        this.price = 0.0;
    }

    // Getters
    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    // Setters with validation
    public void setItemId(int itemId) {
        if (itemId > 0) {
            this.itemId = itemId;
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setSize(String size) {
        if (size != null && !size.trim().isEmpty()) {
            this.size = size;
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    // Method to be overridden - displays item info
    public void displayInfo() {
        System.out.println(name + " is displayed in the store.");
    }

    // Method to be overridden - returns category
    public String getCategory() {
        return "General Clothing";
    }

    // Method to be overridden - care instructions
    public void careInstructions() {
        System.out.println("General care: Follow tag instructions.");
    }

    // Regular method - applies discount
    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= 100) {
            double discount = price * (percentage / 100.0);
            price = price - discount;
        }
    }

    // Regular method - checks if premium
    public boolean isPremium() {
        return price >= 30000.0;
    }

    @Override
    public String toString() {
        return "[" + getCategory() + "] " + name +
                " (ID: " + itemId + ", Size: " + size +
                ", Price: " + price + " KZT)";
    }
}