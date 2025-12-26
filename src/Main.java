public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the clothing store Beka !");
        System.out.println();

        ClothingItem item1 = new ClothingItem(1, "Winter Jacket", "L", 45000.0);
        ClothingItem item2 = new ClothingItem(2, "T-Shirt", "M", 8000.0);
        ClothingItem item3 = new ClothingItem(); // default

        Customer customer1 = new Customer(1001, "Alikhan", "M", 50);
        Customer customer2 = new Customer(1002, "Baktiyar", "L", 120);

        Order order1 = new Order(5001, "Alikhan", 0.0, "Pending");
        Order order2 = new Order(5002, "Baktiyar", 20000.0, "Pending");


        System.out.println("--- INITIAL CLOTHING ITEMS ---");
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);
        System.out.println();

        System.out.println("--- INITIAL CUSTOMERS ---");
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println();

        System.out.println("--- INITIAL ORDERS ---");
        System.out.println(order1);
        System.out.println(order2);
        System.out.println();


        System.out.println("--- TESTING GETTERS ---");
        System.out.println("Item1 name: " + item1.getName());
        System.out.println("Item1 price: " + item1.getPrice());
        System.out.println("Customer1 points: " + customer1.getPoints());
        System.out.println("Order2 status: " + order2.getStatus());
        System.out.println();


        System.out.println("--- TESTING SETTERS ---");
        System.out.println("Updating item3...");
        item3.setItemId(3);
        item3.setName("Jeans");
        item3.setSize("S");
        item3.setPrice(15000.0);
        System.out.println("Updated item3: " + item3);
        System.out.println();

        System.out.println("Changing customer1 name and points...");
        customer1.setName("Ongraov Alikhan");
        customer1.setPoints(90);
        System.out.println("Updated customer1: " + customer1);
        System.out.println();

        System.out.println("Changing order1 customer and total...");
        order1.setCustomerName("Ongraov Alikhan");
        order1.setTotal(10000.0);
        System.out.println("Updated order1: " + order1);
        System.out.println();



        System.out.println("--- TESTING CLOTHINGITEM METHODS ---");
        System.out.println(item1.getName() + " is premium: " + item1.isPremium());
        System.out.println("Applying 10% discount to " + item1.getName());
        item1.applyDiscount(10);
        System.out.println("New price of " + item1.getName() + ": " + item1.getPrice());
        System.out.println();

        System.out.println("--- TESTING CUSTOMER METHODS ---");
        System.out.println(customer1.getName() + " is VIP: " + customer1.isVIP());
        System.out.println("Adding 20 points to " + customer1.getName());
        customer1.addPoints(20);
        System.out.println(customer1.getName() + " points: " + customer1.getPoints());
        System.out.println(customer1.getName() + " is VIP: " + customer1.isVIP());
        System.out.println();

        System.out.println("--- TESTING ORDER METHODS ---");
        System.out.println("Order1 status: " + order1.getStatus());
        order1.complete();
        System.out.println("Order1 status after complete(): " + order1.getStatus());
        System.out.println();

        System.out.println("Adding 5000 to order2 total...");
        order2.addAmount(5000.0);
        System.out.println("Order2 total: " + order2.getTotal());
        System.out.println("Cancelling order2...");
        order2.cancel();
        System.out.println("Order2 status: " + order2.getStatus());
        System.out.println();



        System.out.println("--- FINAL STATE ---");
        System.out.println("Items:");
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);
        System.out.println();

        System.out.println("Customers:");
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println();

        System.out.println("Orders:");
        System.out.println(order1);
        System.out.println(order2);
        System.out.println();
        System.out.println("Program complete.");
    }
}