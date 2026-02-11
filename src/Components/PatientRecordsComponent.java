package Components;

import Entities.Patient;
import Repositories.PatientRepository;
import Service.PatientService;

public class PatientRecordsComponent {
    private final PatientService patientService;
    private final PatientRepository patientRepository;

    public PatientRecordsComponent(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    public void register(Patient patient) {
        patientService.RegisterPatient(patient);
    }

    public Patient findById(int patientId) {
        return patientRepository.getById(patientId);
    }

    public void deleteById(int patientId) {
        patientRepository.deleteById(patientId);
    }
}
