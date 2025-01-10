package db.jdbc.part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteExample {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        String deleteSQL = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(deleteSQL)) {
            ps.setInt(1, 4);
            int rowsDeleted = ps.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
