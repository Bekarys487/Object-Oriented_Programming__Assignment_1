import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Polymorphic ArrayLists
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
                case 12 -> testCustomerMethods();
                case 0 -> {
                    System.out.println("👋 Thank you for using Beka Clothing Store! Goodbye!");
                    running = false;
                }
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    // ⬇️ ОСТАВЛЯЕШЬ ЗДЕСЬ ВСЕ МЕТОДЫ Main:
    // addInitialData()
    // displayMenu()
    // addGeneralItem()
    // addShirt()
    // addPants()
    // addJacket()
    // viewAllItems()
    // demonstrateItemPolymorphism()
    // viewItemsByCategory()
    // addRegularCustomer()
    // addVIPCustomer()
    // viewAllCustomers()
    // demonstrateCustomerPolymorphism()
    // testCustomerMethods()
    // getIntInput()
    // getDoubleInput()
}