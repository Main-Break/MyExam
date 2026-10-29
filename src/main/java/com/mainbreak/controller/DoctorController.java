package com.mainbreak.controller;

import com.mainbreak.model.Doctor;
import com.mainbreak.repository.DoctorRepository;
import com.mainbreak.service.RegistrationService;

public class DoctorController {
    private final DoctorRepository doctorRepository;
    private final RegistrationService registrationService;

    public DoctorController(DoctorRepository doctorRepository,
                            RegistrationService registrationService) {
        this.doctorRepository = doctorRepository;
        this.registrationService = registrationService;
    }

    public Doctor create(String name, String CPF, String email, String password, String crm, String specialty) {
        return registrationService.createDoctor(name, CPF, email, password, crm, specialty);
    }

    public boolean edit(String id, String name, String CPF, String email, String passwordHash) {
        if (this.doctorRepository.findById(id).isEmpty()) {
            return false;
        }
        // atualizar campos do doctor aqui
        return true;
    }
}