public class Pants extends ClothingItem {
    private String fitType;

    public Pants(int itemId, String name, String size, double price, String fitType) {
        super(itemId, name, size, price);
        this.fitType = fitType;
    }

    public String getFitType() {
        return fitType;
    }

    public void setFitType(String fitType) {
        this.fitType = fitType;
    }

    @Override
    public void displayInfo() {
        System.out.println("Pants '" + name + "' with " + fitType + " fit are featured.");
    }

    @Override
    public String getCategory() {
        return "Pants";
    }

    @Override
    public void careInstructions() {
        System.out.println("Pants care: Machine wash warm, hang to dry.");
    }

    public void checkFit(String customerSize) {
        if (customerSize.equals(size)) {
            System.out.println("Perfect fit!");
        } else {
            System.out.println("Size mismatch.");
        }
    }

    public boolean isFormal() {
        return fitType.equalsIgnoreCase("Slim") && price >= 20000.0;
    }

    @Override
    public String toString() {
        return super.toString() + " | Fit: " + fitType;
    }
}