package com.initial.service;

import com.initial.model.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    private Connection connection;

    public DepartmentService(Connection connection) {
        this.connection = connection;
    }

    // ✅ Add a new department
    public void addDepartment(Department department) {
        String sql = "INSERT INTO department (name, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, department.getName());
            statement.setString(2, department.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Retrieve a department by ID
    public Department getDepartmentById(int id) {
        String sql = "SELECT * FROM department WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Department(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Retrieve all departments
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM department";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                departments.add(new Department(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    // ✅ Update a department
    public void updateDepartment(Department department) {
        String sql = "UPDATE department SET name = ?, description = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, department.getName());
            statement.setString(2, department.getDescription());
            statement.setInt(3, department.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete a department by ID
    public void deleteDepartment(int id) {
        String sql = "DELETE FROM department WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
