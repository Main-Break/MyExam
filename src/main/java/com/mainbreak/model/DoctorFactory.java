package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

public class DoctorFactory extends UserFactory<Doctor> {
    @Override
    public Doctor createUser(UserRegistration registration) {
        return new Doctor(registration.getName(), registration.getCPF(), registration.getEmail(),
                registration.getPasswordHash(), UserType.DOCTOR,
                registration.getCrm(), registration.getSpecialty());
    }
}