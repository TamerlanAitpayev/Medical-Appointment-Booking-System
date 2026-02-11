import Entities.*;
import Repositories.implementations.*;
import Service.*;
import Utils.Result;
import edu.aitu.oop3.db.DatabaseInitializer;
import Config.ClinicConfig;
import Components.Billing.BillingComponent;
import Components.Notifications.NotificationComponent;
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
            int choice = Integer.parseInt(sc.nextLine());

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
                        Patient p = new Patient();
                        System.out.print("First Name: "); p.setName(sc.nextLine());
                        System.out.print("Last Name: "); p.setLastName(sc.nextLine());
                        System.out.print("Age: "); p.setAge(Integer.parseInt(sc.nextLine()));
                        System.out.print("Email: "); p.setEmail(sc.nextLine());
                        Result<Patient> res = patientService.RegisterPatient(p);
                        if (res.isSuccess()) System.out.println("Success: " + res.getData().getName());
                        else System.out.println("Error: " + res.getMessage());
                        break;
                    case 7:
                        System.out.print("Patient ID: "); int pid = Integer.parseInt(sc.nextLine());
                        System.out.print("Doctor ID: "); int did = Integer.parseInt(sc.nextLine());
                        System.out.print("Time: "); String timeStr = sc.nextLine();
                        System.out.print("Type (online/person): "); String typeStr = sc.nextLine();

                        AppointmentType type = AppointmentFactory.createAppointment(typeStr);
                        appointmentService.scheduleAppointment(pid, did, timeStr);

                        AppointmentSummary summary = new AppointmentSummary.Builder()
                                .setPatient("ID: " + pid).setDoctor("ID: " + did)
                                .setDate(timeStr).setNotes(type.getDetails()).build();

                        Appointment temp = new Appointment();
                        temp.setPatientId(pid); temp.setDoctorId(did);

                        System.out.println(summary.toString());
                        System.out.println(billing.createInvoice(temp, typeStr));
                        notifier.send("Confirmed for Patient " + pid);
                        break;
                    case 8:
                        appointmentService.showAllAppointments();
                        break;
                    case 0:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}