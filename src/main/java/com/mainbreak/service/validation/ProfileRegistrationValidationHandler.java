package com.mainbreak.service.validation;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.factory.UserRegistration;
import com.mainbreak.model.UserRole;

import java.time.LocalDate;

public class ProfileRegistrationValidationHandler extends RegistrationValidationHandler {
    // Exige os campos específicos do perfil para impedir a criação de contas incompletas.
    @Override
    protected void validateCurrent(UserRegistration registration) {
        if (registration.getRole() == UserRole.PATIENT) {
            validatePatient(registration);
        } else if (registration.getRole() == UserRole.DOCTOR) {
            validateDoctor(registration);
        }
    }

    // Confere se o paciente informou uma data de nascimento possível.
    private void validatePatient(UserRegistration registration) {
        LocalDate birthDate = registration.getBirthDate();
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
            throw new ValidationException("Informe uma data de nascimento válida.");
        }
    }

    // Confere se o cadastro profissional possui CRM e especialidade.
    private void validateDoctor(UserRegistration registration) {
        if (registration.getCrm() == null || registration.getCrm().isBlank()
                || registration.getSpecialty() == null || registration.getSpecialty().isBlank()) {
            throw new ValidationException("CRM e especialidade são obrigatórios.");
        }
    }
}
