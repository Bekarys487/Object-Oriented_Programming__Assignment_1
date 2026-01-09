public class Customer {
    protected int customerId;
    protected String name;
    protected String preferredSize;
    protected int points;

    public Customer(int customerId, String name, String preferredSize, int points) {
        this.customerId = customerId;
        this.name = name;
        this.preferredSize = preferredSize;
        setPoints(points);
    }

    public Customer() {
        this.customerId = 0;
        this.name = "Unknown customer";
        this.preferredSize = "M";
        this.points = 0;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPreferredSize() {
        return preferredSize;
    }

    public int getPoints() {
        return points;
    }

    public void setCustomerId(int customerId) {
        if (customerId > 0) {
            this.customerId = customerId;
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setPreferredSize(String preferredSize) {
        if (preferredSize != null && !preferredSize.trim().isEmpty()) {
            this.preferredSize = preferredSize;
        }
    }

    public void setPoints(int points) {
        if (points >= 0) {
            this.points = points;
        }
    }

    public void shop() {
        System.out.println(name + " is shopping in the store.");
    }

    public String getCustomerType() {
        return "Regular Customer";
    }

    public double getDiscount() {
        return 0.0;
    }

    public void addPoints(int amount) {
        if (amount > 0) {
            points += amount;
        }
    }

    public boolean isVIP() {
        return points >= 100;
    }

    @Override
    public String toString() {
        return "[" + getCustomerType() + "] " + name +
                " (ID: " + customerId +
                ", Size: " + preferredSize +
                ", Points: " + points + ")";
    }
}