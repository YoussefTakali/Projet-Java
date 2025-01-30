package com.initial.controller;

import com.initial.service.PatientService;
import com.initial.model.Patient;

import java.util.List;

public class PatientController {
    private final PatientService patientService;

    public PatientController() {
        this.patientService = new PatientService();
    }

    public void addPatient(Patient patient) {
        patientService.addPatient(patient);
    }

    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    public Patient getPatientById(Long id) {
        return patientService.getPatientById(id);
    }

    public void updatePatient(Patient patient) {
        patientService.updatePatient(patient);
    }

    public void deletePatient(Long id) {
        patientService.deletePatient(id);
    }
}
