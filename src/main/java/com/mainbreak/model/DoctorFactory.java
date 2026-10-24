package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

public class DoctorFactory extends UserFactory {

    private final String crm;
    private final String specialty;

    public DoctorFactory(String crm, String specialty) {
        this.crm = crm;
        this.specialty = specialty;
    }

    @Override
    public User createUser(String name, String CPF, String email, String passwordHash) {
        return new Doctor(name, CPF, email, passwordHash, UserType.DOCTOR, this.crm, this.specialty);
    }
}