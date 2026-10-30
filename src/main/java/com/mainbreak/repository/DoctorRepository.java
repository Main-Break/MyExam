package com.mainbreak.repository;

import com.mainbreak.model.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorRepository {

    private final List<Doctor> doctors = new ArrayList<>();

    public void add(Doctor doctor) {
        this.doctors.add(doctor);
    }

    public Optional<Doctor> findById(String id) {
        for (Doctor doctor : this.doctors) {
            if (doctor.getID().equals(id)) {
                return Optional.of(doctor);
            }
        }
        return Optional.empty();
    }

    public Optional<Doctor> findByEmail(String email) {
        return this.doctors.stream()
                .filter(doctor -> doctor.getEmail().equalsIgnoreCase(email.trim()))
                .findFirst();
    }

    public Optional<Doctor> findByCPF(String CPF) {
        return this.doctors.stream()
                .filter(doctor -> doctor.getCPF().equals(CPF.trim()))
                .findFirst();
    }

    public Optional<Doctor> findByCrm(String crm) {
        return this.doctors.stream()
                .filter(doctor -> doctor.getCrm().equalsIgnoreCase(crm.trim()))
                .findFirst();
    }

    public List<Doctor> findAll() {
        return this.doctors;
    }
}