package com.mainbreak.repository;

import com.mainbreak.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    private final List<Patient> patients = new ArrayList<>();

    public void add(Patient patient) {
        this.patients.add(patient);
    }

    public Patient findById(String id) {
        for (Patient patient : this.patients) {
            if (patient.getID().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    public List<Patient> findAll() {
        return this.patients;
    }
}