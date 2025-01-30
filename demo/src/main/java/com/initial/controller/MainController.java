package com.initial.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.initial.service.*;
import com.initial.model.*;

public class MainController {
    @FXML private TextField patientFirstName, patientLastName, patientEmail, patientPhone, patientPassword, patientAddress;
    @FXML private TableView<Patient> patientTable;
    // ... other FXML elements

    private PatientService patientService;
    private DoctorService doctorService;
    private AppointmentService appointmentService;
    private DepartmentService departmentService;

    public MainController() {
        patientService = new PatientService();
        doctorService = new DoctorService();
        appointmentService = new AppointmentService();
        departmentService = new DepartmentService();
    }

    @FXML
    private void initialize() {
        // Initialize tables, combo boxes, etc.
    }

    @FXML
    private void addPatient() {
        Patient patient = new Patient();
        patient.setFirstName(patientFirstName.getText());
        patient.setLastName(patientLastName.getText());
        patient.setEmail(patientEmail.getText());
        patient.setPhoneNumber(patientPhone.getText());
        patient.setPassword(patientPassword.getText());
        patient.setAddress(patientAddress.getText());
        patientService.addPatient(patient);
        refreshPatientTable();
    }

    // ... other methods for updating, deleting, etc.

    private void refreshPatientTable() {
        patientTable.getItems().setAll(patientService.getAllPatients());
    }

    // ... similar methods for doctors, appointments, and departments
}