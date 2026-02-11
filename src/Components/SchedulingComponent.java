package Components;

import Entities.Appointment;
import Entities.AppointmentType;
import Service.AppointmentService;

public class SchedulingComponent {
    private final AppointmentService appointmentService;

    public SchedulingComponent(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public Appointment schedule(int patientId, int doctorId, String timeStr, AppointmentType type) {
        Appointment appointment = appointmentService.scheduleAppointment(patientId, doctorId, timeStr);
        appointment.setType(type.getDetails());
        return appointment;
    }

    public void showAllAppointments() {
        appointmentService.showAllAppointments();
    }
}
