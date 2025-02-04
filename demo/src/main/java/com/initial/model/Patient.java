package com.initial.model;

import javafx.beans.property.*;

public class Patient {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();

    // Default Constructor
    public Patient() {}

    // Parameterized Constructor
    public Patient(Long id, String firstName, String lastName, String email, 
                   String phoneNumber, String password, String address) {
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.email.set(email);
        this.phoneNumber.set(phoneNumber);
        this.password.set(password);
        this.address.set(address);
    }

    // Getters and Setters for JavaFX TableView

    public LongProperty idProperty() {
        return id;
    }
    public long getId() {
        return id.get();
    }
    public void setId(long id) {
        this.id.set(id);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty emailProperty() {
        return email;
    }
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber.get();
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public StringProperty passwordProperty() {
        return password;
    }
    public String getPassword() {
        return password.get();
    }
    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty addressProperty() {
        return address;
    }
    public String getAddress() {
        return address.get();
    }
    public void setAddress(String address) {
        this.address.set(address);
    }

    @Override
    public String toString() {
        return "Patient{" +
               "id=" + id.get() +
               ", firstName='" + firstName.get() + '\'' +
               ", lastName='" + lastName.get() + '\'' +
               ", email='" + email.get() + '\'' +
               ", phoneNumber='" + phoneNumber.get() + '\'' +
               ", password='" + password.get() + '\'' +
               ", address='" + address.get() + '\'' +
               '}';
    }
}
