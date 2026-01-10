public class RegularCustomer extends Customer {
    private int totalPurchases;

    public RegularCustomer(int customerId, String name, String preferredSize, int points, int totalPurchases) {
        super(customerId, name, preferredSize, points);
        this.totalPurchases = totalPurchases;
    }

    public int getTotalPurchases() {
        return totalPurchases;
    }

    public void setTotalPurchases(int totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    @Override
    public void shop() {
        System.out.println("Regular customer " + name + " is browsing.");
    }

    @Override
    public String getCustomerType() {
        return "Regular Customer";
    }

    @Override
    public double getDiscount() {
        if (totalPurchases >= 5) {
            return 5.0;
        }
        return 0.0;
    }

    public void makePurchase(double amount) {
        totalPurchases++;
        int earnedPoints = (int)(amount / 1000);
        addPoints(earnedPoints);
        System.out.println(name + " made purchase #" + totalPurchases);
    }

    public boolean isFrequentShopper() {
        return totalPurchases >= 10;
    }

    @Override
    public String toString() {
        return super.toString() + " | Purchases: " + totalPurchases;
    }
}

