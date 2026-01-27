package Repositories.implementations;

import Entities.Appointment;
import Repositories.AppointmentRepository;
import Repositories.DoctorRepository;
import edu.aitu.oop3.db.DatabaseConnection;
import org.checkerframework.checker.units.qual.A;

import javax.swing.plaf.SpinnerUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentRepositoryImpl implements AppointmentRepository {
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
