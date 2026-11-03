package com.scc;

import com.scc.dao.DatabaseInitializer;
import com.scc.model.User;
import com.scc.ui.*;

public class Main {
    public static void main(String[] args) {
        // Step 1: Initialize Database and Tables automatically in RODGCCIS.db
        DatabaseInitializer.initializeDatabase();

        // Step 2: Handle Authentication Menu (Login / Register / Exit)
        Authentication auth = new Authentication();
        User loggedInUser = auth.showAuthMenu();

        // Step 3: Route to corresponding menu based on record role()
        if (loggedInUser != null) {
            String role = loggedInUser.role();
            switch (role.toLowerCase()) {
                case "admin":
                    new AdminMenu().showMenu(loggedInUser);
                    break;
                case "receptionist":
                    new ReceptionistMenu().showMenu(loggedInUser);
                    break;
                case "cleaner":
                case "cleaning staff":
                    new CleaningStaffMenu().showMenu(loggedInUser);
                    break;
                case "inspector":
                    new InspectorMenu().showMenu(loggedInUser);
                    break;
                default:
                    System.out.println("No designated menu found for role: " + role);
            }
        }
    }
}