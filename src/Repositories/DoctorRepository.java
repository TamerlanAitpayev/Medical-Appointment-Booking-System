package Repositories;
import Entities.Doctor;
import Entities.Patient;
import java.util.List;

public interface DoctorRepository {
    void add(Doctor doctor);
    void deleteById(int id);
    Doctor getById(int id);
}
