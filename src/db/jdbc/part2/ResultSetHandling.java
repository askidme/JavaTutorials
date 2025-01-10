package db.jdbc.part2;

import java.sql.*;

public class ResultSetHandling {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        String query = "SELECT id, name, email FROM employees";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery(query)) {

            // Move cursor to the last row
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                System.out.println("Total Rows: " + rowCount);
            }

            // Iterate backward
            System.out.println("\nIterating Backward:");
            while (resultSet.previous()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

            // Move to the first row
            if (resultSet.first()) {
                System.out.println("\nFirst Row:");
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Email: " + resultSet.getString("email"));
            }

            // Absolute positioning
            System.out.println("\nAbsolute Position (Row 2):");
            if (resultSet.absolute(2)) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Email: " + resultSet.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
