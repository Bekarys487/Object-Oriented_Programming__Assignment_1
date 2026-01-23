package menu;

import database.ClothingItemDAO;
import exception.InvalidInputException;
import model.*;

import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final ClothingItemDAO dao = new ClothingItemDAO();
    private int nextOrderId = 1;

    @Override
    public void displayMenu() {
        System.out.println("===== CLOTHING STORE (DB) =====");
        System.out.println("1) Show items");
        System.out.println("2) Add item");
        System.out.println("3) Update item");
        System.out.println("4) Delete item");
        System.out.println("5) Search by name");
        System.out.println("6) Search by price range");
        System.out.println("7) Search by min price");
        System.out.println("8) Buy item");
        System.out.println("9) Show items by type");
        System.out.println("10) Show out of stock items");
        System.out.println("11) Polymorphism demo");
        System.out.println("0) Exit");
    }

    @Override
    public void run() {
        while (true) {
            displayMenu();
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1 -> showItems();
                case 2 -> addItem();
                case 3 -> updateItem();
                case 4 -> deleteItem();
                case 5 -> searchByName();
                case 6 -> searchByPriceRange();
                case 7 -> searchByMinPrice();
                case 8 -> buyItem();
                case 9 -> showItemsByType();
                case 10 -> showOutOfStock();
                case 11 -> polymorphismDemo();
                case 0 -> { System.out.println("Program ended."); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void showItems() {
        printList(dao.getAllItems());
    }

    private void addItem() {
        int type = readType();
        ClothingItem item = createItem(
                1,
                type,
                readString("Name: "),
                readString("Size: "),
                readDouble("Price: "),
                readInt("Stock: ")
        );
        System.out.println(dao.insertItem(item) ? "Inserted!" : "Insert failed!");
    }

    private void updateItem() {
        int id = readInt("Item ID: ");
        ClothingItem existing = dao.getItemById(id);
        if (existing == null) throw new InvalidInputException("Item not found");

        ClothingItem updated = createItem(
                id,
                readType(),
                readString("New name: "),
                readString("New size: "),
                readDouble("New price: "),
                readInt("New stock: ")
        );
        System.out.println(dao.updateItem(updated) ? "Updated!" : "Update failed!");
    }

    private void deleteItem() {
        int id = readInt("Item ID: ");
        ClothingItem item = dao.getItemById(id);
        if (item == null) throw new InvalidInputException("Item not found");

        if (readString("Type YES to confirm: ").equalsIgnoreCase("YES")) {
            System.out.println(dao.deleteItem(id) ? "Deleted!" : "Delete failed!");
        }
    }

    private void searchByName() {
        printList(dao.searchByName(readString("Name contains: ")));
    }

    private void searchByPriceRange() {
        printList(dao.searchByPriceRange(
                readDouble("Min price: "),
                readDouble("Max price: ")
        ));
    }

    private void searchByMinPrice() {
        printList(dao.searchByMinPrice(readDouble("Min price: ")));
    }

    private void buyItem() {
        showItems();
        int id = readInt("Item ID: ");
        int qty = readInt("Quantity: ");

        if (!dao.reduceStock(id, qty))
            throw new InvalidInputException("Not enough stock");

        System.out.println("Purchase completed.");
    }

    // ---------- NEW OPTIONS ----------

    private void showItemsByType() {
        String type = readString("Enter type (Shirt/Pants/Jacket): ");
        dao.getAllItems().stream()
                .filter(i -> i.getType().equalsIgnoreCase(type))
                .forEach(System.out::println);
    }

    private void showOutOfStock() {
        dao.getAllItems().stream()
                .filter(i -> i.getStock() == 0)
                .forEach(System.out::println);
    }

    private void polymorphismDemo() {
        ClothingItem[] items = {
                new Shirt(1, "Demo Shirt", "M", 100, 1),
                new Pants(2, "Demo Pants", "L", 200, 1),
                new Jacket(3, "Demo Jacket", "XL", 300, 1)
        };
        for (ClothingItem i : items)
            System.out.println(i.getType() + " discounted: " + i.applyDiscount(10));
    }

    // ---------- HELPERS ----------

    private int readType() {
        int t = readInt("Type (1 Shirt, 2 Pants, 3 Jacket): ");
        if (t < 1 || t > 3) throw new InvalidInputException("Wrong type");
        return t;
    }

    private ClothingItem createItem(int id, int type, String n, String s, double p, int st) {
        return switch (type) {
            case 1 -> new Shirt(id, n, s, p, st);
            case 2 -> new Pants(id, n, s, p, st);
            case 3 -> new Jacket(id, n, s, p, st);
            default -> throw new InvalidInputException("Wrong type");
        };
    }

    private void printList(List<ClothingItem> list) {
        if (list.isEmpty()) System.out.println("No results.");
        else list.forEach(System.out::println);
    }

    private int readInt(String p) {
        while (true) try {
            System.out.print(p);
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Enter integer!");
        }
    }

    private double readDouble(String p) {
        while (true) try {
            System.out.print(p);
            return Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Enter number!");
        }
    }

    private String readString(String p) {
        System.out.print(p);
        String s = scanner.nextLine().trim();
        if (s.isEmpty()) throw new InvalidInputException("Empty input");
        return s;
    }
}
