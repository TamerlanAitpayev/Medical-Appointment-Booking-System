package Components.PatientRecords;

import Entities.Patient;
import Utils.Result;

public class PatientComponent {
    public Result<Patient> getPatientData(int id) {
        System.out.println("[PatientComponent] Fetching records for ID: " + id);
        return Result.success(new Patient());
    }
}