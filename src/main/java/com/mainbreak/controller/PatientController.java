package com.mainbreak.controller;

import com.mainbreak.model.Patient;
import com.mainbreak.service.RegistrationService;

import java.time.LocalDate;

public class PatientController {
    private final RegistrationService registrationService;

    public PatientController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public Patient create(String name, String CPF, String email, String password, LocalDate birthDate) {
        return registrationService.createPatient(name, CPF, email, password, birthDate);
    }
}