public class Jacket extends ClothingItem {
    // Additional fields specific to Jacket
    private String material;
    private boolean waterproof;

    public Jacket(int itemId, String name, String size, double price, String material, boolean waterproof) {
        super(itemId, name, size, price);
        this.material = material;
        this.waterproof = waterproof;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (material != null && !material.trim().isEmpty()) {
            this.material = material;
        }
    }

    public boolean isWaterproof() {
        return waterproof;
    }

    public void setWaterproof(boolean waterproof) {
        this.waterproof = waterproof;
    }

    @Override
    public void displayInfo() {
        String wpInfo = waterproof ? "waterproof" : "standard";
        System.out.println("Jacket '" + name + "' made of " + material + " (" + wpInfo + ") is showcased.");
    }

    @Override
    public String getCategory() {
        return "Jacket";
    }

    @Override
    public void careInstructions() {
        if (material.equalsIgnoreCase("Leather")) {
            System.out.println("Jacket care: Professional leather cleaning only, condition regularly.");
        } else if (material.equalsIgnoreCase("Wool")) {
            System.out.println("Jacket care: Dry clean only, store with moth protection.");
        } else {
            System.out.println("Jacket care: Machine wash gentle, air dry, avoid high heat.");
        }
    }

    public void checkWeatherSuitability(String weather) {
        if (weather.equalsIgnoreCase("rainy") && waterproof) {
            System.out.println(name + " is perfect for rainy weather!");
        } else if (weather.equalsIgnoreCase("rainy") && !waterproof) {
            System.out.println(name + " is NOT suitable for rain. Consider waterproof options.");
        } else if (weather.equalsIgnoreCase("cold")) {
            System.out.println(name + " (" + material + ") will keep you warm!");
        } else {
            System.out.println(name + " can be worn in " + weather + " weather.");
        }
    }

    public boolean isWinterJacket() {
        return (material.equalsIgnoreCase("Wool") || material.equalsIgnoreCase("Synthetic"))
                && price >= 40000.0;
    }

    @Override
    public String toString() {
        String wpStatus = waterproof ? "Waterproof" : "Not Waterproof";
        return super.toString() + " | Material: " + material + " | " + wpStatus;
    }
}