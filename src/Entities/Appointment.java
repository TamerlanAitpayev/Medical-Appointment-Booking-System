package Entities;

import java.sql.Timestamp;

public class Appointment {
    private int id;
    private int patientId;
    private int doctorId;
    private Timestamp appointmentTime;
    private String status;

    public Appointment() {}

    public Appointment(int id, int patientId, int doctorId, String status, Timestamp appointmentTime) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.status = status;
        this.appointmentTime = appointmentTime;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(Timestamp timestamp) { this.appointmentTime = timestamp; }
<<<<<<< HEAD
}
=======
}
>>>>>>> 62e48312dd74b6f968f6561b4c802ee3546a9e4d
