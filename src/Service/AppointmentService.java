package Service;

import Entities.Appointment;
import Exceptions.AppointmentNotFound;
import Repositories.AppointmentRepository;
import java.sql.Timestamp;

public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository repo) {
        this.appointmentRepository = repo;
    }

    public void scheduleAppointment(int patientId, int doctorId, String timeStr) {
        try {
            Timestamp appointmentTime = Timestamp.valueOf(timeStr);
            if (appointmentTime.before(new Timestamp(System.currentTimeMillis()))) {
                throw new AppointmentNotFound("Cannot schedule in the past!");
            }

            Appointment app = new Appointment();
            app.setPatientId(patientId);
            app.setDoctorId(doctorId);
            app.setAppointmentTime(appointmentTime);
            app.setStatus("Scheduled");

            appointmentRepository.add(app);
            System.out.println("Appointment saved successfully!");
        } catch (IllegalArgumentException e) {
            throw new AppointmentNotFound("Invalid time format. Use YYYY-MM-DD HH:MM:SS");
        }
    }

    public void showAllAppointments() {
        appointmentRepository.findAllDetailed();
    }
}