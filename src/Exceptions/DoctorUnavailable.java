package Exceptions;

public class DoctorUnavailable extends RuntimeException {
    public DoctorUnavailable() {
        super("Doctor not found");
    }
    public DoctorUnavailable(String message) {super (message);}
}
