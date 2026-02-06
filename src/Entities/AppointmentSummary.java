package Entities;

public class AppointmentSummary {
    private final String doctorName;
    private final String patientName;
    private final String date;
    private final String notes;

    private AppointmentSummary(Builder builder) {
        this.doctorName = builder.doctorName;
        this.patientName = builder.patientName;
        this.date = builder.date;
        this.notes = builder.notes;
    }

    public static class Builder {
        private String doctorName;
        private String patientName;
        private String date;
        private String notes;

        public Builder setDoctor(String name) { this.doctorName = name; return this; }
        public Builder setPatient(String name) { this.patientName = name; return this; }
        public Builder setDate(String date) { this.date = date; return this; }
        public Builder setNotes(String notes) { this.notes = notes; return this; }

        public AppointmentSummary build() { return new AppointmentSummary(this); }
    }

    @Override
    public String toString() {
        return "Summary: [Dr. " + doctorName + " with " + patientName + " on " + date + ". Notes: " + notes + "]";
    }
}