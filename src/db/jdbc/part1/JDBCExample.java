package db.jdbc.part1;

import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "postgres";

        String query = "SELECT id, name, email, salary FROM employees";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Employee Details:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Double salary = resultSet.getDouble(4);
                System.out.println("ID: " + id + ", Name: " + name +", Email: " + email +", Salary: " + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
