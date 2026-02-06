    package Exceptions;

    public class AppointmentNotFound extends RuntimeException {
        public AppointmentNotFound() {super ("Appointment not found in the database.");}
        public AppointmentNotFound(String message) {
            super(message);
        }
    }
