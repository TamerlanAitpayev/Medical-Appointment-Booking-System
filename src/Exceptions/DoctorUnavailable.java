package Exceptions;

public class DoctorUnavailable extends RuntimeException {
    public DoctorUnavailable(String message) {
        super(message);
    }
}
