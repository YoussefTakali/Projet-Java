package com.initial.service;

import com.initial.db.DBConnection;
import com.initial.model.Appointment;
import com.initial.model.AppointmentStatus;

import java.sql.*;
import java.util.Date;

public class AppointmentService {

    private DBConnection dbConnection;

    public AppointmentService() {
        // Initialize DBConnection
        dbConnection = DBConnection.getInstance();
    }

    // Method to cancel an appointment
    public void cancelAppointment(int appointmentId) {
        String sql = "UPDATE appointment SET status = ? WHERE id = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, AppointmentStatus.CANCELED.toString()); // Use the CANCELED status
                statement.setInt(2, appointmentId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Appointment has been canceled.");
                } else {
                    System.out.println("No appointment found with ID: " + appointmentId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to reschedule an appointment
    public boolean rescheduleAppointment(int appointmentId, Date newDate) {
        String sql = "UPDATE appointment SET dateTime = ?, status = ? WHERE id = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setTimestamp(1, new Timestamp(newDate.getTime())); // Convert Date to Timestamp
                statement.setString(2, AppointmentStatus.RESCHEDULED.toString()); // Status for rescheduled
                statement.setInt(3, appointmentId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Appointment has been rescheduled.");
                    return true;
                } else {
                    System.out.println("No appointment found with ID: " + appointmentId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get the status of an appointment from the database
    public AppointmentStatus getAppointmentStatus(int appointmentId) {
        String sql = "SELECT status FROM appointment WHERE id = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, appointmentId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return AppointmentStatus.valueOf(resultSet.getString("status"));
                } else {
                    System.out.println("No appointment found with ID: " + appointmentId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AppointmentStatus.UNKNOWN;
    }

    // Method to insert a new appointment into the database
    public boolean addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointment (dateTime, status, doctor_id, patient_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setTimestamp(1, new Timestamp(appointment.getDateTime().getTime())); // Set dateTime
                statement.setString(2, appointment.getStatus()); // Set status
                statement.setLong(3, appointment.getDoctorId()); // Set doctor_id
                statement.setLong(4, appointment.getPatientId()); // Set patient_id
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("New appointment added.");
                    return true;
                } else {
                    System.out.println("Failed to add appointment.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete an appointment from the database
    public boolean deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM appointment WHERE id = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, appointmentId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Appointment has been deleted.");
                    return true;
                } else {
                    System.out.println("No appointment found with ID: " + appointmentId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to retrieve an appointment by its ID
    public Appointment getAppointment(int appointmentId) {
        String sql = "SELECT id, dateTime, status, doctor_id, patient_id FROM appointment WHERE id = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, appointmentId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    Date dateTime = resultSet.getTimestamp("dateTime");
                    String status = resultSet.getString("status");
                    long doctorId = resultSet.getLong("doctor_id");
                    long patientId = resultSet.getLong("patient_id");
                    Appointment appointment = new Appointment(id, dateTime, status, doctorId, patientId);
                    return appointment;
                } else {
                    System.out.println("No appointment found with ID: " + appointmentId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
