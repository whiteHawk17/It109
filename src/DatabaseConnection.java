// src/DatabaseConnection.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_booking_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Vinay@2005";

    public static Connection getConnection() throws SQLException {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // or "com.mysql.jdbc.Driver" for older versions
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found.");
        }
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

