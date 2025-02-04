package com.initial.controller;

import com.initial.model.Appointment;
import com.initial.model.AppointmentStatus;
import com.initial.service.AppointmentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AppointmentController {

    @FXML private DatePicker appointmentDate;
    @FXML private ComboBox<String> appointmentDoctor;
    @FXML private ComboBox<String> appointmentPatient;
    @FXML private TableView<Appointment> appointmentTable;
    @FXML private TableColumn<Appointment, Integer> idColumn;
    @FXML private TableColumn<Appointment, Date> dateColumn;
    @FXML private TableColumn<Appointment, String> statusColumn;
    @FXML private TableColumn<Appointment, Long> doctorIdColumn;
    @FXML private TableColumn<Appointment, Long> patientIdColumn;

    private AppointmentService appointmentService;
    private ObservableList<Appointment> appointmentList;

    public AppointmentController() {
        this.appointmentService = new AppointmentService();
        this.appointmentList = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        setupTable();
        loadAppointments();
        setupComboBoxes();
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        doctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        appointmentTable.setItems(appointmentList);
    }

    private void loadAppointments() {
        appointmentList.clear();
        appointmentList.addAll(appointmentService.getAppointmentsByDoctorId(1)); // Example: load for doctor with ID 1
    }

    private void setupComboBoxes() {
        // Populate doctor and patient combo boxes (you'll need to implement these methods)
        appointmentDoctor.setItems(FXCollections.observableArrayList(getDoctorNames()));
        appointmentPatient.setItems(FXCollections.observableArrayList(getPatientNames()));
    }

    @FXML
    private void addAppointment() {
        LocalDate date = appointmentDate.getValue();
        String doctor = appointmentDoctor.getValue();
        String patient = appointmentPatient.getValue();

        if (date == null || doctor == null || patient == null) {
            showAlert("Error", "Please fill all fields");
            return;
        }

        // Convert LocalDate to Date
        Date appointmentDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // In a real application, you'd get the actual IDs based on the selected names
        long doctorId = 1; // Example
        long patientId = 1; // Example

        Appointment newAppointment = new Appointment(0, appointmentDate, AppointmentStatus.SCHEDULED.toString(), doctorId, patientId);
        
        if (appointmentService.addAppointment(newAppointment)) {
            loadAppointments();
            showAlert("Success", "Appointment added successfully");
        } else {
            showAlert("Error", "Failed to add appointment");
        }
    }

    @FXML
    private void cancelAppointment() {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            showAlert("Error", "Please select an appointment to cancel");
            return;
        }

        appointmentService.cancelAppointment((int)selectedAppointment.getId());
        loadAppointments();
        showAlert("Success", "Appointment cancelled successfully");
    }

    @FXML
    private void rescheduleAppointment() {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        LocalDate newDate = appointmentDate.getValue();

        if (selectedAppointment == null || newDate == null) {
            showAlert("Error", "Please select an appointment and a new date");
            return;
        }

        Date newAppointmentDate = Date.from(newDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        if (appointmentService.rescheduleAppointment((int)selectedAppointment.getId(), newAppointmentDate)) {
            loadAppointments();
            showAlert("Success", "Appointment rescheduled successfully");
        } else {
            showAlert("Error", "Failed to reschedule appointment");
        }
    }

    @FXML
    private void deleteAppointment() {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            showAlert("Error", "Please select an appointment to delete");
            return;
        }

        if (appointmentService.deleteAppointment((int)selectedAppointment.getId())) {
            loadAppointments();
            showAlert("Success", "Appointment deleted successfully");
        } else {
            showAlert("Error", "Failed to delete appointment");
        }
    }

    public AppointmentStatus getAppointmentStatus(int appointmentId) {
        return appointmentService.getAppointmentStatus(appointmentId);
    }

    public Appointment getAppointment(int appointmentId) {
        return appointmentService.getAppointment(appointmentId);
    }

    public List<Appointment> getAppointmentsByPatientId(long patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctorId(long doctorId) {
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // You'll need to implement these methods to get doctor and patient names
    private List<String> getDoctorNames() {
        // Implement this method to return a list of doctor names
        return List.of("Doctor 1", "Doctor 2", "Doctor 3");
    }

    private List<String> getPatientNames() {
        // Implement this method to return a list of patient names
        return List.of("Patient 1", "Patient 2", "Patient 3");
    }
}