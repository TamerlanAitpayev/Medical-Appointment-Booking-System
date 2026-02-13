package Components.PatientRecords;

import Entities.Patient;
import Utils.Result;

public class PatientComponent {
    public Result<Patient> getPatientData(Patient patient) {
        System.out.println("[PatientComponent] Fetching records for: " + patient.getName());
        return Result.success(patient);
    }
}