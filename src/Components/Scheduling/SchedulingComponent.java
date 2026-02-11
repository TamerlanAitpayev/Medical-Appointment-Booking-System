package Components.Scheduling;

import Entities.*;
import Components.Notifications.NotificationComponent;

public class SchedulingComponent {
    private NotificationComponent notifier = new NotificationComponent();

    public void createBooking(int pId, int dId, String typeStr) {
        AppointmentType type = AppointmentFactory.createAppointment(typeStr);

        System.out.println("[SchedulingComponent] Booking created: " + type.getDetails());
        notifier.send("New appointment scheduled for Patient #" + pId);
    }
}