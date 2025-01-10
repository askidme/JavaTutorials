package db.jdbc.part2;

import java.sql.*;

public class CallableStatementOutputExample {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        String procedureCall = "{ ? = call get_employee_count() }";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement callableStmt = connection.prepareCall(procedureCall)) {

            // Register the output parameter
            callableStmt.registerOutParameter(1, Types.INTEGER);

            // Execute stored procedure
            callableStmt.execute();

            // Retrieve the output parameter
            int count = callableStmt.getInt(1);
            System.out.println("Total Employees: " + count);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

