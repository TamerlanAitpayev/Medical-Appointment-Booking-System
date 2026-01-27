package Repositories.implementations;

import Entities.Patient;
import Repositories.PatientRepository;
import edu.aitu.oop3.db.DatabaseConnection;

import java.sql.*;

public class PatientRepositoryImpl implements PatientRepository {

    @Override
    public void add(Patient p) {
        String sql = "INSERT INTO patients (name, lastname, age, gender, weight, height, email, phone_number) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getLastName());
            ps.setInt(3, p.getAge());
            ps.setString(4, p.getGender());
            ps.setDouble(5, p.getWeight());
            ps.setDouble(6, p.getHeight());
            ps.setString(7, p.getEmail());
            ps.setString(8, p.getPhoneNumber());
            ps.executeUpdate();
            System.out.println("Patient added successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding patient: " + e.getMessage());
        }
    }

    @Override
    public Patient getById(int id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Patient p = new Patient();
                    p.setName(rs.getString("name"));
                    p.setLastName(rs.getString("lastname"));
                    p.setAge(rs.getInt("age"));
                    p.setGender(rs.getString("gender"));
                    p.setWeight(rs.getDouble("weight"));
                    p.setHeight(rs.getDouble("height"));
                    p.setEmail(rs.getString("email"));
                    p.setPhoneNumber(rs.getString("phone_number"));
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding patient: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Patient with ID " + id + " deleted successfully.");
            } else {
                System.out.println("No patient found with ID " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting patient: " + e.getMessage());
        }
    }
}