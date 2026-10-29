package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

public class PatientFactory extends UserFactory<Patient> {
    @Override
    public Patient createUser(UserRegistration registration) {
        return new Patient(registration.getName(), registration.getCPF(), registration.getEmail(),
                registration.getPasswordHash(), UserType.PATIENT, registration.getBirthDate());
    }
}