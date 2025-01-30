package com.initial.main;

import com.initial.controller.AppointmentController;
import com.initial.controller.DoctorController;
import com.initial.model.Appointment;
import com.initial.model.AppointmentStatus;
import com.initial.model.Doctor;
import com.initial.model.Patient;

import java.util.Date;
import java.util.List;

import com.initial.service.AppointmentService;
import com.initial.service.DoctorService;
import com.initial.service.PatientService;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/MainView.fxml"));
            Parent root = loader.load();

            // Set the scene
            Scene scene = new Scene(root);

            // Set the stage
            primaryStage.setTitle("Hospital Management System");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Your existing code
        // PatientService patientService = new PatientService();
        // AppointmentService appointmentService = new AppointmentService();
        // Doctor doctor = new Doctor();
        // doctor.setPassword("doctor123");
        // doctor.setEmail("doctor@example.com");
        // doctor.setFirstName("John");
        // doctor.setLastName("Doe");
        // doctor.setSpecialization("Cardiology");
        // doctor.setDepartmentId(1);

        // DoctorService doctorService = new DoctorService();
        // doctorService.addDoctor(doctor);
        
        // System.out.println("Doctor added successfully!");

        // Patient newPatient = new Patient();
        // newPatient.setPassword("mypassword");
        // newPatient.setEmail("youssef@example.com");
        // newPatient.setFirstName("Mike");
        // newPatient.setLastName("Smith");
        // newPatient.setAddress("Roued");
        // newPatient.setPhoneNumber("123456789");

        // patientService.addPatient(newPatient);
        // System.out.println("Patient added successfully!");

        // Appointment newAppointment = new Appointment(0, new Date(), AppointmentStatus.SCHEDULED.toString(), 19, 5);
        // boolean added = appointmentService.addAppointment(newAppointment);
        // if (added) {
        //     System.out.println("Appointment added successfully.");
        // }

        // List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(18);
        // System.out.println("All Appointments:");
        // for (Appointment app : appointments) {
        //     System.out.println(app);
        // }
        // List<Appointment> appointments2 = appointmentService.getAppointmentsByPatientId(5);
        // System.out.println("All Appointments:");
        // for (Appointment app : appointments2) {
        //     System.out.println(app);
        // }

        // System.out.println(patientService.loginPatient("youssef@example.com", "mypassword"));
        // System.out.println(doctorService.loginDoctor("dr.jane@example.com", "password123"));

        // Launch the JavaFX application
        launch(args);
    }
}