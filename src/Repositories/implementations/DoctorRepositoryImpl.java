package Repositories.implementations;

import Entities.Doctor;
import Repositories.DoctorRepository;
import edu.aitu.oop3.db.DatabaseConnection;
import java.sql.*;

public class DoctorRepositoryImpl implements DoctorRepository {
    @Override
    public void add(Doctor d) {
        String sql = "INSERT INTO doctors (name, email, specialization) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.setString(2, d.getEmail());
            ps.setString(3, d.getSpecialization());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public Doctor getById(int id) {
        String sql = "SELECT * FROM doctors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setSpecialization(rs.getString("specialization"));
                return d;
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM doctors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}