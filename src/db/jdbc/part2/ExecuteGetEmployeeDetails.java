package db.jdbc.part2;

import java.sql.*;

public class ExecuteGetEmployeeDetails {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testdb?user=postgres&password=postgres";

        String procedureCall = "CALL Get_Employee_Details(?,?, ?)";

        try(Connection connection = DriverManager.getConnection(url);
            CallableStatement callableStatement = connection.prepareCall(procedureCall)) {
            callableStatement.setInt(1,1);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.execute();
            String name = callableStatement.getString(2);
            String email = callableStatement.getString(3);
            System.out.println("employee name: "+ name+" email: "+email);
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
