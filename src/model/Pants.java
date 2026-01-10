package model;

public class Pants extends ClothingItem {

    public Pants(int id, String name, String size, double price, int stock) {
        super(id, name, size, price, stock);
    }

    @Override
    public String getType() {
        return "Pants";
    }
}
