package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

public class Patient extends User {

    protected Patient(String name, String CPF, String email, String passwordHash, UserType type) {
        super(name, CPF, email, passwordHash, type);
    }

    @Override
    public String getID() {
        return this.ID;
    }
}
