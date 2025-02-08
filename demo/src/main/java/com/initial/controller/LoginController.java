package com.initial.controller;

import com.initial.model.Doctor;
import com.initial.model.Patient;
import com.initial.service.DoctorService;
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
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    private final PatientService patientService = new PatientService();
    private final DoctorService doctorService = new DoctorService();
    @FXML
    private TextField usernameField; 

    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        if (imageView == null) {
            System.err.println("ERROR: ImageView is NULL! FXML might not be properly loaded.");
            return; // Exit if ImageView is not injected properly
        }

        Image imageUrl = new Image(getClass().getResourceAsStream("login-image.jpg"));

        imageView.setImage(imageUrl);

    }
    @FXML
    public void navigateToSignUp(ActionEvent event) throws IOException {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/com/initial/view/sign-up.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(signUpRoot));
        stage.show();
    }

@FXML
public void handleLogin(ActionEvent event) {
    String email = usernameField.getText();
    String password = passwordField.getText();

    // Check if the user is a patient
    Patient patient = patientService.loginPatient(email, password);
    if (patient != null) {
        System.out.println("hello patient");
        loadView(event, "/com/initial/view/patient.fxml");

        return;
    }

    // Check if the user is a doctor
    Doctor doctor = doctorService.loginDoctor(email, password);
    if (doctor != null) {
        System.out.println("hello docteur");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/doctor.fxml"));
            Parent loginView = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loginView);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load login page.", Alert.AlertType.ERROR);
        }

        return;
    }

    // If neither patient nor doctor exists, show error
    showAlert("Login Failed", "Invalid credentials. Please try again.", Alert.AlertType.ERROR);
}

// Generic method to load a view
private void loadView(ActionEvent event, String fxmlPath) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent view = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        showAlert("Error", "Unable to load the requested view.", Alert.AlertType.ERROR);
    }
}

// Alert helper method
private void showAlert(String title, String message, Alert.AlertType type) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

  

}
