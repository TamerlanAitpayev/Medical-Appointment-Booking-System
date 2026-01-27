package Exceptions;

public class TimeSlotAlreadyBooked extends RuntimeException {
    public TimeSlotAlreadyBooked() {super("This time slot has already been booked");}
    public TimeSlotAlreadyBooked(String message) {super(message);}
}
