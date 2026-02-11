package Components;

import Entities.Appointment;

public class BillingComponent {

    public String createInvoice(Appointment appointment) {
        double amount = calculateAmount(appointment.getType());
        return "Invoice -> Patient ID: " + appointment.getPatientId()
                + ", Doctor ID: " + appointment.getDoctorId()
                + ", Service: " + appointment.getType()
                + ", Amount: $" + String.format("%.2f", amount)
                + ", Status: UNPAID";
    }

    private double calculateAmount(String appointmentType) {
        if (appointmentType == null) {
            return 0.0;
        }

        String normalizedType = appointmentType.trim().toLowerCase();
        if (normalizedType.contains("online")) {
            return 40.0;
        }
        if (normalizedType.contains("in-person") || normalizedType.contains("person")) {
            return 55.0;
        }
        return 45.0;
    }
}
