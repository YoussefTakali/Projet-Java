package com.initial.controller;

import com.initial.model.Appointment;
import com.initial.model.Doctor;
import com.initial.service.AppointmentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSchedulingController {
    private final AppointmentService appointmentService = new AppointmentService();
    private Doctor selectedDoctor;
    private Long patientId; // This should be set from your login session

    @FXML private Label doctorNameLabel;
    @FXML private DatePicker datePicker;
    @FXML private FlowPane timeSlotsContainer;
    @FXML private Label statusLabel;

    public void initialize() {
        // Set minimum date to today
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
    }

    public void initData(Doctor doctor, Long patientId) {
        this.selectedDoctor = doctor;
        this.patientId = patientId;
        doctorNameLabel.setText("Schedule Appointment with Dr. " + 
                              doctor.getFirstName() + " " + doctor.getLastName());
    }

    @FXML
    private void onDateSelected() {
        if (datePicker.getValue() != null) {
            displayTimeSlots();
        }
    }

    private void displayTimeSlots() {
        timeSlotsContainer.getChildren().clear();
        LocalDate selectedDate = datePicker.getValue();
        
        // Generate time slots from 9 AM to 5 PM
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        
        while (startTime.isBefore(endTime)) {
            LocalTime timeSlot = startTime;
            Button timeButton = createTimeSlotButton(timeSlot, selectedDate);
            timeSlotsContainer.getChildren().add(timeButton);
            startTime = startTime.plusHours(1);
        }
    }

    private Button createTimeSlotButton(LocalTime time, LocalDate date) {
        Button button = new Button(time.toString());
        button.getStyleClass().add("time-slot-button");
        
        // Check if slot is already booked
        if (isTimeSlotBooked(date, time)) {
            button.getStyleClass().add("booked");
            button.setDisable(true);
        } else {
            button.setOnAction(e -> scheduleAppointment(date, time));
        }
        
        return button;
    }

    private boolean isTimeSlotBooked(LocalDate date, LocalTime time) {
        // Implement your logic to check if the time slot is already booked
        // This would typically involve checking your appointments database
        return false; // Placeholder return
    }

    private void scheduleAppointment(LocalDate date, LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        Date appointmentDate = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

        Appointment appointment = new Appointment();
        appointment.setDateTime(appointmentDate);
        appointment.setStatus("SCHEDULED");
        appointment.setDoctorId(selectedDoctor.getId());
        appointment.setPatientId(patientId);

        if (appointmentService.addAppointment(appointment)) {
            showSuccessAlert();
            navigateBack();
        } else {
            showErrorAlert();
        }
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Appointment scheduled successfully!");
        alert.showAndWait();
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Failed to schedule appointment. Please try again.");
        alert.showAndWait();
    }

    @FXML
    private void navigateBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/patient.fxml"));
            Parent doctorsView = loader.load();
            Stage stage = (Stage) datePicker.getScene().getWindow();
            Scene scene = new Scene(doctorsView);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}