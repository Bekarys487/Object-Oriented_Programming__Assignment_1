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
                case 4 -> addJacket(); // Остальные (2,3) добавляются аналогично
                case 5 -> viewAllItems();
                case 6 -> demonstrateItemPolymorphism();
                case 8 -> addRegularCustomer();
                case 9 -> addVIPCustomer();
                case 10 -> viewAllCustomers();
                case 0 -> {
                    System.out.println("👋 Thank you for using Beka Clothing Store! Goodbye!");
                    running = false;
                }
                default -> System.out.println("❌ Invalid choice. (Note: Only cases 1,4,5,6,8,9,10,0 implemented in this fix)");
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
        allItems.add(new Jacket(nextItemId++, "Winter Parka", "XL", 45000, "Synthetic", true));
        allCustomers.add(new Customer(nextCustomerId++, "Ivan", "L", 50));
        allCustomers.add(new VIPCustomer(nextCustomerId++, "Olga", "M", 200, "Gold", true));
    }

    private static void displayMenu() {
        System.out.println("\n--- Beka Clothing Store Menu ---");
        System.out.println("1. Add General Item");
        System.out.println("4. Add Jacket");
        System.out.println("5. View All Items");
        System.out.println("6. Demonstrate Item Polymorphism");
        System.out.println("8. Add Regular Customer");
        System.out.println("9. Add VIP Customer");
        System.out.println("10. View All Customers");
        System.out.println("0. Exit");
    }

    private static void addGeneralItem() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter size: ");
        String size = scanner.nextLine();
        double price = getDoubleInput("Enter price: ");
        allItems.add(new ClothingItem(nextItemId++, name, size, price));
        System.out.println("✅ Item added!");
    }

    private static void addJacket() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter size: ");
        String size = scanner.nextLine();
        double price = getDoubleInput("Enter price: ");
        System.out.print("Enter material: ");
        String material = scanner.nextLine();
        System.out.print("Is waterproof? (true/false): ");
        boolean wp = scanner.nextBoolean();
        scanner.nextLine();
        allItems.add(new Jacket(nextItemId++, name, size, price, material, wp));
        System.out.println("✅ Jacket added!");
    }

    private static void viewAllItems() {
        System.out.println("--- All Store Items ---");
        for (ClothingItem item : allItems) {
            System.out.println(item);
        }
    }

    private static void demonstrateItemPolymorphism() {
        System.out.println("--- Polymorphism in Action ---");
        for (ClothingItem item : allItems) {
            item.displayInfo(); // Вызовет метод Jacket, если объект - куртка
            item.careInstructions();
            System.out.println("-------");
        }
    }

    private static void addRegularCustomer() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Preferred size: ");
        String size = scanner.nextLine();
        allCustomers.add(new Customer(nextCustomerId++, name, size, 0));
        System.out.println("✅ Customer registered!");
    }

    private static void addVIPCustomer() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Membership Level (Gold/Platinum/Diamond): ");
        String level = scanner.nextLine();
        allCustomers.add(new VIPCustomer(nextCustomerId++, name, "M", 500, level, true));
        System.out.println("✅ VIP Customer registered!");
    }

    private static void viewAllCustomers() {
        for (Customer c : allCustomers) {
            System.out.println(c);
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Please enter a valid number.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("❌ Please enter a valid price.");
            scanner.next();
        }
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }
}