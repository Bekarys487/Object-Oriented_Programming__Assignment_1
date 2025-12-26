public class ClothingItem {
    private int itemId;
    private String name;
    private String size;
    private double price;


    public ClothingItem(int itemId, String name, String size, double price) {
        this.itemId = itemId;
        this.name = name;
        this.size = size;
        this.price = price;
    }


    public ClothingItem() {
        this.itemId = 0;
        this.name = "Unknown item";
        this.size = "M";
        this.price = 0.0;
    }




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




    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public void setPrice(double price) {
        this.price = price;
    }



    public void applyDiscount(double percentage) {
        double discount = price * (percentage / 100.0);
        price = price - discount;
    }
    public boolean isPremium() {
        return price >= 30000.0;
    }




    @Override
    public String toString() {
        return "ClothingItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                '}';
    }
}