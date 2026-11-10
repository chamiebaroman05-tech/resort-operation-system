package com.scc.ui;

import com.scc.model.User;
import java.util.Scanner;

public class ReceptionistMenu {

    public void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        do {
            System.out.println("\n--- Receptionist Menu ---");
            System.out.println("Welcome, " + (user != null ? user.username() : "Receptionist") + "!");
            System.out.println("1. Manage Check-ins & Guests");
            System.out.println("2. View Room Statuses");
            System.out.println("3. Logout");
            System.out.print("Choose an option (1-3): ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    manageCheckInsAndGuests(scanner);
                    break;
                case "2":
                    viewRoomStatuses();
                    break;
                case "3":
                    System.out.println("Logging out... Returning to main menu.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        } while (running);
    }

    private void manageCheckInsAndGuests(Scanner scanner) {
        System.out.println("\n--- Manage Check-ins & Guests ---");
        System.out.print("Enter Guest Name or Reservation ID: ");
        String searchKey = scanner.nextLine();

        System.out.println("Processing details for: " + searchKey);
    }

    private void viewRoomStatuses() {
        System.out.println("\n--- Room Statuses ---");
        System.out.println("Fetching current room statuses...");
    }
}