package com.scc.ui;

import com.scc.model.User;
import java.util.Scanner;

public class CleaningStaffMenu {
    public void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Cleaning Staff Menu ---");
        System.out.println("1. View Assigned Cleaning Tasks");
        System.out.println("2. Update Task Status");
        System.out.println("3. Logout");
    }
}