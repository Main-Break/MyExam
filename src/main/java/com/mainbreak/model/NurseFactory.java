package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

public class NurseFactory extends UserFactory<Nurse> {

    @Override
    public Nurse createUser(UserRegistration registration) {
        return new Nurse(registration.getName(), registration.getCPF(), registration.getEmail(),
                registration.getPasswordHash(), UserType.NURSE);
    }
}