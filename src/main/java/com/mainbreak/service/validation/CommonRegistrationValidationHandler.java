package com.mainbreak.service.validation;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.factory.UserRegistration;
import com.mainbreak.model.UserRole;

import java.util.regex.Pattern;

public class CommonRegistrationValidationHandler extends RegistrationValidationHandler {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    // Rejeita dados comuns incompletos antes de verificar regras específicas do perfil.
    @Override
    protected void validateCurrent(UserRegistration registration) {
        if (registration.getName() == null || registration.getName().isBlank()) {
            throw new ValidationException("O nome é obrigatório.");
        }
        if (registration.getCPF() == null || !registration.getCPF().matches("\\d{11}")) {
            throw new ValidationException("O CPF deve conter exatamente 11 números.");
        }
        if (registration.getEmail() == null
                || !EMAIL_PATTERN.matcher(registration.getEmail()).matches()) {
            throw new ValidationException("Informe um e-mail válido.");
        }
        if (registration.getPasswordHash() == null || registration.getPasswordHash().isBlank()) {
            throw new ValidationException("O hash da senha é obrigatório.");
        }
        if (registration.getRole() == null) {
            throw new ValidationException("O tipo de conta é obrigatório.");
        }
        if (registration.getRole() != UserRole.ADMIN
                && registration.getRole() != UserRole.DOCTOR
                && registration.getRole() != UserRole.PATIENT) {
            throw new ValidationException("O tipo de conta não é suportado.");
        }
    }
}
