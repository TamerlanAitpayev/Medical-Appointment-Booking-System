package Factory;

import Entities.Appointment;
import java.sql.Timestamp;
public class AppointmentFactory {
    private AppointmentFactory() {}
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
