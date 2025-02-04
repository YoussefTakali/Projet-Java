package com.initial.controller;

import com.initial.model.Patient;
import com.initial.service.PatientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientSignupController {
    private final PatientService patientService = new PatientService();

    @FXML
    private TextField firstNameField;
    
    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField addressField;

     @FXML
    private ImageView imageView;


    @FXML
    public void initialize() {

        Image imageUrl = new Image(getClass().getResourceAsStream("login-image.jpg"));

        imageView.setImage(imageUrl);

    }

    @FXML
    public void handleSignUp(ActionEvent event) {
        // Get input values
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = passwordField.getText();
        String address = addressField.getText().trim();

        // Validate inputs
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || address.isEmpty()) {
            showAlert("Validation Error", "All fields are required.", Alert.AlertType.WARNING);
            return;
        }

        // Create Patient object
        Patient newPatient = new Patient(0L,firstName, lastName, email, phone, password, address);

        // Add patient to database
        patientService.addPatient(newPatient);

        // Show success message
        showAlert("Success", "Account created successfully!", Alert.AlertType.INFORMATION);

        // Redirect to login page
        navigateToLogin(event);
    }

    public void navigateToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/login_page.fxml"));
            Parent loginView = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loginView);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load login page.", Alert.AlertType.ERROR);
        }
    }

    public void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
