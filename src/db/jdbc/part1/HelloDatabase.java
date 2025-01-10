package db.jdbc.part1;

import java.sql.DriverManager;
import java.sql.Driver;
import java.util.Enumeration;

public class HelloDatabase {
    public static void main(String[] args) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println("Registered Driver: " + driver.getClass().getName());
        }
    }
}

