package Factory;

import Entities.Appointment;
import java.sql.Timestamp;

/**
 * Factory class for creating Appointment objects
 * This class follows the Factory Pattern
 */
public class AppointmentFactory {

    // Private constructor (so no one creates object directly)
    private AppointmentFactory() {}

    /**
     * Create a new Appointment
     */
    public static Appointment createAppointment(
            int patientId,
            int doctorId,
            Timestamp appointmentTime
    ) {
        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setStatus("PENDING");

        return appointment;
    }
}
