package com.initial.controller;
import com.initial.service.DoctorService;
import com.initial.model.Doctor;

public class DoctorController {
    private DoctorService doctorService;

    public DoctorController() {
        this.doctorService = new DoctorService();
    }

    public void addDoctor(Doctor doctor) {
        doctorService.addDoctor(doctor);
    }

    // Other controller methods for Doctor
}