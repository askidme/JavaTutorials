package db.jdbc.part2;

import java.sql.*;

public class BatchProcessingPreparedStatement {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        String insertSQL = "INSERT INTO employees (id, name, email) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {

            // Disable auto-commit
            connection.setAutoCommit(false);

            // First record
            pstmt.setInt(1, 20);
            pstmt.setString(2, "Hannah Montana");
            pstmt.setString(3, "hannah.montana@example.com");
            pstmt.addBatch();

            // Second record
            pstmt.setInt(1, 21);
            pstmt.setString(2, "Ian Fleming");
            pstmt.setString(3, "ian.fleming@example.com");
            pstmt.addBatch();

            // Third record
            pstmt.setInt(1, 22);
            pstmt.setString(2, "Jenna Marbles");
            pstmt.setString(3, "jenna.marbles@example.com");
            pstmt.addBatch();

            // Execute batch
            int[] results = pstmt.executeBatch();

            // Commit transaction
            connection.commit();

            System.out.println("Batch executed successfully. Rows inserted: " + results.length);

        } catch (BatchUpdateException bue) {
            System.out.println("BatchUpdateException occurred: " + bue.getMessage());
            // Handle batch update exception
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

