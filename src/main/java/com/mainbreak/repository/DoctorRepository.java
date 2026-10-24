package com.mainbreak.repository;

import com.mainbreak.model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {

    private final List<Doctor> doctors = new ArrayList<>();

    public void add(Doctor doctor) {
        this.doctors.add(doctor);
    }

    public Doctor findById(String id) {
        for (Doctor doctor : this.doctors) {
            if (doctor.getID().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    public List<Doctor> findAll() {
        return this.doctors;
    }
}