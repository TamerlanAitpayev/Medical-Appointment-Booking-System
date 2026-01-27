package Repositories;
import Entities.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository {
 void add(Appointment appointment);
}