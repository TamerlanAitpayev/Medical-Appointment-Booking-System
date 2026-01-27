package Repositories;
import Entities.Patient;

public interface PatientRepository {
    void add(Patient patient);
    Entities.Patient getById(int id);
    void deleteById(int id);
}