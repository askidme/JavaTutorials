package db.jdbc.part2;

import java.sql.*;

public class BatchProcessingStatement {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        String insertSQL1 = "INSERT INTO employees (id, name, email) VALUES (10, 'Ethan Hunt', 'ethan.hunt@example.com')";
        String insertSQL2 = "INSERT INTO employees (id, name, email) VALUES (11, 'Fiona Glenanne', 'fiona.glenanne@example.com')";
        String insertSQL3 = "INSERT INTO employees (id, name, email) VALUES (12, 'George Costanza', 'george.costanza@example.com')";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Disable auto-commit
            connection.setAutoCommit(false);

            // Add SQL commands to batch
            statement.addBatch(insertSQL1);
            statement.addBatch(insertSQL2);
            statement.addBatch(insertSQL3);

            // Execute batch
            int[] results = statement.executeBatch();

            // Commit transaction
            connection.commit();

            System.out.println("Batch executed successfully. Rows inserted: " + results.length);

        } catch (BatchUpdateException bue) {
            System.out.println("BatchUpdateException occurred: " + bue.getMessage());
            // Handle batch update exception
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

