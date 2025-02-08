package com.initial.model;

import java.util.Date;

public class Appointment {
    private long id; // ID of the appointment
    private Date dateTime; // Date and time of the appointment
    private String status; // Status of the appointment (e.g., SCHEDULED, CANCELED, etc.)
    private long doctorId; // ID of the doctor (foreign key)
    private long patientId; // ID of the patient (foreign key)

    // Constructor with all fields
    public Appointment(long id, Date dateTime, String status, long doctorId, long patientId) {
        this.id = id;
        this.dateTime = dateTime;
        this.status = status;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }
    public Appointment(){}
    // Getters and setters for each field
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", status='" + status + '\'' +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                '}';
    }

}
