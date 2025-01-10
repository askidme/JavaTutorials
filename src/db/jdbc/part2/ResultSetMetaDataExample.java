package db.jdbc.part2;

import java.sql.*;

public class ResultSetMetaDataExample {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        String query = "SELECT * FROM employees";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("Table Columns: "+columnCount);
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(metaData.getColumnName(i) + " - " + metaData.getColumnTypeName(i));
            }

            System.out.println("\nEmployee Details:");
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + ": " + resultSet.getObject(i) + "  ");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
