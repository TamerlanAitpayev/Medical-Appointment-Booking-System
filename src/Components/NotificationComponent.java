package Components;

import Entities.Appointment;

public class NotificationComponent {

    public void sendBookingConfirmation(Appointment appointment) {
        System.out.println("[Notification] Appointment confirmed for patient "
                + appointment.getPatientId()
                + " with doctor "
                + appointment.getDoctorId()
                + " at "
                + appointment.getAppointmentTime()
                + " (" + appointment.getType() + ")");
    }
}
