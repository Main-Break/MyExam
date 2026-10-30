package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

/**
 * Usuário do tipo enfermeiro, criado pela NurseFactory.
 *
 * @note Papel no Padrão: Concrete Product (Factory Method)
 */
public class Nurse extends User {

    protected Nurse(String name, String CPF, String email, String passwordHash, UserType type) {
        super(name, CPF, email, passwordHash, type);

    }

    @Override
    public String getID() {
        return this.ID;
    }
}
