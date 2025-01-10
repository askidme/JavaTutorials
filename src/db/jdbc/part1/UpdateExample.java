package db.jdbc.part1;

import java.sql.*;

public class UpdateExample {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        String updateSQL = "UPDATE employees set name = ? where id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);) {
            preparedStatement.setString(1, "Eve");
            preparedStatement.setInt(2, 2);
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
