package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

/**
 * Usuário do tipo médico, criado pela DoctorFactory.
 *
 * @note Papel no Padrão: Concrete Product (Factory Method)
 */
public class Doctor extends User {
    private String crm;
    private String specialty;

    protected Doctor(String name, String CPF, String email, String passwordHash, UserType type, String crm, String specialty) {
        super(name, CPF, email, passwordHash, type);
        this.crm = crm;
        this.specialty = specialty;
    }

    @Override
    public String getID() {
        return ID;
    }

    public String getCrm() {
        return crm;
    }

    public String getSpecialty() {
        return specialty;
    }
}
