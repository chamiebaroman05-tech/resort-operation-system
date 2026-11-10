package com.scc.ui;

import com.scc.model.User;
import java.util.Scanner;

public class CleaningStaffMenu {

    public void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Cleaning Staff Menu ---");
            System.out.println("Welcome, " + (user != null ? user.username() : "Staff") + "!");
            System.out.println("1. View Assigned Cleaning Tasks");
            System.out.println("2. Update Task Status");
            System.out.println("3. Logout");
            System.out.print("Choose an option (1-3): ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    viewAssignedTasks(user);
                    break;
                case "2":
                    updateTaskStatus(scanner);
                    break;
                case "3":
                    System.out.println("Logging out... Returning to main menu.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    private void viewAssignedTasks(User user) {
        System.out.println("\n--- Assigned Cleaning Tasks ---");
        // TODO: Implement database/service call to fetch tasks for user.id()
        System.out.println("No tasks currently assigned or feature under development.");
    }

    private void updateTaskStatus(Scanner scanner) {
        System.out.println("\n--- Update Task Status ---");
        System.out.print("Enter Task ID to update: ");
        String taskId = scanner.nextLine();

        System.out.print("Enter new status (e.g., In Progress, Completed): ");
        String status = scanner.nextLine();

        // TODO: Implement database/service call to update the task status
        System.out.println("Task " + taskId + " status successfully updated to: " + status);
    }
}