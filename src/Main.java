import Entities.Doctor;
import Entities.Patient;
import Repositories.implementations.AppointmentRepositoryImpl;
import Repositories.implementations.DoctorRepositoryImpl;
import Repositories.implementations.PatientRepositoryImpl;
import Service.AppointmentService;
import Service.DoctorService;
import Service.PatientService;
import edu.aitu.oop3.db.DatabaseInitializer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initDatabase();
        PatientRepositoryImpl patientRepo = new PatientRepositoryImpl();
        DoctorRepositoryImpl doctorRepo = new DoctorRepositoryImpl();
        AppointmentRepositoryImpl appRepo = new AppointmentRepositoryImpl();

        PatientService patientService = new PatientService(patientRepo);
        DoctorService doctorService = new DoctorService(doctorRepo);
        AppointmentService appointmentService = new AppointmentService(appRepo);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MEDICAL BOOKING SYSTEM ---");
            System.out.println("1. Register Doctor");
            System.out.println("2. Register Patient");
            System.out.println("3. Find Doctor by ID");
            System.out.println("4. Find Patient by ID");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Delete Patient");
            System.out.println("7. Book Appointment");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1:
                        Doctor d = new Doctor();
                        System.out.print("Doctor Name: "); d.setName(sc.nextLine());
                        System.out.print("Doctor Email: "); d.setEmail(sc.nextLine());
                        System.out.print("Specialization: "); d.setSpecialization(sc.nextLine());
                        doctorService.RegisterDoctor(d);
                        System.out.println("Doctor successfully added to Supabase!");
                        break;

                    case 2:
                        Patient p = new Patient();
                        System.out.print("Name: "); p.setName(sc.nextLine());
                        System.out.print("Last Name: "); p.setLastName(sc.nextLine());
                        System.out.print("Age: "); p.setAge(Integer.parseInt(sc.nextLine()));
                        System.out.print("Gender: "); p.setGender(sc.nextLine());
                        System.out.print("Weight: "); p.setWeight(Double.parseDouble(sc.nextLine()));
                        System.out.print("Height: "); p.setHeight(Double.parseDouble(sc.nextLine()));
                        System.out.print("Email: "); p.setEmail(sc.nextLine());
                        System.out.print("Phone: "); p.setPhoneNumber(sc.nextLine());
                        patientService.RegisterPatient(p);
                        System.out.println("Patient successfully added to Supabase!");
                        break;

                    case 3:
                        System.out.print("Enter Doctor ID: ");
                        int docId = Integer.parseInt(sc.nextLine());
                        Doctor foundDoc = doctorRepo.getById(docId);
                        if (foundDoc != null) {
                            System.out.println("Found: Dr. " + foundDoc.getName() + " [" + foundDoc.getSpecialization() + "]");
                        } else {
                            System.out.println("Doctor not found in Database.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Patient ID: ");
                        int patId = Integer.parseInt(sc.nextLine());
                        Patient foundPat = patientRepo.getById(patId);
                        if (foundPat != null) {
                            System.out.println("Found: " + foundPat.getName() + " " + foundPat.getLastName());
                        } else {
                            System.out.println("Patient not found.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter Doctor ID to delete: ");
                        doctorRepo.deleteById(Integer.parseInt(sc.nextLine()));
                        break;

                    case 6:
                        System.out.print("Enter Patient ID to delete: ");
                        patientRepo.deleteById(Integer.parseInt(sc.nextLine()));
                        break;

                    case 7:
                        System.out.print("Patient ID: "); int pid = Integer.parseInt(sc.nextLine());
                        System.out.print("Doctor ID: "); int did = Integer.parseInt(sc.nextLine());
                        System.out.print("Time (format YYYY-MM-DD HH:MM:SS): ");
                        String timeStr = sc.nextLine();
                        appointmentService.scheduleAppointment(pid, did, timeStr);
                        break;

                    case 0:
                        System.out.println("Shutdown...");
                        return;

                    default:
                        System.out.println("Wrong choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}