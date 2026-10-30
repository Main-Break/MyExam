package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

/**
 * Cria instâncias de Nurse a partir de um UserRegistration.
 *
 * @note Papel no Padrão: Concrete Creator (Factory Method)
 */
public class NurseFactory extends UserFactory<Nurse> {

    @Override
    public Nurse createUser(UserRegistration registration) {
        return new Nurse(registration.getName(), registration.getCPF(), registration.getEmail(),
                registration.getPasswordHash(), UserType.NURSE);
    }
}