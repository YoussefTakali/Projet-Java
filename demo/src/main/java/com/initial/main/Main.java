package com.initial.main;
import com.initial.controller.AppointmentController;
import com.initial.controller.DoctorController;
import com.initial.model.Appointment;
import com.initial.model.AppointmentStatus;
import com.initial.model.Doctor;
import java.util.Date;

import com.initial.service.AppointmentService;
public class Main {
    public static void main(String[] args) {
        DoctorController doctorController = new DoctorController();

        Doctor newDoctor = new Doctor();
        newDoctor.setUsername("dr_jane");
        newDoctor.setPassword("password123");
        newDoctor.setEmail("dr.jane@example.com");
        newDoctor.setFirstName("Jane");
        newDoctor.setLastName("Smith");
        newDoctor.setSpecialization("Neurologist");
        newDoctor.setDepartmentId(1L);  // Assuming department ID is 1

        AppointmentService appointmentService = new AppointmentService();

        // Test: Add a new appointment
        Appointment newAppointment = new Appointment(0, new Date(), AppointmentStatus.SCHEDULED.toString(), 1, 1); // 1 for doctor_id and patient_id
        boolean added = appointmentService.addAppointment(newAppointment);
        if (added) {
            System.out.println("Appointment added successfully.");
        }

        // Test: Get the status of the appointment
        Appointment fetchedAppointment = appointmentService.getAppointment(1); // Assuming the appointment ID is 1
        if (fetchedAppointment != null) {
            System.out.println("Fetched Appointment Status: " + fetchedAppointment.getStatus());
        }

        // Test: Cancel an appointment
        appointmentService.cancelAppointment(1); // Assuming the appointment ID is 1

        // Test: Reschedule an appointment
        boolean rescheduled = appointmentService.rescheduleAppointment(1, new Date()); // Reschedule with a new date
        if (rescheduled) {
            System.out.println("Appointment has been rescheduled.");
        }

        // Test: Get the status again after cancellation
        AppointmentStatus status = appointmentService.getAppointmentStatus(1); // Assuming the appointment ID is 1
        System.out.println("Appointment status after update: " + status);
        }
}