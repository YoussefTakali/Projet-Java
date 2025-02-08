package com.initial.controller;

import com.initial.model.Doctor;
import com.initial.service.DoctorService;
import com.initial.service.DepartmentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class DoctorListController {
    private final DoctorService doctorService = new DoctorService();
    private final DepartmentService departmentService = new DepartmentService();

    @FXML private FlowPane doctorsContainer;
    @FXML private Label departmentNameLabel;

    public void loadDoctorsForDepartment(int departmentId) {
        // Get department name
        String departmentName = departmentService.getDepartmentById(departmentId).getName();
        departmentNameLabel.setText(departmentName + " Department");

        // Load doctors
        List<Doctor> doctors = doctorService.getDoctorsByDepartment(departmentId);
        doctorsContainer.getChildren().clear();

        for (Doctor doctor : doctors) {
            VBox doctorCard = createDoctorCard(doctor);
            doctorsContainer.getChildren().add(doctorCard);
        }
    }

    private VBox createDoctorCard(Doctor doctor) {
        VBox card = new VBox();
        card.getStyleClass().add("doctor-card");
        
        Label nameLabel = new Label("Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
        nameLabel.getStyleClass().add("doctor-name");
        
        Label specializationLabel = new Label(doctor.getSpecialization());
        specializationLabel.getStyleClass().add("doctor-specialization");
        
        Label emailLabel = new Label(doctor.getEmail());
        emailLabel.getStyleClass().add("doctor-email");
        
        card.getChildren().addAll(nameLabel, specializationLabel, emailLabel);
        return card;
    }

    @FXML
    private void navigateBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/patient.fxml"));
            Parent departmentsView = loader.load();
            Stage stage = (Stage) doctorsContainer.getScene().getWindow();
            Scene scene = new Scene(departmentsView);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToAboutUs() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/about-us.fxml"));
            Parent aboutUsView = loader.load();
            Stage stage = (Stage) doctorsContainer.getScene().getWindow();
            Scene scene = new Scene(aboutUsView);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToAppointments() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/appointments.fxml"));
            Parent appointmentsView = loader.load();
            Stage stage = (Stage) doctorsContainer.getScene().getWindow();
            Scene scene = new Scene(appointmentsView);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}