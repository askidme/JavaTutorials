package db.jdbc.part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FirstJDBC {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:postgresql://localhost:5432/testdb"; // Replace 'testdb' with your database name
        String user = "postgres"; // Replace with your DB username
        String password = "postgres"; // Replace with your DB password

        // Optional: Load the PostgreSQL JDBC driver (not required for JDBC 4.0 and above)
        // try {
        //     Class.forName("org.postgresql.Driver");
        // } catch (ClassNotFoundException e) {
        //     System.out.println("PostgreSQL JDBC Driver not found.");
        //     e.printStackTrace();
        //     return;
        // }

        // Establishing the connection
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            // e.printStackTrace(); // Uncomment for detailed error
        }
    }
}


