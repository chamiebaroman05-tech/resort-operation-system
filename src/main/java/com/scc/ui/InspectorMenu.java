package com.scc.ui;

import com.scc.model.User;
import java.util.Scanner;

public class InspectorMenu {

    public void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Inspector Menu ---");
            System.out.println("Welcome, " + (user != null ? user.username() : "Inspector") + "!");
            System.out.println("1. Inspect Cleaned Rooms");
            System.out.println("2. Logout");
            System.out.print("Choose an option (1-2): ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    inspectCleanedRooms(scanner, user);
                    break;
                case "2":
                    System.out.println("Logging out... Returning to main menu.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }

    private void inspectCleanedRooms(Scanner scanner, User user) {
        System.out.println("\n--- Inspect Cleaned Rooms ---");
        System.out.print("Enter Room Number or Task ID to inspect: ");
        String target = scanner.nextLine();

        System.out.print("Enter inspection result (e.g., Passed, Failed): ");
        String result = scanner.nextLine();

        // TODO: Implement database/service call to save inspection results
        System.out.println("Inspection for '" + target + "' recorded as: " + result + " (Inspector ID: " + user.id() + ")");
    }
}