package com.mainbreak.service.validation;

import com.mainbreak.factory.UserRegistration;

public abstract class RegistrationValidationHandler {
    private RegistrationValidationHandler next;

    // Liga o próximo validador para que cada regra seja executada em sequência.
    public RegistrationValidationHandler setNext(RegistrationValidationHandler next) {
        this.next = next;
        return next;
    }

    // Executa esta regra e encaminha o cadastro adiante somente se ela passar.
    public final void validate(UserRegistration registration) {
        validateCurrent(registration);
        if (next != null) {
            next.validate(registration);
        }
    }

    // Define a validação específica que cada elo da cadeia deve executar.
    protected abstract void validateCurrent(UserRegistration registration);
}
