package com.mainbreak.service.validation;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.factory.UserRegistration;
import com.mainbreak.model.UserRole;
import com.mainbreak.repository.UserRepository;

public class UniqueRegistrationValidationHandler extends RegistrationValidationHandler {
    private final UserRepository userRepository;

    // Recebe o repositório para verificar duplicidade antes de persistir uma conta.
    public UniqueRegistrationValidationHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Impede reutilização de e-mail, CPF ou CRM antes que o creator produza o usuário.
    @Override
    protected void validateCurrent(UserRegistration registration) {
        if (userRepository.findByEmail(registration.getEmail()).isPresent()) {
            throw new ValidationException("Já existe uma conta com este e-mail.");
        }
        if (userRepository.findByCPF(registration.getCPF()).isPresent()) {
            throw new ValidationException("Já existe uma conta com este CPF.");
        }
        if (registration.getRole() == UserRole.DOCTOR
                && userRepository.findDoctorByCrm(registration.getCrm()).isPresent()) {
            throw new ValidationException("Já existe um médico com este CRM.");
        }
    }
}
