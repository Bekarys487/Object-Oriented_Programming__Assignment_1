package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClothingItemDAO {
    // SQL
    private static final String SQL_INSERT =
            "INSERT INTO clothing_item(item_type, name, size, price, stock) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL =
            "SELECT * FROM clothing_item ORDER BY item_id";

    private static final String SQL_SELECT_BY_ID =
            "SELECT * FROM clothing_item WHERE item_id = ?";

    private static final String SQL_UPDATE =
            "UPDATE clothing_item SET item_type = ?, name = ?, size = ?, price = ?, stock = ? WHERE item_id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM clothing_item WHERE item_id = ?";

    private static final String SQL_REDUCE_STOCK =
            "UPDATE clothing_item SET stock = stock - ? WHERE item_id = ? AND stock >= ?";

    private static final String SQL_SEARCH_NAME =
            "SELECT * FROM clothing_item WHERE name ILIKE ? ORDER BY item_id";

    private static final String SQL_SEARCH_PRICE_RANGE =
            "SELECT * FROM clothing_item WHERE price BETWEEN ? AND ? ORDER BY price";

    private static final String SQL_SEARCH_MIN_PRICE =
            "SELECT * FROM clothing_item WHERE price >= ? ORDER BY price";


    public boolean insertItem(ClothingItem item) {
        return executeUpdate(SQL_INSERT, ps -> {
            ps.setString(1, item.getType());
            ps.setString(2, item.getName());
            ps.setString(3, item.getSize());
            ps.setDouble(4, item.getPrice());
            ps.setInt(5, item.getStock());
        }) == 1;
    }

    public List<ClothingItem> getAllItems() {
        return executeQueryList(SQL_SELECT_ALL, null);
    }

    public ClothingItem getItemById(int id) {
        return executeQueryOne(SQL_SELECT_BY_ID, ps -> ps.setInt(1, id));
    }

    public boolean updateItem(ClothingItem item) {
        return executeUpdate(SQL_UPDATE, ps -> {
            ps.setString(1, item.getType());
            ps.setString(2, item.getName());
            ps.setString(3, item.getSize());
            ps.setDouble(4, item.getPrice());
            ps.setInt(5, item.getStock());
            ps.setInt(6, item.getId());
        }) == 1;
    }

    public boolean deleteItem(int id) {
        return executeUpdate(SQL_DELETE, ps -> ps.setInt(1, id)) == 1;
    }

    public boolean reduceStock(int id, int qty) {
        return executeUpdate(SQL_REDUCE_STOCK, ps -> {
            ps.setInt(1, qty);
            ps.setInt(2, id);
            ps.setInt(3, qty);
        }) == 1;
    }

    public List<ClothingItem> searchByName(String name) {
        return executeQueryList(SQL_SEARCH_NAME, ps -> ps.setString(1, "%" + name + "%"));
    }

    public List<ClothingItem> searchByPriceRange(double min, double max) {
        return executeQueryList(SQL_SEARCH_PRICE_RANGE, ps -> {
            ps.setDouble(1, min);
            ps.setDouble(2, max);
        });
    }

    public List<ClothingItem> searchByMinPrice(double min) {
        return executeQueryList(SQL_SEARCH_MIN_PRICE, ps -> ps.setDouble(1, min));
    }

    private interface Binder {
        void bind(PreparedStatement ps) throws SQLException;
    }

    private int executeUpdate(String sql, Binder binder) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            if (c == null) return 0;
            if (binder != null) binder.bind(ps);

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private List<ClothingItem> executeQueryList(String sql, Binder binder) {
        List<ClothingItem> items = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            if (c == null) return items;
            if (binder != null) binder.bind(ps);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(mapRowToItem(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    private ClothingItem executeQueryOne(String sql, Binder binder) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            if (c == null) return null;
            if (binder != null) binder.bind(ps);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRowToItem(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ClothingItem mapRowToItem(ResultSet rs) throws SQLException {
        int id = rs.getInt("item_id");
        String type = rs.getString("item_type");
        String name = rs.getString("name");
        String size = rs.getString("size");
        double price = rs.getDouble("price");
        int stock = rs.getInt("stock");

        return switch (type) {
            case "Shirt" -> new Shirt(id, name, size, price, stock);
            case "Pants" -> new Pants(id, name, size, price, stock);
            case "Jacket" -> new Jacket(id, name, size, price, stock);
            default -> new Shirt(id, name, size, price, stock);
        };
    }
}
