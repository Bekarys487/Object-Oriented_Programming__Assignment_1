package app;

import database.DatabaseConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection c = DatabaseConnection.getConnection();
        DatabaseConnection.closeConnection(c);
    }
}
