package db.jdbc.part2;

import java.sql.*;

public class CallableStatementExample2 {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        String procedureCall = "{ call add_employee(?, ?, ?) }";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement callableStmt = connection.prepareCall(procedureCall)) {

            // Set input parameters
            callableStmt.setInt(1, 1020);
            callableStmt.setString(2, "Karen Page2");
            callableStmt.setString(3, "karen.page2@example.com");

            // Execute stored procedure
            callableStmt.execute();
            System.out.println("Stored procedure executed successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
