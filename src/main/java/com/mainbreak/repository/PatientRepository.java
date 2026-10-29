package com.mainbreak.repository;

import com.mainbreak.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientRepository {

    private final List<Patient> patients = new ArrayList<>();

    public void add(Patient patient) {
        this.patients.add(patient);
    }

    public Optional<Patient> findById(String id) {
        for (Patient patient : this.patients) {
            if (patient.getID().equals(id)) {
                return Optional.of(patient);
            }
        }
        return Optional.empty();
    }

    public Optional<Patient> findByEmail(String email) {
        return this.patients.stream()
                .filter(patient -> patient.getEmail().equalsIgnoreCase(email.trim()))
                .findFirst();
    }

    public Optional<Patient> findByCPF(String CPF) {
        return this.patients.stream()
                .filter(patient -> patient.getCPF().equals(CPF.trim()))
                .findFirst();
    }

    public List<Patient> findAll() {
        return this.patients;
    }
}