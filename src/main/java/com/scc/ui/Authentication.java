package com.scc.ui;

import com.scc.dao.UserDao;
import com.scc.model.User;
import java.util.Scanner;

public class Authentication {
    public User showAuthMenu() {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();

        while (true) {
            System.out.println("\n=========================================");
            System.out.println("   HOTEL MANAGEMENT SYSTEM - WELCOME     ");
            System.out.println("=========================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    User loggedInUser = handleLogin(scanner, userDao);
                    if (loggedInUser != null) {
                        return loggedInUser;
                    }
                    break;
                case 2:
                    handleRegistration(scanner, userDao);
                    break;
                case 3:
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }

    private User handleLogin(Scanner scanner, UserDao userDao) {
        System.out.println("\n--- SYSTEM LOGIN ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userDao.getUserByUsername(username);

        if (user != null && user.password().equals(password)) {
            if ("Active".equalsIgnoreCase(user.status())) {
                System.out.println("\nLogin successful! Welcome, " + user.username());
                System.out.println(user); // Tests the toString() method in your record
                return user;
            } else {
                System.out.println("\nError: Your account is inactive.");
            }
        } else {
            System.out.println("\nError: Invalid username or password.");
        }
        return null;
    }

    private void handleRegistration(Scanner scanner, UserDao userDao) {
        System.out.println("\n--- USER REGISTRATION ---");
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        // Check if username exists
        if (userDao.getUserByUsername(username) != null) {
            System.out.println("Error: Username already exists! Try another one.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter role (Admin, Receptionist, Cleaner, Inspector): ");
        String role = scanner.nextLine();

        String status = "Active"; // Default active status for new registrations

        boolean success = userDao.registerUser(username, password, role, status);
        if (success) {
            System.out.println("Registration successful! Your data has been saved to RODGCCIS.db.");
        }
    }
}