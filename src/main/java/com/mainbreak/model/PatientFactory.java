package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

public class PatientFactory extends UserFactory {

    @Override
    public User createUser(String name, String CPF, String email, String passwordHash) {
        return new Patient(name, CPF, email, passwordHash, UserType.PATIENT);
    }
}