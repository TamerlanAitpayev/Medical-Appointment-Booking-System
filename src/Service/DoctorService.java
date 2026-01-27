package Service;
import Entities.Doctor;
import Exceptions.ValidationException;
import Repositories.DoctorRepository;
import Exceptions.DoctorUnavailable;


public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService (DoctorRepository doctorRepository){
        this.doctorRepository=doctorRepository;
    }
    public void RegisterDoctor(Doctor doctor){
        if (doctor.getName() == null || doctor.getName().isEmpty()){
            throw new ValidationException("The name cannot be empty");
        }
        if (doctor.getEmail() == null || doctor.getEmail().isEmpty()){
            throw new ValidationException("The email cannot be empty");
        }
        if (doctor.getSpecialization() == null || doctor.getSpecialization().isEmpty()){
            throw new ValidationException("Specialization cannot be empty");
        }
        doctorRepository.add(doctor);
        System.out.println("Doctor " + doctor.getName() + " added to the system.");
    }
}
