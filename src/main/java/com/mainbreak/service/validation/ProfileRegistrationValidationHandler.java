package com.mainbreak.service.validation;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.model.UserRegistration;
import com.mainbreak.model.enums.UserType;

import java.time.LocalDate;

/**
 * Valida os dados específicos de cada perfil de usuário.
 *
 * @note Papel no Padrão: Concrete Handler para regras de perfil da Chain of Responsibility.
 */
public class ProfileRegistrationValidationHandler extends RegistrationValidationHandler {
    // Exige a data de nascimento do paciente e os dados profissionais do médico.
    @Override
    protected void validateCurrent(UserRegistration registration) {
        System.out.println("[CoR] Validando os campos específicos do perfil.");
        if (registration.getType() == UserType.PATIENT) {
            LocalDate birthDate = registration.getBirthDate();
            if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
                throw new ValidationException("Informe uma data de nascimento válida.");
            }
        } else if (registration.getType() == UserType.DOCTOR
                && (registration.getCrm() == null || registration.getCrm().isBlank()
                || registration.getSpecialty() == null || registration.getSpecialty().isBlank())) {
            throw new ValidationException("CRM e especialidade são obrigatórios.");
        }
    }
}
