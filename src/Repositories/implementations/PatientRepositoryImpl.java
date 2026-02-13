package Repositories.implementations;

import Entities.Patient;
import Repositories.PatientRepository;
import edu.aitu.oop3.db.DatabaseConnection;
import java.sql.*;

public class PatientRepositoryImpl implements PatientRepository {

    @Override
    public void add(Patient patient) {
        String sql = "INSERT INTO patients(name, lastname, age, email, phone_number) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement prst = conn.prepareStatement(sql)) {

            prst.setString(1, patient.getName());
            prst.setString(2, patient.getLastName());
            prst.setInt(3, patient.getAge());
            prst.setString(4, patient.getEmail());
            prst.setString(5, patient.getPhoneNumber());

            prst.executeUpdate();
            System.out.println("Patient added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }

    @Override
    public Patient getById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        System.out.println("Patient with ID " + id + " deleted.");
    }
}