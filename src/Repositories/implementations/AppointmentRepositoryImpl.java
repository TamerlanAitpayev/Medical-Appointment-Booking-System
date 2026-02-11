package Repositories.implementations;

import Entities.Appointment;
import Repositories.AppointmentRepository;
import edu.aitu.oop3.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    @Override
    public void findAllDetailed() {
        String sql = "SELECT a.id, p.name as p_name, d.name as d_name, a.appointment_time, a.status " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.id " +
                "JOIN doctors d ON a.doctor_id = d.id";
        try (java.sql.Connection conn = edu.aitu.oop3.db.DatabaseConnection.getConnection();
             java.sql.Statement st = conn.createStatement();
             java.sql.ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- ALL APPOINTMENTS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Patient: " + rs.getString("p_name") +
                        " | Doctor: " + rs.getString("d_name") + " | Time: " + rs.getTimestamp("appointment_time"));
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Appointment appointment){
        String sql = "INSERT INTO appointments(patient_id, doctor_id, appointment_time, status) VALUES(?, ?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement prst = conn.prepareStatement(sql)){
                    prst.setInt(1, appointment.getPatientId());
                    prst.setInt(2, appointment.getDoctorId());
                    prst.setTimestamp(3, appointment.getAppointmentTime());
                    prst.setString(4, appointment.getStatus());

                    prst.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException("Database error" + e.getMessage());
        }
    }
}
