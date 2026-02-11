package Service;

import Entities.Patient;
import Repositories.implementations.PatientRepositoryImpl;
import Utils.Result;

public class PatientService {
    private PatientRepositoryImpl patientRepo;

    public PatientService(PatientRepositoryImpl patientRepo) {
        this.patientRepo = patientRepo;
    }

    public Result<Patient> RegisterPatient(Patient p) {
        if (p.getName() == null || p.getName().isEmpty()) {
            return Result.error("Name cannot be empty!");
        }
        if (p.getAge() < 0) {
            return Result.error("Invalid age!");
        }

        patientRepo.add(p);
        return Result.success(p);
    }
}