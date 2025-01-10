package db.jdbc.part2;

import java.sql.*;

public class TransactionManagement {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        String insertSQL1 = "INSERT INTO employees (id, name, email) VALUES (31,'Kevin Hart', 'kevin.hart@example.com')";
        String insertSQL2 = "INSERT INTO employees (id, name, email) VALUES (32, 'Laura Palmer', 'laura.palmer@example.com')";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Disable auto-commit
            connection.setAutoCommit(false);

            try {
                // Execute first insert
                statement.executeUpdate(insertSQL1);
                System.out.println("Inserted Kevin Hart");

                // Execute second insert
                statement.executeUpdate(insertSQL2);
                System.out.println("Inserted Laura Palmer");

                // Commit transaction
                connection.commit();
                System.out.println("Transaction committed successfully.");

            } catch (SQLException e) {
                System.out.println("Error occurred. Rolling back changes.");
                connection.rollback();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

