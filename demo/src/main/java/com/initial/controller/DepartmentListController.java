package com.initial.controller;

import com.initial.model.Department;
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

public class DepartmentListController {
    private final DepartmentService departmentService = new DepartmentService();

    @FXML
    private FlowPane departmentsContainer;

    @FXML
    public void initialize() {
        loadDepartments();
    }

    private void loadDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        departmentsContainer.getChildren().clear();

        for (Department department : departments) {
            VBox departmentCard = createDepartmentCard(department);
            departmentsContainer.getChildren().add(departmentCard);
        }
    }

    private VBox createDepartmentCard(Department department) {
        VBox card = new VBox();
        card.getStyleClass().add("department-card");
        
        Label nameLabel = new Label(department.getName());
        nameLabel.getStyleClass().add("department-name");
        
        Label descriptionLabel = new Label(department.getDescription());
        descriptionLabel.getStyleClass().add("department-description");
        descriptionLabel.setWrapText(true);
        
        card.getChildren().addAll(nameLabel, descriptionLabel);
        
        card.setOnMouseClicked(event -> navigateToDoctors(department.getId()));
        
        return card;
    }

    private void navigateToDoctors(int departmentId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/doctorBydepartement.fxml"));
            Parent doctorsView = loader.load();
            
            DoctorListController controller = loader.getController();
            controller.loadDoctorsForDepartment(departmentId);
            
            Stage stage = (Stage) departmentsContainer.getScene().getWindow();
            Scene scene = new Scene(doctorsView);
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
            Stage stage = (Stage) departmentsContainer.getScene().getWindow();
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
            Stage stage = (Stage) departmentsContainer.getScene().getWindow();
            Scene scene = new Scene(appointmentsView);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void navigateTodepartemnt() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/patient.fxml"));
            Parent appointmentsView = loader.load();
            Stage stage = (Stage) departmentsContainer.getScene().getWindow();
            Scene scene = new Scene(appointmentsView);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}