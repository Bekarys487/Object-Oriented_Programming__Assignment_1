import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<ClothingItem> allItems = new ArrayList<>();
    private static ArrayList<Customer> allCustomers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextItemId = 1;
    private static int nextCustomerId = 1001;

    public static void main(String[] args) {
        addInitialData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            System.out.println();

            switch (choice) {
                case 1 -> addGeneralItem();
                case 2 -> addShirt();
                case 3 -> addPants();
                case 4 -> addJacket();
                case 5 -> viewAllItems();
                case 6 -> demonstrateItemPolymorphism();
                case 7 -> viewItemsByCategory();
                case 8 -> addRegularCustomer();
                case 9 -> addVIPCustomer();
                case 10 -> viewAllCustomers();
                case 11 -> demonstrateCustomerPolymorphism();
                case 0 -> {
                    System.out.println("Thank you for using Beka Clothing Store! Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void addInitialData() {
        allItems.add(new ClothingItem(nextItemId++, "Basic T-Shirt", "L", 5000));
        allItems.add(new Shirt(nextItemId++, "Cotton Polo", "M", 12000, "Short"));
        allItems.add(new Pants(nextItemId++, "Jeans", "32", 15000, "Regular"));
        allItems.add(new Jacket(nextItemId++, "Winter Parka", "XL", 45000, "Synthetic", true));

        allCustomers.add(new Customer(nextCustomerId++, "Ivan", "L", 50));
        allCustomers.add(new RegularCustomer(nextCustomerId++, "Alikhan", "M", 75, 8));
        allCustomers.add(new VIPCustomer(nextCustomerId++, "Olga", "M", 200, "Gold", true));
    }

    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("   BEKA CLOTHING STORE - WEEK 4");
        System.out.println("========================================");
        System.out.println("1. Add General Item");
        System.out.println("2. Add Shirt");
        System.out.println("3. Add Pants");
        System.out.println("4. Add Jacket");
        System.out.println("5. View All Items");
        System.out.println("6. Demonstrate Item Polymorphism");
        System.out.println("7. View Items by Category");
        System.out.println("8. Add Regular Customer");
        System.out.println("9. Add VIP Customer");
        System.out.println("10. View All Customers");
        System.out.println("11. Demonstrate Customer Polymorphism");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    private static void addGeneralItem() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter size: ");
        String size = scanner.nextLine();
        double price = getDoubleInput("Enter price: ");
        scanner.nextLine();
        allItems.add(new ClothingItem(nextItemId++, name, size, price));
        System.out.println("Item added!");
    }

    private static void addShirt() {
        System.out.print("Enter shirt name: ");
        String name = scanner.nextLine();
        System.out.print("Enter size: ");
        String size = scanner.nextLine();
        double price = getDoubleInput("Enter price: ");
        scanner.nextLine();
        System.out.print("Sleeve type (Short/Long/Sleeveless): ");
        String sleeve = scanner.nextLine();
        allItems.add(new Shirt(nextItemId++, name, size, price, sleeve));
        System.out.println("Shirt added!");
    }

    private static void addPants() {
        System.out.print("Enter pants name: ");
        String name = scanner.nextLine();
        System.out.print("Enter size: ");
        String size = scanner.nextLine();
        double price = getDoubleInput("Enter price: ");
        scanner.nextLine();
        System.out.print("Fit type (Slim/Regular/Relaxed): ");
        String fit = scanner.nextLine();
        allItems.add(new Pants(nextItemId++, name, size, price, fit));
        System.out.println("Pants added!");
    }

    private static void addJacket() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter size: ");
        String size = scanner.nextLine();
        double price = getDoubleInput("Enter price: ");
        scanner.nextLine();
        System.out.print("Enter material: ");
        String material = scanner.nextLine();
        System.out.print("Is waterproof? (true/false): ");
        boolean wp = scanner.nextBoolean();
        scanner.nextLine();
        allItems.add(new Jacket(nextItemId++, name, size, price, material, wp));
        System.out.println("Jacket added!");
    }

    private static void viewAllItems() {
        System.out.println("========================================");
        System.out.println("   ALL ITEMS (POLYMORPHIC)");
        System.out.println("========================================");
        if (allItems.isEmpty()) {
            System.out.println("No items.");
            return;
        }
        System.out.println("Total: " + allItems.size() + "\n");
        for (int i = 0; i < allItems.size(); i++) {
            ClothingItem item = allItems.get(i);
            System.out.println((i + 1) + ". " + item);

            // instanceof & downcasting
            if (item instanceof Jacket) {
                Jacket j = (Jacket) item;
                if (j.isWinterJacket()) {
                    System.out.println("   Winter Jacket!");
                }
            } else if (item instanceof Shirt) {
                Shirt s = (Shirt) item;
                if (s.isCasual()) {
                    System.out.println("   Casual!");
                }
            } else if (item instanceof Pants) {
                Pants p = (Pants) item;
                if (p.isFormal()) {
                    System.out.println("   Formal!");
                }
            }
            if (item.isPremium()) {
                System.out.println("   Premium!");
            }
            System.out.println();
        }
    }

    private static void demonstrateItemPolymorphism() {
        System.out.println("========================================");
        System.out.println("   ITEM POLYMORPHISM DEMO");
        System.out.println("========================================");
        System.out.println("Calling displayInfo() on all items:\n");
        for (ClothingItem item : allItems) {
            item.displayInfo();
        }
        System.out.println("\nCalling careInstructions():\n");
        for (ClothingItem item : allItems) {
            System.out.print(item.getName() + ": ");
            item.careInstructions();
        }
        System.out.println("\nSame methods, different outputs!");
        System.out.println("This is POLYMORPHISM!");
    }

    private static void viewItemsByCategory() {
        System.out.println("========================================");
        System.out.println("   VIEW BY CATEGORY");
        System.out.println("========================================");
        System.out.println("1. Shirts");
        System.out.println("2. Pants");
        System.out.println("3. Jackets");
        System.out.println("4. General Items");
        int choice = getIntInput("Select: ");
        scanner.nextLine();
        System.out.println();

        int count = 0;
        for (ClothingItem item : allItems) {
            boolean match = false;
            if (choice == 1 && item instanceof Shirt) match = true;
            else if (choice == 2 && item instanceof Pants) match = true;
            else if (choice == 3 && item instanceof Jacket) match = true;
            else if (choice == 4 && item.getClass() == ClothingItem.class) match = true;

            if (match) {
                count++;
                System.out.println(count + ". " + item);
            }
        }
        if (count == 0) {
            System.out.println("No items in this category.");
        }
    }

    private static void addRegularCustomer() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Preferred size: ");
        String size = scanner.nextLine();
        int purchases = getIntInput("Total purchases: ");
        scanner.nextLine();
        allCustomers.add(new RegularCustomer(nextCustomerId++, name, size, 0, purchases));
        System.out.println("Customer registered!");
    }

    private static void addVIPCustomer() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Membership Level (Gold/Platinum/Diamond): ");
        String level = scanner.nextLine();
        allCustomers.add(new VIPCustomer(nextCustomerId++, name, "M", 500, level, true));
        System.out.println("VIP Customer registered!");
    }

    private static void viewAllCustomers() {
        System.out.println("========================================");
        System.out.println("   ALL CUSTOMERS (POLYMORPHIC)");
        System.out.println("========================================");
        if (allCustomers.isEmpty()) {
            System.out.println("No customers.");
            return;
        }
        System.out.println("Total: " + allCustomers.size() + "\n");
        for (int i = 0; i < allCustomers.size(); i++) {
            Customer c = allCustomers.get(i);
            System.out.println((i + 1) + ". " + c);
            System.out.println("   Discount: " + c.getDiscount() + "%");

            if (c instanceof VIPCustomer) {
                VIPCustomer vip = (VIPCustomer) c;
                if (vip.isDiamondMember()) {
                    System.out.println("   Diamond VIP!");
                }
            } else if (c instanceof RegularCustomer) {
                RegularCustomer reg = (RegularCustomer) c;
                if (reg.isFrequentShopper()) {
                    System.out.println("    Frequent Shopper!");
                }
            }
            System.out.println();
        }
    }

    private static void demonstrateCustomerPolymorphism() {
        System.out.println("========================================");
        System.out.println("   CUSTOMER POLYMORPHISM DEMO");
        System.out.println("========================================");
        System.out.println("Calling shop() on all customers:\n");
        for (Customer c : allCustomers) {
            c.shop();
        }
        System.out.println("\nDiscount comparison:\n");
        for (Customer c : allCustomers) {
            System.out.println(c.getName() + " (" + c.getCustomerType() + "): " + c.getDiscount() + "%");
        }
        System.out.println("\n Same methods, different behavior!");
        System.out.println(" This is POLYMORPHISM!");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println(" Please enter a valid number.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println(" Please enter a valid price.");
            scanner.next();
        }
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }
}