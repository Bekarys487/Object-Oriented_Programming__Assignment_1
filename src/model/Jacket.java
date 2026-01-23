package model;

public class Jacket extends ClothingItem {
    public Jacket(int id, String name, String size, double price, int stock) {
        super(id, name, size, price, stock);
    }
    @Override
    public String getType() { return "Jacket"; }
}
