package com.mainbreak.controller;

import com.mainbreak.model.Patient;
import com.mainbreak.model.PatientFactory;
import com.mainbreak.model.UserFactory;
import com.mainbreak.repository.PatientRepository;

public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient create(String name, String CPF, String email, String passwordHash) {
        UserFactory factory = new PatientFactory();
        Patient patient = (Patient) factory.createUser(name, CPF, email, passwordHash);
        this.patientRepository.add(patient);
        return patient;
    }
}