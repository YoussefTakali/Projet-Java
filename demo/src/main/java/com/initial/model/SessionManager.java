package com.initial.model;

import com.initial.model.Doctor;
import com.initial.model.Patient;

public class SessionManager {
    private static SessionManager instance;
    private Patient loggedInPatient;
    private Doctor loggedInDoctor;

    private SessionManager() {
        // Private constructor to prevent instantiation
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setLoggedInPatient(Patient patient) {
        this.loggedInPatient = patient;
    }

    public Patient getLoggedInPatient() {
        return loggedInPatient;
    }

    public void setLoggedInDoctor(Doctor doctor) {
        this.loggedInDoctor = doctor;
    }

    public Doctor getLoggedInDoctor() {
        return loggedInDoctor;
    }

    public void clearSession() {
        loggedInPatient = null;
        loggedInDoctor = null;
    }
}
