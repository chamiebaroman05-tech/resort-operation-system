package com.scc.ui;

import com.scc.model.User;
import java.util.Scanner;

public class AdminMenu {

    public void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("Welcome, " + (user != null ? user.username() : "Admin") + "!");
            System.out.println("1. Manage Users");
            System.out.println("2. View Activity Logs");
            System.out.println("3. Logout");
            System.out.print("Choose an option (1-3): ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    manageUsers(scanner);
                    break;
                case "2":
                    viewActivityLogs();
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

    private void manageUsers(Scanner scanner) {
        System.out.println("\n--- User Management ---");
        System.out.println("1. View All Users");
        System.out.println("2. Add New User");
        System.out.println("3. Delete User");
        System.out.print("Choose an option: ");

        String subInput = scanner.nextLine().trim();
        System.out.println("[Feature] User management action selected: " + subInput);
    }

    private void viewActivityLogs() {
        System.out.println("\n--- Activity Logs ---");
        System.out.println("Fetching system activity logs...");
    }
}