package com.scc.ui;

import com.scc.model.User;
import java.util.Scanner;

public class ReceptionistMenu {
    public void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Receptionist Menu ---");
            System.out.println("1. Manage Check-ins & Guests");
            System.out.println("2. View Room Statuses");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            if (choice == 3) {
                System.out.println("Logging out...");
                break;
            }
        } while(true);
    }
}