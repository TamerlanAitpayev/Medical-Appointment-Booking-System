import Entities.*;
import Repositories.implementations.*;
import Service.*;
import Utils.Result;
import edu.aitu.oop3.db.DatabaseInitializer;
import Config.ClinicConfig;
import Components.Billing.BillingComponent;
import Components.Notifications.NotificationComponent;
import Factory.AppointmentFactory;
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

        BillingComponent billing = new BillingComponent();
        NotificationComponent notifier = new NotificationComponent();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- " + config.getClinicName().toUpperCase() + " ---");
            System.out.println("1. Register Doctor\n2. Register Patient\n3. Find Doctor\n4. Find Patient\n5. Delete Doctor\n6. Delete Patient\n7. Book Appointment\n8. Show All\n0. Exit");
            System.out.print("Select: ");

            String input = sc.nextLine();
            if (input.isEmpty()) continue;
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        Doctor d = new Doctor();
                        System.out.print("Name: "); d.setName(sc.nextLine());
                        System.out.print("Email: "); d.setEmail(sc.nextLine());
                        System.out.print("Spec: "); d.setSpecialization(sc.nextLine());
                        doctorService.RegisterDoctor(d);
                        break;
                    case 2:
                        System.out.print("First Name: "); String fname = sc.nextLine();
                        System.out.print("Last Name: "); String lname = sc.nextLine();
                        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine());
                        System.out.print("Email: "); String email = sc.nextLine();
                        System.out.print("Phone: "); String phone = sc.nextLine();
                        Patient p = new Patient(fname, lname, age, email, phone);
                        patientService.RegisterPatient(p);
                        break;
                    case 3:
                        System.out.print("Enter Doctor ID: ");
                        int docId = Integer.parseInt(sc.nextLine());
                        doctorService.findDoctor(docId);
                        break;
                    case 4:
                        System.out.print("Enter Patient ID: ");
                        int patId = Integer.parseInt(sc.nextLine());
                        patientService.findPatient(patId);
                        break;
                    case 5:
                        System.out.print("Enter Doctor ID to delete: ");
                        doctorService.deleteDoctor(Integer.parseInt(sc.nextLine()));
                        break;
                    case 6:
                        System.out.print("Enter Patient ID to delete: ");
                        patientService.deletePatient(Integer.parseInt(sc.nextLine()));
                        break;
                    case 7:
                        System.out.print("Patient ID: "); int pid = Integer.parseInt(sc.nextLine());
                        System.out.print("Doctor ID: "); int did = Integer.parseInt(sc.nextLine());
                        System.out.print("Time (YYYY-MM-DD HH:MM:SS): "); String timeStr = sc.nextLine();
                        appointmentService.scheduleAppointment(pid, did, timeStr);
                        break;
                    case 8:
                        System.out.println("\n--- ALL APPOINTMENTS ---");
                        appointmentService.showAllAppointments();
                        break;
                    case 0:
                        return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid ID number, not text!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}