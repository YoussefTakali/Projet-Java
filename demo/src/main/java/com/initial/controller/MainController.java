package com.initial.controller;
import javafx.scene.Node;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import com.initial.service.*;

import java.io.IOException;

import com.initial.model.*;

public class MainController {
    @FXML private TextField patientFirstName, patientLastName, patientEmail, patientPhone, patientPassword, patientAddress;
    @FXML private TableView<Patient> patientTable;
    @FXML private TableColumn<Patient, String> idColumn, firstNameColumn, lastNameColumn, emailColumn, phoneColumn, addressColumn;
    @FXML private Button loginButton;

    private final PatientService patientService;

    public MainController() {
        this.patientService = new PatientService();
    }

    @FXML
    private void initialize() {
        if (patientTable == null) {
            System.err.println("Error: patientTable is null. Check your FXML file.");
            return;
        }

        setupTableColumns();
        refreshPatientTable();
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
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

    @FXML
    public void refreshPatientTable() {
        if (patientTable == null) {
            System.err.println("Error: patientTable is null. Cannot refresh.");
            return;
        }

        ObservableList<Patient> patients = FXCollections.observableArrayList(patientService.getAllPatients());
        patientTable.setItems(patients);
    }

@FXML
private void openLoginPage(ActionEvent event) {
    try {
        // Load login.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/login.fxml"));
        Parent loginRoot = loader.load();

        // Get current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set new scene
        stage.setScene(new Scene(loginRoot));
        stage.setTitle("Login Page");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @FXML
    private void openAboutUsPage(ActionEvent event) {
        System.out.println("About Us button clicked!");
        // Add scene-switching logic
    }
}