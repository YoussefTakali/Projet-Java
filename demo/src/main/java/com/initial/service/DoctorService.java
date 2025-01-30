package com.initial.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.initial.model.Doctor;
import com.initial.db.DBConnection;
public class DoctorService {
    private Connection connection;

    public DoctorService() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    // Method to add a doctor
    public void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctor (password, email, firstName, lastName, specialization, department_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the values for each column except the auto-incremented ID
            statement.setString(1, doctor.getPassword());
            statement.setString(2, doctor.getEmail());
            statement.setString(3, doctor.getFirstName());
            statement.setString(4, doctor.getLastName());
            statement.setString(5, doctor.getSpecialization());
            statement.setInt(6, doctor.getDepartmentId());
    
            // Execute the insert
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Doctor loginDoctor(String email, String password) {
        String sql = "SELECT * FROM doctor WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getLong("id"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setEmail(resultSet.getString("email"));
                doctor.setFirstName(resultSet.getString("firstName"));
                doctor.setLastName(resultSet.getString("lastName"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setDepartmentId(resultSet.getInt("department_id"));
                return doctor;
                    

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Login failed
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctor";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getLong("id"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setEmail(resultSet.getString("email"));
                doctor.setFirstName(resultSet.getString("firstName"));
                doctor.setLastName(resultSet.getString("lastName"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setDepartmentId(resultSet.getInt("department_id"));
                
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return doctors;
    }

    public Doctor getDoctorById(Long id) {
        Doctor doctor = null;
        String sql = "SELECT * FROM Doctor WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                doctor = new Doctor();
                doctor.setId(resultSet.getLong("id"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setEmail(resultSet.getString("email"));
                doctor.setFirstName(resultSet.getString("firstName"));
                doctor.setLastName(resultSet.getString("lastName"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setDepartmentId(resultSet.getInt("department_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return doctor;
    }
    public List<Doctor> getDoctorsByDepartment(Long departmentId) {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctor WHERE department_id = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, departmentId);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getLong("id"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setEmail(resultSet.getString("email"));
                doctor.setFirstName(resultSet.getString("firstName"));
                doctor.setLastName(resultSet.getString("lastName"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setDepartmentId(resultSet.getInt("department_id"));
    
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return doctors;
    }
    

}