package com.mainbreak.controller;

import com.mainbreak.model.Doctor;
import com.mainbreak.model.DoctorFactory;
import com.mainbreak.model.UserFactory;
import com.mainbreak.repository.DoctorRepository;

public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor create(String name, String CPF, String email, String passwordHash, String crm, String specialty) {
        UserFactory factory = new DoctorFactory(crm, specialty);
        Doctor doctor = (Doctor) factory.createUser(name, CPF, email, passwordHash);
        this.doctorRepository.add(doctor);
        return doctor;
    }

    public boolean edit(String id, String name, String CPF, String email, String passwordHash) {
        Doctor doctor = this.doctorRepository.findById(id);
        if (doctor == null) {
            return false;
        }
        // atualizar campos do doctor aqui
        return true;
    }
}