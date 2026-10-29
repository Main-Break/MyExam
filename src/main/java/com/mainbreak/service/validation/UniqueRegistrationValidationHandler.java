package com.mainbreak.service.validation;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.model.UserRegistration;
import com.mainbreak.model.enums.UserType;
import com.mainbreak.repository.DoctorRepository;
import com.mainbreak.repository.PatientRepository;

import java.util.Objects;

/**
 * Verifica se os identificadores do cadastro já pertencem a outra conta.
 *
 * @note Papel no Padrão: Concrete Handler final para unicidade na Chain of Responsibility.
 */
public class UniqueRegistrationValidationHandler extends RegistrationValidationHandler {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    /**
     * Recebe os dois repositórios para verificar unicidade entre perfis sem um UserRepository.
     *
     * @note Papel no Padrão: Dependências do Concrete Handler fornecidas externamente.
     */
    public UniqueRegistrationValidationHandler(DoctorRepository doctorRepository,
                                               PatientRepository patientRepository) {
        this.doctorRepository = Objects.requireNonNull(doctorRepository);
        this.patientRepository = Objects.requireNonNull(patientRepository);
    }

    // Rejeita e-mail, CPF ou CRM duplicados; Optional vazio significa que o valor está disponível.
    @Override
    protected void validateCurrent(UserRegistration registration) {
        System.out.println("[CoR] Verificando e-mail, CPF e CRM disponíveis.");
        boolean doctorEmailExists = doctorRepository.findByEmail(registration.getEmail()).isPresent();
        boolean patientEmailExists = patientRepository.findByEmail(registration.getEmail()).isPresent();
        if (doctorEmailExists || patientEmailExists) {
            throw new ValidationException("Já existe uma conta com este e-mail.");
        }

        boolean doctorCpfExists = doctorRepository.findByCPF(registration.getCPF()).isPresent();
        boolean patientCpfExists = patientRepository.findByCPF(registration.getCPF()).isPresent();
        if (doctorCpfExists || patientCpfExists) {
            throw new ValidationException("Já existe uma conta com este CPF.");
        }

        if (registration.getType() == UserType.DOCTOR
                && doctorRepository.findByCrm(registration.getCrm()).isPresent()) {
            throw new ValidationException("Já existe um médico com este CRM.");
        }
    }
}
