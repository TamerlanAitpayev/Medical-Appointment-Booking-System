package edu.aitu.oop3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER = "postgres.dztwgjjtblmjwxphlfbj";
    private static final String PASSWORD = "hellonigga1!@KKK";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initDatabase() {
        String sql =
                "CREATE TABLE IF NOT EXISTS patients (" +
                        "  id SERIAL PRIMARY KEY, " +
                        "  name VARCHAR(100) NOT NULL, " +
                        "  lastname VARCHAR(100) NOT NULL, " +
                        "  age INT, " +
                        "  gender VARCHAR(10), " +
                        "  weight DECIMAL(5, 2), " +
                        "  height DECIMAL(5, 2), " +
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

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("--- Database tables initialized (IF NOT EXISTS) ---");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }
}