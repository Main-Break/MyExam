package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

public class NurseFactory extends UserFactory {

    @Override
    public User createUser(String name, String CPF, String email, String passwordHash) {
        return new Nurse(name, CPF, email, passwordHash, UserType.NURSE);
    }
}