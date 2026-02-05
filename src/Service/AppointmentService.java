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
        Appointment app = new Appointment();
        app.setPatientId(patientId);
        app.setDoctorId(doctorId);
        Timestamp appointmentTime = Timestamp.valueOf(timeStr);
        app.setAppointmentTime(appointmentTime);
        app.setStatus("Scheduled");
        if (app.getAppointmentTime().before(new Timestamp(System.currentTimeMillis()))) {
            throw new AppointmentNotFound("Cannot schedule in the past!");
        }

        appointmentRepository.add(app);
        System.out.println("Appointment saved successfully!");
    }
}