package menu;

import exception.InvalidInputException;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<ClothingItem> inventory = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    private int nextItemId = 1;
    private int nextOrderId = 1;

    public MenuManager() {
        inventory.add(new Shirt(nextItemId++, "Basic Shirt", "M", 8990, 10));
        inventory.add(new Pants(nextItemId++, "Black Pants", "L", 12990, 5));
        inventory.add(new Jacket(nextItemId++, "Winter Jacket", "XL", 29990, 3));
    }

    @Override
    public void displayMenu() {
        System.out.println("===== CLOTHING STORE =====");
        System.out.println("1) Show items");
        System.out.println("2) Add item");
        System.out.println("3) Buy item");
        System.out.println("4) Show orders");
        System.out.println("0) Exit");
    }

    @Override
    public void run() {
        while (true) {
            System.out.println();
            displayMenu();

            int choice = readInt("Choose: ");

            switch (choice) {
                case 1:
                    showItems();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    buyItem();
                    break;
                case 4:
                    showOrders();
                    break;
                case 0:
                    System.out.println("Program ended.");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void showItems() {
        System.out.println();
        System.out.println("--- INVENTORY ---");

        if (inventory.isEmpty()) {
            System.out.println("No items in inventory.");
            return;
        }

        for (ClothingItem item : inventory) {
            System.out.println(item);
        }
    }

    private void addItem() {
        try {
            System.out.println();
            System.out.println("--- ADD ITEM ---");
            System.out.println("Type: 1) Shirt  2) Pants  3) Jacket");

            int type = readInt("Choose type: ");
            String name = readString("Name: ");
            String size = readString("Size: ");
            double price = readDouble("Price: ");
            int stock = readInt("Stock: ");

            ClothingItem item;

            if (type == 1) {
                item = new Shirt(nextItemId++, name, size, price, stock);
            } else if (type == 2) {
                item = new Pants(nextItemId++, name, size, price, stock);
            } else if (type == 3) {
                item = new Jacket(nextItemId++, name, size, price, stock);
            } else {
                throw new InvalidInputException("Wrong type selected!");
            }

            inventory.add(item);
            System.out.println("Item added: " + item);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buyItem() {
        try {
            System.out.println();
            System.out.println("--- BUY ITEM ---");

            if (inventory.isEmpty()) {
                System.out.println("No items to buy.");
                return;
            }

            showItems();

            String customerName = readString("Customer name: ");
            Customer customer = new Customer(customerName);

            int id = readInt("Item ID: ");
            int qty = readInt("Quantity: ");

            ClothingItem item = findItemById(id);
            if (item == null) {
                throw new InvalidInputException("Item not found!");
            }

            item.reduceStock(qty);

            Order order = new Order(nextOrderId++, customer);
            order.addLine(new OrderLine(item.getType(), item.getName(), item.getPrice(), qty));
            orders.add(order);

            System.out.println("Purchase complete.");
            System.out.println(order);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showOrders() {
        System.out.println();
        System.out.println("--- ORDERS ---");

        if (orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }

        for (Order order : orders) {
            System.out.println(order);
            System.out.println("---------------------");
        }
    }

    private ClothingItem findItemById(int id) {
        for (ClothingItem item : inventory) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter an integer number!");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number!");
            }
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine();
        if (s.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        return s.trim();
    }
}