// src/Room.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Room {
    public int roomId;
    public String roomType;
    public double price;
    public boolean available;

    public Room(int roomId, String roomType, double price, boolean available) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
        this.available = available;
    }

    public static List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        String query = "SELECT * FROM Rooms WHERE availability = true";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Room room = new Room(rs.getInt("room_id"), rs.getString("room_type"),
                                     rs.getDouble("price"), rs.getBoolean("availability"));
                availableRooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableRooms;
    }

    public boolean bookRoom(int userId) {
        String query = "UPDATE Rooms SET availability = false WHERE room_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.roomId);
            stmt.executeUpdate();

            String bookingQuery = "INSERT INTO Bookings (user_id, room_id, booking_date) VALUES (?, ?, NOW())";
            try (PreparedStatement bookingStmt = conn.prepareStatement(bookingQuery)) {
                bookingStmt.setInt(1, userId);
                bookingStmt.setInt(2, this.roomId);
                bookingStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}



