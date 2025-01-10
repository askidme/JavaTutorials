package db.jdbc.part1;

import java.sql.*;
import java.util.Scanner;

public class EmployeeCRUD {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb?user=postgres&password=postgres";

    private static void insertEmployee(Scanner scanner) {
        System.out.println("Enter employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter employee name: ");
        String name = scanner.nextLine();
        String sql = "INSERT INTO employees VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            int rowsInserted = ps.executeUpdate();
            System.out.println("rows inserted: " + rowsInserted);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Duplicate ID. Please use a unique ID");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.println("Enter employee id to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new employee name: ");
        String name = scanner.nextLine();
        String sql = "UPDATE employees set name = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, id);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee udpated successfully ");
            } else {
                System.out.println("No employees found with provided id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.println("Enter employee id to delete: ");
        int id = scanner.nextInt();
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee deleted successfully ");
            } else {
                System.out.println("No employees found with provided id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewEmployees() {
        String selectSQL = "SELECT * FROM employees";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Employee Management ===");
            System.out.println("1. Insert Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    insertEmployee(scanner);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using PostgreSQL!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }
}
