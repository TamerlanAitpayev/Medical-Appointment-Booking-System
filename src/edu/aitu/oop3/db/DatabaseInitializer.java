package edu.aitu.oop3.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initDatabase() {
        String sql =
                "CREATE TABLE IF NOT EXISTS patients (" +
                        "  id SERIAL PRIMARY KEY, " +
                        "  name VARCHAR(100) NOT NULL, " +
                        "  lastname VARCHAR(100) NOT NULL, " +
                        "  age INT, " +
                        "  email VARCHAR(150) UNIQUE, " +
                        "  phone_number VARCHAR(20) NOT NULL" +
                        ");" +

                        "CREATE TABLE IF NOT EXISTS doctors (" +
                        "  id SERIAL PRIMARY KEY, " +
                        "  name VARCHAR(100) NOT NULL, " +
                        "  email VARCHAR(150) UNIQUE NOT NULL, " +
                        "  specialization VARCHAR(100) NOT NULL" +
                        ");" +

                        "CREATE TABLE IF NOT EXISTS appointments (" +
                        "  id SERIAL PRIMARY KEY, " +
                        "  patient_id INT NOT NULL, " +
                        "  doctor_id INT NOT NULL, " +
                        "  appointment_time TIMESTAMP NOT NULL, " +
                        "  status VARCHAR(20) DEFAULT 'Scheduled', " +
                        "  CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE, " +
                        "  CONSTRAINT fk_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE" +
                        ");";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("--- Database tables initialized in Supabase ---");

        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());

        }
    }
}