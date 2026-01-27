package Service;
import Entities.Patient;
import Repositories.PatientRepository;
import Exceptions.ValidationException;

public class PatientService {
    private PatientRepository  patientRepository;

    public PatientService (PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }
    public void RegisterPatient(Patient patient){
        if (patient.getName() == null || patient.getName().isEmpty()) {
            throw new ValidationException("The name cannot be empty");
        }
        if (patient.getLastName() == null || patient.getLastName().isEmpty()) {
            throw new ValidationException("The lastname cannot be empty");
        }
        if (patient.getAge() < 0 || patient.getAge() > 150){
            throw new ValidationException("Incorrect age");
        }
        if (patient.getWeight() < 0 || patient.getHeight() < 0){
            throw new ValidationException("Incorrect weight and height");
        }
        if (patient.getGender() != null) {
            String g = patient.getGender().trim().toLowerCase();
            if (!(g.equals("male") || g.equals("female"))) {throw new ValidationException("Gender must be 'male' or 'female'");}
            patient.setGender(g);
        } else {throw new ValidationException("Gender cannot be empty");}
        if (patient.getPhoneNumber() == null || patient.getPhoneNumber().isEmpty()){
            throw new ValidationException("The phone number cannot be empty");
        }
        patientRepository.add(patient);
                System.out.println("Patient successfully validated and sent to repository");
    }

}
