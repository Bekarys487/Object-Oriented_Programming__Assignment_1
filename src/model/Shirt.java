package model;

public class Shirt extends ClothingItem {
    public Shirt(int id, String name, String size, double price, int stock) {
        super(id, name, size, price, stock);
    }
    @Override
    public String getType() { return "Shirt"; }
}
