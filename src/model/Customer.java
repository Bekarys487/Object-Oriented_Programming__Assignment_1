package model;

public class Customer {
    private String name;

    public Customer(String name) {
        setName(name);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Customer name cannot be empty");
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }
}
