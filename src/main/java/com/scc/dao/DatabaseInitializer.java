package com.scc.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // USERS table
            stmt.execute("CREATE TABLE IF NOT EXISTS USERS (" +
                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "full_name TEXT UNIQUE NOT NULL, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password_hash TEXT NOT NULL, " +
                    "role TEXT NOT NULL, " +
                    "account_status TEXT NOT NULL)");

            // GUESTS table
            stmt.execute("CREATE TABLE IF NOT EXISTS GUESTS (" +
                    "guest_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "first_name VARCHAR(50) NOT NULL, " +
                    "last_name VARCHAR(50) NOT NULL, " +
                    "contact_number VARCHAR(20) NOT NULL, " +
                    "address VARCHAR(150) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL)");

            // ROOMS table
            stmt.execute("CREATE TABLE IF NOT EXISTS ROOMS (" +
                    "room_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "room_number VARCHAR(10) UNIQUE NOT NULL, " +
                    "room_type VARCHAR(50) NOT NULL, " +
                    "room_capacity INTEGER NOT NULL, " +
                    "room_rate DECIMAL(10,2) NOT NULL, " +
                    "room_status VARCHAR(30) NOT NULL)");

            // CHECK_INS table
            stmt.execute("CREATE TABLE IF NOT EXISTS CHECK_INS (" +
                    "checkin_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "guest_id INTEGER NOT NULL, " +
                    "room_id INTEGER NOT NULL, " +
                    "receptionist_id INTEGER NOT NULL, " +
                    "checkin_date DATETIME NOT NULL, " +
                    "checkout_date DATETIME, " +
                    "checkin_status VARCHAR(20) NOT NULL, " +
                    "FOREIGN KEY (guest_id) REFERENCES GUESTS(guest_id), " +
                    "FOREIGN KEY (room_id) REFERENCES ROOMS(room_id), " +
                    "FOREIGN KEY (receptionist_id) REFERENCES USERS(user_id))");

            // CLEANING_TASKS table
            stmt.execute("CREATE TABLE IF NOT EXISTS CLEANING_TASKS (" +
                    "cleaning_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "room_id INTEGER NOT NULL, " +
                    "cleaner_id INTEGER NOT NULL, " +
                    "cleaning_date DATETIME NOT NULL, " +
                    "cleaning_status VARCHAR(30) NOT NULL, " +
                    "remarks TEXT, " +
                    "FOREIGN KEY (room_id) REFERENCES ROOMS(room_id), " +
                    "FOREIGN KEY (cleaner_id) REFERENCES USERS(user_id))");

            // INSPECTIONS table
            stmt.execute("CREATE TABLE IF NOT EXISTS INSPECTIONS (" +
                    "inspection_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "cleaning_id INTEGER NOT NULL, " +
                    "inspector_id INTEGER NOT NULL, " +
                    "inspection_date DATETIME NOT NULL, " +
                    "inspection_status VARCHAR(30) NOT NULL, " +
                    "remarks TEXT, " +
                    "FOREIGN KEY (cleaning_id) REFERENCES CLEANING_TASKS(cleaning_id), " +
                    "FOREIGN KEY (inspector_id) REFERENCES USERS(user_id))");

            // ACTIVITY_LOGS table
            stmt.execute("CREATE TABLE IF NOT EXISTS ACTIVITY_LOGS (" +
                    "log_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER NOT NULL, " +
                    "action VARCHAR(100) NOT NULL, " +
                    "activity_details TEXT NOT NULL, " +
                    "activity_date DATETIME NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES USERS(user_id))");

            // Insert default Admin account (Username: admin, Password: password123)
            stmt.execute("INSERT OR IGNORE INTO USERS (user_id, full_name, username, password_hash, role, account_status) " +
                    "VALUES (1, 'Administrator', 'admin', 'password123', 'Admin', 'Active')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}