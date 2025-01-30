package com.initial.controller;
import com.initial.model.Appointment;
import com.initial.model.AppointmentStatus;
import com.initial.service.AppointmentService;
import java.util.Date;
import java.util.List;

public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController() {
        // Initialize the AppointmentService
        this.appointmentService = new AppointmentService();
    }

    // Method to cancel an appointment
    public void cancelAppointment(int appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
    }

    // Method to reschedule an appointment
    public boolean rescheduleAppointment(int appointmentId, Date newDate) {
        return appointmentService.rescheduleAppointment(appointmentId, newDate);
    }

    // Method to get the status of an appointment
    public AppointmentStatus getAppointmentStatus(int appointmentId) {
        return appointmentService.getAppointmentStatus(appointmentId);
    }

    // Method to add a new appointment
    public boolean addAppointment(Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    // Method to delete an appointment
    public boolean deleteAppointment(int appointmentId) {
        return appointmentService.deleteAppointment(appointmentId);
    }

    // Method to retrieve an appointment by its ID
    public Appointment getAppointment(int appointmentId) {
        return appointmentService.getAppointment(appointmentId);
    }
    public List<Appointment> getAppointmentsByPatientId(long patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctorId(long doctorId) {
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }

}
