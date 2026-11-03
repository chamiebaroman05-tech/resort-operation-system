package com.scc.ui;

import com.scc.model.User;
import java.util.Scanner;

public class AdminMenu {
    public void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Manage Users");
            System.out.println("2. View Activity Logs");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            switch(choice) {
                case 1: System.out.println("[Feature] User management module selected."); break;
                case 2: System.out.println("[Feature] Activity logs module selected."); break;
                case 3: System.out.println("Logging out..."); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while(choice != 3);
    }
}