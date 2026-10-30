package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

/**
 * Cria instâncias de Doctor a partir de um UserRegistration.
 *
 * @note Papel no Padrão: Concrete Creator (Factory Method)
 */
public class DoctorFactory extends UserFactory<Doctor> {
    @Override
    public Doctor createUser(UserRegistration registration) {
        return new Doctor(registration.getName(), registration.getCPF(), registration.getEmail(),
                registration.getPasswordHash(), UserType.DOCTOR,
                registration.getCrm(), registration.getSpecialty());
    }
}