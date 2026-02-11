package Components.Billing;

import Entities.Appointment;

public class BillingComponent {
    public String createInvoice(Appointment appointment, String type) {
        double amount = calculateAmount(type);
        return "\n--- BILLING INVOICE ---\n" +
                "Patient ID: " + appointment.getPatientId() + "\n" +
                "Amount Due: $" + String.format("%.2f", amount) + "\n" +
                "Status: PENDING\n" +
                "----------------------";
    }

    private double calculateAmount(String type) {
        if (type == null) return 45.0;
        if (type.toLowerCase().contains("online")) return 40.0;
        if (type.toLowerCase().contains("person")) return 55.0;
        return 45.0;
    }
}