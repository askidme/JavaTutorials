package db.jdbc.part2;

import java.sql.*;

public class TransactionWithSavepoint {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        String insertSQL1 = "INSERT INTO employees (id, name, email) VALUES (41, 'Michael Scott', 'michael.scott@example.com')";
        String insertSQL2 = "INSERT INTO employees (id, name, email) VALUES (42, 'Pam Beesly', 'pam.beesly@example.com')";
        String insertSQL3 = "INSERT INTO employees (id, name, email) VALUES (43, 'Jim Halpert', 'jim.halpert@example.com')";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Disable auto-commit
            connection.setAutoCommit(false);

            Savepoint savepoint1 = null;

            try {
                // Execute first insert
                statement.executeUpdate(insertSQL1);
                System.out.println("Inserted Michael Scott");

                // Create a savepoint
                savepoint1 = connection.setSavepoint("Savepoint1");

                // Execute second insert
                statement.executeUpdate(insertSQL2);
                System.out.println("Inserted Pam Beesly");

                // Execute third insert with an intentional error (duplicate email)
                statement.executeUpdate(insertSQL3);
                System.out.println("Inserted Jim Halpert");

                // Commit transaction
                connection.commit();
                System.out.println("Transaction committed successfully.");

            } catch (SQLException e) {
                System.out.println("Error occurred: " + e.getMessage());
                if (savepoint1 != null) {
                    System.out.println("Rolling back to Savepoint1.");
                    connection.rollback(savepoint1);
                } else {
                    System.out.println("Rolling back entire transaction.");
                    connection.rollback();
                }
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

