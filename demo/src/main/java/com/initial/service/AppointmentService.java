package com.initial.service;

import com.initial.db.DBConnection;
import com.initial.model.Appointment;
import com.initial.model.AppointmentStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentService {
    private final DBConnection dbConnection;
    private Connection connection;

    public AppointmentService() {
        dbConnection = DBConnection.getInstance();
        this.connection = dbConnection.getConnection();
    }

    // Method to cancel an appointment
    public void cancelAppointment(int appointmentId) {
        String sql = "UPDATE appointment SET status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, AppointmentStatus.CANCELED.toString());
            statement.setInt(2, appointmentId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment has been canceled.");
            } else {
                System.out.println("No appointment found with ID: " + appointmentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to reschedule an appointment
    public boolean rescheduleAppointment(int appointmentId, Date newDate) {
        String sql = "UPDATE appointment SET dateTime = ?, status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, new Timestamp(newDate.getTime()));
            statement.setString(2, AppointmentStatus.RESCHEDULED.toString());
            statement.setInt(3, appointmentId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get the status of an appointment
    public AppointmentStatus getAppointmentStatus(int appointmentId) {
        String sql = "SELECT status FROM appointment WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return AppointmentStatus.valueOf(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AppointmentStatus.UNKNOWN;
    }

    // Method to insert a new appointment
    public boolean addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointment (dateTime, status, doctor_id, patient_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, new Timestamp(appointment.getDateTime().getTime()));
            statement.setString(2, appointment.getStatus());
            statement.setLong(3, appointment.getDoctorId());
            statement.setLong(4, appointment.getPatientId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete an appointment
    public boolean deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM appointment WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to retrieve an appointment by ID
    public Appointment getAppointment(int appointmentId) {
        String sql = "SELECT id, dateTime, status, doctor_id, patient_id FROM appointment WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Appointment(
                    resultSet.getInt("id"),
                    resultSet.getTimestamp("dateTime"),
                    resultSet.getString("status"),
                    resultSet.getLong("doctor_id"),
                    resultSet.getLong("patient_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get all appointments for a specific patient
    public List<Appointment> getAppointmentsByPatientId(long patientId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT id, dateTime, status, doctor_id FROM appointment WHERE patient_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                appointments.add(new Appointment(
                    resultSet.getInt("id"),
                    resultSet.getTimestamp("dateTime"),
                    resultSet.getString("status"),
                    resultSet.getLong("doctor_id"),
                    patientId
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Method to get all appointments for a specific doctor
    public List<Appointment> getAppointmentsByDoctorId(long doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT id, dateTime, status, patient_id FROM appointment WHERE doctor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, doctorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                appointments.add(new Appointment(
                    resultSet.getInt("id"),
                    resultSet.getTimestamp("dateTime"),
                    resultSet.getString("status"),
                    doctorId,
                    resultSet.getLong("patient_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Close the connection manually when the service is no longer needed
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}