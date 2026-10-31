package com.mainbreak.controller;

import com.mainbreak.model.Patient;
import com.mainbreak.repository.PatientRepository;
import com.mainbreak.service.RegistrationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PatientController {
    private final PatientRepository patientRepository;
    private final RegistrationService registrationService;

    public PatientController(PatientRepository patientRepository, RegistrationService registrationService) {
        this.patientRepository = Objects.requireNonNull(patientRepository);
        this.registrationService = Objects.requireNonNull(registrationService);
    }

    public Patient create(String name, String CPF, String email, String password, LocalDate birthDate) {
        return registrationService.createPatient(name, CPF, email, password, birthDate);
    }

    public List<Patient> list() {
        return this.patientRepository.findAll();
    }
}
