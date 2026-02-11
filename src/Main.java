import Components.BillingComponent;
import Components.NotificationComponent;
import Components.PatientRecordsComponent;
import Components.SchedulingComponent;
import Entities.*;
import Repositories.implementations.*;
import Service.*;
import edu.aitu.oop3.db.DatabaseInitializer;
import Config.ClinicConfig;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initDatabase();

        ClinicConfig config = ClinicConfig.getInstance();

        PatientRepositoryImpl patientRepo = new PatientRepositoryImpl();
        DoctorRepositoryImpl doctorRepo = new DoctorRepositoryImpl();
        AppointmentRepositoryImpl appRepo = new AppointmentRepositoryImpl();

        PatientService patientService = new PatientService(patientRepo);
        DoctorService doctorService = new DoctorService(doctorRepo);
        AppointmentService appointmentService = new AppointmentService(appRepo);

        SchedulingComponent schedulingComponent = new SchedulingComponent(appointmentService);
        PatientRecordsComponent patientRecordsComponent = new PatientRecordsComponent(patientService, patientRepo);
        BillingComponent billingComponent = new BillingComponent();
        NotificationComponent notificationComponent = new NotificationComponent();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- " + config.getClinicName().toUpperCase() + " ---");
            System.out.println("1. Register Doctor");
            System.out.println("2. Register Patient");
            System.out.println("3. Find Doctor by ID");
            System.out.println("4. Find Patient by ID");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Delete Patient");
            System.out.println("7. Book Appointment (Factory & Builder Demo)");
            System.out.println("8. Show All Appointments (JOIN Demo)");
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
                        patientRecordsComponent.register(p);
                        break;

                    case 3:
                        System.out.print("Enter Doctor ID: ");
                        int docId = Integer.parseInt(sc.nextLine());
                        Doctor foundDoc = doctorRepo.getById(docId);
                        if (foundDoc != null) {
                            System.out.println("Found: Dr. " + foundDoc.getName());
                        } else {
                            System.out.println("Doctor not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Patient ID: ");
                        int patId = Integer.parseInt(sc.nextLine());
                        Patient foundPat = patientRecordsComponent.findById(patId);
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
                        patientRecordsComponent.deleteById(Integer.parseInt(sc.nextLine()));
                        break;

                    case 7:
                        System.out.print("Patient ID: "); int pid = Integer.parseInt(sc.nextLine());
                        System.out.print("Doctor ID: "); int did = Integer.parseInt(sc.nextLine());
                        System.out.print("Time (YYYY-MM-DD HH:MM:SS): "); String timeStr = sc.nextLine();

                        System.out.print("Appointment Type (online/person): ");
                        String typeStr = sc.nextLine();
                        AppointmentType type = AppointmentFactory.createAppointment(typeStr);

                        Appointment createdAppointment = schedulingComponent.schedule(pid, did, timeStr, type);

                        AppointmentSummary summary = new AppointmentSummary.Builder()
                                .setPatient("ID: " + pid)
                                .setDoctor("ID: " + did)
                                .setDate(timeStr)
                                .setNotes("Type: " + type.getDetails())
                                .build();

                        notificationComponent.sendBookingConfirmation(createdAppointment);
                        System.out.println(billingComponent.createInvoice(createdAppointment));

                        System.out.println("\n--- Appointment Summary ---");
                        System.out.println(summary.toString());
                        break;

                    case 8:
                        schedulingComponent.showAllAppointments();
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
