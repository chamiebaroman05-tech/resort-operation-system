package com.scc.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // USERS table matched to record fields
            stmt.execute("CREATE TABLE IF NOT EXISTS USERS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "role TEXT NOT NULL, " +
                    "status TEXT NOT NULL)");

            // GUESTS
            stmt.execute("CREATE TABLE IF NOT EXISTS GUESTS (" +
                    "guest_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "first_name TEXT NOT NULL, " +
                    "last_name TEXT NOT NULL, " +
                    "contact_number TEXT, " +
                    "address TEXT, " +
                    "email TEXT)");

            // ROOMS
            stmt.execute("CREATE TABLE IF NOT EXISTS ROOMS (" +
                    "room_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "room_number TEXT UNIQUE NOT NULL, " +
                    "room_type TEXT NOT NULL, " +
                    "room_capacity INTEGER NOT NULL, " +
                    "room_rate REAL NOT NULL, " +
                    "room_status TEXT NOT NULL)");

            // CHECK_INS
            stmt.execute("CREATE TABLE IF NOT EXISTS CHECK_INS (" +
                    "checkin_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "guest_id INTEGER NOT NULL, " +
                    "room_id INTEGER NOT NULL, " +
                    "receptionist_id INTEGER NOT NULL, " +
                    "checkin_date TEXT NOT NULL, " +
                    "checkout_date TEXT, " +
                    "checkin_status TEXT NOT NULL, " +
                    "FOREIGN KEY (guest_id) REFERENCES GUESTS(guest_id), " +
                    "FOREIGN KEY (room_id) REFERENCES ROOMS(room_id), " +
                    "FOREIGN KEY (receptionist_id) REFERENCES USERS(id))");

            // CLEANING_TASKS
            stmt.execute("CREATE TABLE IF NOT EXISTS CLEANING_TASKS (" +
                    "cleaning_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "room_id INTEGER NOT NULL, " +
                    "cleaner_id INTEGER NOT NULL, " +
                    "cleaning_date TEXT NOT NULL, " +
                    "cleaning_status TEXT NOT NULL, " +
                    "remarks TEXT, " +
                    "FOREIGN KEY (room_id) REFERENCES ROOMS(room_id), " +
                    "FOREIGN KEY (cleaner_id) REFERENCES USERS(id))");

            // INSPECTIONS
            stmt.execute("CREATE TABLE IF NOT EXISTS INSPECTIONS (" +
                    "inspection_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "cleaning_id INTEGER NOT NULL, " +
                    "inspector_id INTEGER NOT NULL, " +
                    "inspection_date TEXT NOT NULL, " +
                    "inspection_status TEXT NOT NULL, " +
                    "remarks TEXT, " +
                    "FOREIGN KEY (cleaning_id) REFERENCES CLEANING_TASKS(cleaning_id), " +
                    "FOREIGN KEY (inspector_id) REFERENCES USERS(id))");

            // ACTIVITY_LOGS
            stmt.execute("CREATE TABLE IF NOT EXISTS ACTIVITY_LOGS (" +
                    "log_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER NOT NULL, " +
                    "action TEXT NOT NULL, " +
                    "activity_details TEXT, " +
                    "activity_date TEXT NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES USERS(id))");

            // Insert default Admin account (Username: admin, Password: password123)
            stmt.execute("INSERT OR IGNORE INTO USERS (id, username, password, role, status) " +
                    "VALUES (1, 'admin', 'password123', 'Admin', 'Active')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}