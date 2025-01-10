package db.jdbc.part2;

import java.sql.*;

public class CursorPosition {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        String query = "SELECT id, name FROM employees";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    System.out.println("First Row:");
                }
                if (resultSet.isLast()) {
                    System.out.println("Last Row:");
                }
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

