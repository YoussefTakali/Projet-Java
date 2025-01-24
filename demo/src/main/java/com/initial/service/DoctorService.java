package com.initial.service;

import java.sql.*;
import com.initial.model.Doctor;
import com.initial.db.DBConnection;
public class DoctorService {
    private Connection connection;

    public DoctorService() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    // Method to add a doctor
    public void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctor (username, password, email, firstName, lastName, specialization, department_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, doctor.getUsername());
            statement.setString(2, doctor.getPassword());
            statement.setString(3, doctor.getEmail());
            statement.setString(4, doctor.getFirstName());
            statement.setString(5, doctor.getLastName());
            statement.setString(6, doctor.getSpecialization());
            statement.setLong(7, doctor.getDepartmentId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Other CRUD methods for Doctor (e.g., updateDoctor, deleteDoctor, getDoctorById)
}