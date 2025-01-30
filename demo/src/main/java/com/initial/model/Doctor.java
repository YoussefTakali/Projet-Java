package com.initial.model;

public class Doctor {
    private Long id;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String specialization;
    private int departmentId; // FK to Department
    public Doctor(String password, String email, String firstName, String lastName, String specialization, int departmentId) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.departmentId = departmentId;
    }
    public Doctor(){}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    @Override
    public String toString() {
        return "Doctor [id=" + id + ", password=" + password + ", email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + ", specialization=" + specialization + ", departmentId=" + departmentId
                + "]";
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public int getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    // Getters and setters...
}