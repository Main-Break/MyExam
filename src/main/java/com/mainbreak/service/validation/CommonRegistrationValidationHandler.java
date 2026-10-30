package com.mainbreak.service.validation;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.model.UserRegistration;

import java.util.regex.Pattern;

/**
 * Valida os campos presentes em todos os tipos de cadastro.
 *
 * @note Papel no Padrão: Concrete Handler para os campos comuns da Chain of Responsibility.
 */
public class CommonRegistrationValidationHandler extends RegistrationValidationHandler {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    // Rejeita dados comuns inválidos antes dos validadores de perfil e unicidade.
    @Override
    protected void validateCurrent(UserRegistration registration) {
        System.out.println("[CoR] Validando os campos comuns do cadastro.");
        if (registration == null) {
            throw new ValidationException("Os dados do cadastro são obrigatórios.");
        }
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
        if (registration.getType() == null) {
            throw new ValidationException("O tipo de usuário é obrigatório.");
        }
    }
}
