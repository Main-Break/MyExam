package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

/**
 * Cria instâncias de Patient a partir de um UserRegistration.
 *
 * @note Papel no Padrão: Concrete Creator (Factory Method)
 */
public class PatientFactory extends UserFactory<Patient> {
    @Override
    public Patient createUser(UserRegistration registration) {
        return new Patient(registration.getName(), registration.getCPF(), registration.getEmail(),
                registration.getPasswordHash(), UserType.PATIENT, registration.getBirthDate());
    }
}