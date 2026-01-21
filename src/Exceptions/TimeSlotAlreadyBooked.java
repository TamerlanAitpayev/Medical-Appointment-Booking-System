package Exceptions;

public class TimeSlotAlreadyBooked extends RuntimeException {
    public TimeSlotAlreadyBooked(String message) {
        super(message);
    }
}
