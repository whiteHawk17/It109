// src/Main.java
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Hotel Booking System");

        // User registration and login
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = new User(username, password);

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        boolean loggedIn = false;
        if (choice == 1) {
            if (user.register()) {
                System.out.println("Registration successful! Please log in.");
            }
        }
        if (choice == 2 && user.login()) {
            System.out.println("Login successful!");
            loggedIn = true;
        } else {
            System.out.println("Invalid credentials.");
        }

        // Viewing and booking rooms
        if (loggedIn) {
            List<Room> availableRooms = Room.getAvailableRooms();
            System.out.println("Available Rooms:");
            for (Room room : availableRooms) {
                System.out.println("Room ID: " + room.roomId + ", Type: " + room.roomType +
                                   ", Price: " + room.price + ", Available: " + room.available);
            }

            System.out.print("Enter Room ID to book: ");
            int roomId = scanner.nextInt();
            Room roomToBook = availableRooms.stream().filter(r -> r.roomId == roomId).findFirst().orElse(null);
            if (roomToBook != null && roomToBook.bookRoom(1)) { // assuming user ID 1 for simplicity
                System.out.println("Room booked successfully!");
            } else {
                System.out.println("Room booking failed.");
            }
        }
        scanner.close();
    }
}

