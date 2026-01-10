public class Shirt extends ClothingItem {
    private String sleeveType;

    public Shirt(int itemId, String name, String size, double price, String sleeveType) {
        super(itemId, name, size, price);
        this.sleeveType = sleeveType;
    }

    public String getSleeveType() {
        return sleeveType;
    }

    public void setSleeveType(String sleeveType) {
        this.sleeveType = sleeveType;
    }

    @Override
    public void displayInfo() {
        System.out.println("Shirt '" + name + "' with " + sleeveType + " sleeves is on display.");
    }

    @Override
    public String getCategory() {
        return "Shirt";
    }

    @Override
    public void careInstructions() {
        System.out.println("Shirt care: Machine wash cold, tumble dry low.");
    }

    public void tryOn() {
        System.out.println("Customer is trying on " + name);
    }

    public boolean isCasual() {
        return sleeveType.equalsIgnoreCase("Short") && price < 15000.0;
    }

    @Override
    public String toString() {
        return super.toString() + " | Sleeve: " + sleeveType;
    }
}