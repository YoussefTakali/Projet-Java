package com.initial.controller;

import com.initial.model.Appointment;
import com.initial.model.AppointmentStatus;
import com.initial.service.AppointmentService;

import java.util.Date;

public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController() {
        // Initialize the AppointmentService
        this.appointmentService = new AppointmentService();
    }

    // Method to cancel an appointment
    public void cancelAppointment(int appointmentId) {
        appointmentService.cancelAppointment(appointmentId, AppointmentStatus.CANCELED.toString());
    }

    // Method to reschedule an appointment
    public boolean rescheduleAppointment(int appointmentId, Date newDate) {
        return appointmentService.rescheduleAppointment(appointmentId, newDate, AppointmentStatus.RESCHEDULED.toString());
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
}
