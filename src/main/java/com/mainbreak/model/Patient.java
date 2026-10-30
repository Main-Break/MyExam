package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

import java.time.LocalDate;

/**
 * Usuário do tipo paciente, criado pela PatientFactory.
 *
 * @note Papel no Padrão: Concrete Product (Factory Method)
 */
public class Patient extends User {
    private final LocalDate birthDate;

    protected Patient(String name, String CPF, String email, String passwordHash, UserType type,
                      LocalDate birthDate) {
        super(name, CPF, email, passwordHash, type);
        this.birthDate = birthDate;
    }

    @Override
    public String getID() {
        return this.ID;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
