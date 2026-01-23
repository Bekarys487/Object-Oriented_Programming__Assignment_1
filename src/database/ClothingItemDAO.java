package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClothingItemDAO {

    // ----------- INSERT -----------
    public boolean insertItem(ClothingItem item) {
        String sql = """
                INSERT INTO clothing_item(item_type, name, size, price, stock)
                VALUES (?, ?, ?, ?, ?)
                """;

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, item.getType());
            ps.setString(2, item.getName());
            ps.setString(3, item.getSize());
            ps.setDouble(4, item.getPrice());
            ps.setInt(5, item.getStock());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // ----------- SELECT ALL -----------
    public List<ClothingItem> getAllItems() {
        String sql = "SELECT * FROM clothing_item ORDER BY item_id";
        List<ClothingItem> items = new ArrayList<>();

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return items;

        try (PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                items.add(mapRowToItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }

        return items;
    }

    // ----------- SELECT BY ID -----------
    public ClothingItem getItemById(int id) {
        String sql = "SELECT * FROM clothing_item WHERE item_id = ?";

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return null;

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRowToItem(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }
        return null;
    }

    // ----------- UPDATE -----------
    public boolean updateItem(ClothingItem item) {
        String sql = """
                UPDATE clothing_item
                SET item_type = ?, name = ?, size = ?, price = ?, stock = ?
                WHERE item_id = ?
                """;

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, item.getType());
            ps.setString(2, item.getName());
            ps.setString(3, item.getSize());
            ps.setDouble(4, item.getPrice());
            ps.setInt(5, item.getStock());
            ps.setInt(6, item.getId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // ----------- DELETE -----------
    public boolean deleteItem(int id) {
        String sql = "DELETE FROM clothing_item WHERE item_id = ?";

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // ----------- BUY / reduce stock -----------
    public boolean reduceStock(int id, int qty) {
        String sql = """
                UPDATE clothing_item
                SET stock = stock - ?
                WHERE item_id = ? AND stock >= ?
                """;

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, qty);
            ps.setInt(2, id);
            ps.setInt(3, qty);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // ----------- SEARCH -----------
    public List<ClothingItem> searchByName(String name) {
        String sql = "SELECT * FROM clothing_item WHERE name ILIKE ? ORDER BY item_id";
        List<ClothingItem> items = new ArrayList<>();

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return items;

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) items.add(mapRowToItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }

        return items;
    }

    public List<ClothingItem> searchByPriceRange(double min, double max) {
        String sql = "SELECT * FROM clothing_item WHERE price BETWEEN ? AND ? ORDER BY price";
        List<ClothingItem> items = new ArrayList<>();

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return items;

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) items.add(mapRowToItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }

        return items;
    }

    public List<ClothingItem> searchByMinPrice(double min) {
        String sql = "SELECT * FROM clothing_item WHERE price >= ? ORDER BY price";
        List<ClothingItem> items = new ArrayList<>();

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return items;

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, min);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) items.add(mapRowToItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(c);
        }

        return items;
    }

    // ----------- helper -----------
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
