package db.jdbc.part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertExample {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        String insertSQL = "INSERT INTO employees(id, name, email, salary) values(4, 'David', 'david@email.com', 95000)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();) {
            int rowsInserted = stmt.executeUpdate(insertSQL);
            System.out.println("Rows inserted: " + rowsInserted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
