package com.mainbreak.service.validation;

import com.mainbreak.model.UserRegistration;

/**
 * Elo base da validação de cadastros.
 *
 * @note Papel no Padrão: Handler abstrato que liga validadores da Chain of Responsibility.
 */
public abstract class RegistrationValidationHandler {
    private RegistrationValidationHandler next;

    // Configura o próximo elo e retorna-o para permitir encadear handlers.
    public RegistrationValidationHandler setNext(RegistrationValidationHandler next) {
        this.next = next;
        return next;
    }

    // Executa esta regra e continua pela cadeia somente se a validação passar.
    public final void validate(UserRegistration registration) {
        validateCurrent(registration);
        if (next != null) {
            next.validate(registration);
        }
    }

    // Define a verificação específica implementada por cada elo.
    protected abstract void validateCurrent(UserRegistration registration);
}
