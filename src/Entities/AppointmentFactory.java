package Entities;

class OnlineAppointment implements AppointmentType {
    @Override
    public String getDetails() { return "Online Consultation (via Zoom)"; }
}

class InPersonAppointment implements AppointmentType {
    @Override
    public String getDetails() { return "In-Person Visit (Room 101)"; }
}

public class AppointmentFactory {
    public static AppointmentType createAppointment(String type) {
        if (type.equalsIgnoreCase("online")) return new OnlineAppointment();
        if (type.equalsIgnoreCase("person")) return new InPersonAppointment();
        return () -> "Standard Appointment";
    }
}