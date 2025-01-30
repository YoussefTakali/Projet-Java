package com.initial.controller;
import com.initial.service.DoctorService;

import java.util.List;

import com.initial.model.Doctor;

public class DoctorController {
    private DoctorService doctorService;

    public DoctorController() {
        this.doctorService = new DoctorService();
    }

    public void addDoctor(Doctor doctor) {
        doctorService.addDoctor(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    public Doctor getDoctorById(Long id) {
        return doctorService.getDoctorById(id);
    }
}